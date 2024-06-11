from pyspark.sql.types import StructType, StructField, StringType, IntegerType, DoubleType, DateType


class SchemaUtils:
    @staticmethod
    def create_schema(schema_dict):
        schema = StructType()
        for column, dtype in schema_dict.items():
            if dtype == 'string':
                schema.add(StructField(column, StringType(), True))
            elif dtype == 'integer':
                schema.add(StructField(column, IntegerType(), True))
            elif dtype == 'double':
                schema.add(StructField(column, DoubleType(), True))
            elif dtype == 'date':
                schema.add(StructField(column, DateType(), True))
            else:
                raise ValueError(f"Unsupported data type {dtype} for column {column}")
        return schema
