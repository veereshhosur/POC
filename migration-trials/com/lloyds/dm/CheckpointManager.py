import os


class CheckpointManager:
    def __init__(self, checkpoint_dir):
        self.checkpoint_dir = checkpoint_dir
        os.makedirs(self.checkpoint_dir, exist_ok=True)

    def mark_checkpoint(self, table):
        with open(os.path.join(self.checkpoint_dir, f'{table}.checkpoint'), 'w') as f:
            f.write('success')

    def is_table_processed(self, table):
        return os.path.exists(os.path.join(self.checkpoint_dir, f'{table}.checkpoint'))
