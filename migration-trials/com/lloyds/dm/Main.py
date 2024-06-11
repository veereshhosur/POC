import logging
import multiprocessing
from functools import partial
import ConfigLoader
import CheckpointManager
import SparkJob


# Initialize logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')
logger = logging.getLogger(__name__)

def process_table(table, config, checkpoint_manager):
   spark_job = SparkJob(config, checkpoint_manager)
   spark_job.read_and_write_table(table)

def main():
   config_loader = ConfigLoader('config.json')
   config = config_loader.get_config()

   checkpoint_manager = CheckpointManager('checkpoints')


   worker_function = partial(process_table, config=config, checkpoint_manager=checkpoint_manager, prometheus_metrics=prometheus_metrics)

   pool = multiprocessing.Pool(len(config['tables']))
   pool.map(worker_function, config['tables'])
   pool.close()
   pool.join()

if __name__ == "__main__":
   main()