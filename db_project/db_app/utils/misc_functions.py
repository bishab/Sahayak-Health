import requests
import json

def hospital_fetcher():
    """"
    Returns the list of hospitals from api
    """
    details=requests.get("https://corona.askbhunte.com/api/v1/hospitals")
    details=json.loads(details.text)
    list_details=details['data']
    hospitals=[]
    for hosp in list_details:
        hospitals.append(hosp['name'])
    return hospitals

def doctors_fetcher():
    """
    Returns the list of doctors
    """
    return Response({"doctors":})