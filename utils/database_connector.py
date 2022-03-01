import requests
import json
def viewpatient(email):
    """
    Displays patient data based on a condition
    Args:
        email: primary key for displaying user data
    """

    link="http://20.41.221.66:7000/getreg/"+email
    userdata=json.loads(requests.get(link).text)
    if len(userdata)==0:
        return "No record"
    else:
        userdata=userdata[0]
        user_dob=userdata['date_of_birth'].split("T")[0]
        userdata["date_of_birth"]=user_dob
        return userdata

def delpatient(email):
    """
    Deletes patient data
    """
    link="http://20.41.221.66:7000/delreg/"+email
    status=requests.delete(link)
    if  '200' in str(status):
        return "successful"
    else:
        return "unsuccessful"


'''
def appointment_table_creator():
    """
    Creates a table for appointment if it does not exist.
    Args:
        None
    Returns:
        None    
    """
    if 'db'not in os.listdir():
        os.mkdir('db') 
    con=sqlite3.connect("db/appointment_database.db")
    con.execute("""create table if not exists appointment(first_name text, last_name text, address text, date text, contact_number text)""")
    con.commit()
    con.close()

def appointment_entry_dumper(fn,ln,ad,dt,cn):
    """
    Stores the appointment entries to the database.
    Args:
        fn: first_name
        ln: last_name
        ad: address
        dt: date
        cn: contact_number
    Returns:
        None    
    """
    con=sqlite3.connect("db/appointment_database.db")
    con.execute("insert into appointment (first_name,last_name,address,date,contact_number) values (?,?,?,?,?)", (fn,ln,ad,dt,cn))
    con.commit()
    con.close()

def appointment_entry_deletor(cn):
    """
    Removes the entry based on the unique user contact_number.
    Args:
        cn: contact_number
    Returns:
        None    
    """
    con=sqlite3.connect("db/appointment_database.db")
    con.execute("delete from appointment where contact_number=?",(cn,))
    con.commit()
    con.close()

def appointment_data_view():
    """
    Displays the total entries of the table.
    Args:
        None
    Returns:
        None    
    """
    con=sqlite3.connect("db/appointment_database.db")
    cur=con.cursor()
    cur.execute("select * from appointment")
    data=cur.fetchall()
    for row in data:
        print(row)

def appointment_data_lookup(cn):
    """
    Displays the result based on a key.
    Args:
        cn: contact_number
    Returns:
        None    
    """
    con=sqlite3.connect("db/appointment_database.db")
    cur=con.cursor()
    cur.execute("select * from appointment where contact_number=?",(cn,))
    data=cur.fetchall()
    return data
'''
