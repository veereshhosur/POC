import time
import logging
from pyspark.sql import SparkSession
import SchemaUtils


class SparkJob:
    def __init__(self, config, checkpoint_manager, prometheus_metrics):
        self.config = config
        self.checkpoint_manager = checkpoint_manager
        self.prometheus_metrics = prometheus_metrics

    def read_and_write_table(self, table):
        if self.checkpoint_manager.is_table_processed(table):
            logger.info(f"Table {table} already processed. Skipping.")
            return

        retries = 0
        success = False
        while retries < self.config['retry']['max_retries'] and not success:
            try:
                start_time = time.time()
                spark = SparkSession.builder \
                    .appName(f"ReadFromDB-{table}") \
                    .config("spark.master", "local[*]") \
                    .getOrCreate()

                jdbc_url = f"jdbc:mysql://{self.config['jdbc']['hostname']}:{self.config['jdbc']['port']}/{self.config['jdbc']['database']}"

                jdbc_df = spark.read \
                    .format("jdbc") \
                    .option("url", jdbc_url) \
                    .option("dbtable", table) \
                    .option("user", self.config['jdbc']['username']) \
                    .option("password", self.config['jdbc']['password']) \
                    .load()

                if table in self.config['parquet_schema']:
                    schema = SchemaUtils.create_schema(self.config['parquet_schema'][table])
                    jdbc_df = spark.createDataFrame(jdbc_df.rdd, schema)

                output_path = f"{self.config['cloud_storage']['path']}{table}/"

                if table in self.config['parquet_partitions']:
                    jdbc_df.write.partitionBy(*self.config['parquet_partitions'][table]).parquet(output_path)
                else:
                    jdbc_df.write.parquet(output_path)

                duration = time.time() - start_time
                logger.info(f"Finished writing {table} to {output_path} in {duration} seconds")

                self.prometheus_metrics.increment_processing_count(table)
                self.prometheus_metrics.set_processing_duration(table, duration)
                self.checkpoint_manager.mark_checkpoint(table)

                spark.stop()
                success = True
            except Exception as e:
                logger.error(f"Error processing table {table}: {e}")
                self.prometheus_metrics.increment_processing_errors(table)
                retries += 1
                if retries < self.config['retry']['max_retries']:
                    logger.info(
                        f"Retrying {table} ({retries}/{self.config['retry']['max_retries']}) after {self.config['retry']['delay']} seconds...")
                    time.sleep(self.config['retry']['delay'])
                else:
                    logger.error(
                        f"Failed to process table {table} after {self.config['retry']['max_retries']} attempts.")