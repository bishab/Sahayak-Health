import requests
import json

def hospital_fetcher():
    """"
    Returns hospital names from api
    """
    details=requests.get("https://corona.askbhunte.com/api/v1/hospitals")
    details=json.loads(details.text)
    list_details=details['data']
    hospitals=[]
    for hosp in list_details:
        hospitals.append(hosp['name'])
    return hospitals

