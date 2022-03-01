import requests
import json
#------ PATIENT REGISTRATION RELATED FUNCTIONS ---------------------
def view_patient_reg(email):
    """
    Displays patient registration detail based on email
    Args:
        email: primary key for displaying user data
    """

    link="http://20.41.221.66:7000/patient/getreg/"+email
    userdata=json.loads(requests.get(link).text)
    if len(userdata)==0:
        return "No record"
    else:
        userdata=userdata[0]
        user_dob=userdata['date_of_birth'].split("T")[0]
        userdata["date_of_birth"]=user_dob
        return userdata

def del_patient_reg(email):
    """
    Deletes patient registration details
    Args:
        email: primary key for filtering user data
    """
    link="http://20.41.221.66:7000/patient/delreg/"+email
    status=requests.delete(link)
    if  '200' in str(status):
        return "successful"
    else:
        return "unsuccessful"

#------ PATIENT APPOINTMENT RELATED FUNCTIONS ---------------------
def view_patient_appointment(email):
    """
    Displays patient appointment detail based on email
    Args:
        email: primary key for displaying user data
    """
    link="http://20.41.221.66:7000/patient/getapp/"+email
    userdata=json.loads(requests.get(link).text)
    if len(userdata)==0:
        return "No record"
    else:
        return userdata[0]


def del_patient_appointment(email):
    """
    Deletes patient appointment details
    Args:
        email: primary key for filtering user data
    """
    link="http://20.41.221.66:7000/patient/delapp/"+email
    status=requests.delete(link)
    if  '200' in str(status):
        return "successful"
    else:
        return "unsuccessful"
