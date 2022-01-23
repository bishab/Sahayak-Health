import os
import logging
def log_setup():
    """
    Sets up the logger.
    Args:
        None
    Returns:
        A logger method
    """

    if 'logs' not in os.listdir():                          
        os.mkdir('logs')
    logger = logging.getLogger()
    logger.setLevel(logging.DEBUG)									
    formatter = logging.Formatter(fmt='%(asctime)s - %(levelname)s - %(message)s',datefmt='%Y/%m/%d %I-%M %p')	
    fh = logging.FileHandler(f"logs/user.log")							
    fh.setLevel(logging.INFO)
    fh.setFormatter(formatter)
    logger.addHandler(fh)
    return logger