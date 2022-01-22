import mysql.connector

def DataUpdater():
    """
    Updates the data into the mysql backend.
    
    Args:
           
    """
    mydb=mysql.connector.connect(
        host="localhost",
        user="root",
        passwd="root",
        database="appointment"
    )

    mycursor=mydb.cursor()

    query1="show databases;"
    mycursor.execute(mycursor)

if __name__=="__main__":
    print(DataUpdater())
    