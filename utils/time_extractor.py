from datetime import datetime
def time_extract():
    """
    Extracts and returns the time in format: 11 (24 hr format)
    Args:
        None
    Returns:
        A time string
    """
    current_time= int(datetime.today().strftime("%H"))
    if current_time in range(0,12):
        return "Good Morning!"
    elif current_time in range(12,16):
        return "Good Afternoon!"
    elif current_time in range(16,24):
        return "Good Evening!"
        
    

