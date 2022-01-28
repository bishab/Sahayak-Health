from aiohttp import request
import requests
import json
from utils.location_fetch import * 
#For Covid Data
response = requests.get("https://api.covid19api.com/summary")
jsondata=response.json()

#------------------Nepal Cases------------------------------------
#CountryWiseData = response.json()["Countries"][121]
#LatestUpdatedDate = response.json()["Countries"][121]["Date"][0:10]
#TotalConfirmedCases = response.json()["Countries"][121]["TotalConfirmed"]
#NewConfirmedCases = response.json()["Countries"][121]["NewConfirmed"]
#TotalDeathCases = response.json()["Countries"][121]["TotalDeaths"]
#NewDeathCases = response.json()["Countries"][121]["NewDeaths"]
#TotalRecoveredCases = response.json()["Countries"][121]["TotalRecovered"]
#NewRecoveredCases = response.json()["Countries"][121]["NewRecovered"]


#----------- FETCH COUNTRY FROM IP ADDRESS AND SHOW COVID CONDITION -------------------

def fetch_country_num(data):
    country=find_location()
    country_num=0
    for i in range(193):
        if data["Countries"][i]["Country"]==country:
            country_num=i
            break
    return country_num


def return_data(data,country_num):
    CountryWiseData = data["Countries"][country_num]
    LatestUpdatedDate = data["Countries"][country_num]["Date"][0:10]
    TotalConfirmedCases = data["Countries"][country_num]["TotalConfirmed"]
    NewConfirmedCases = data["Countries"][country_num]["NewConfirmed"]
    TotalDeathCases = data["Countries"][country_num]["TotalDeaths"]
    NewDeathCases = data["Countries"][country_num]["NewDeaths"]
    TotalRecoveredCases = data["Countries"][country_num]["TotalRecovered"]
    NewRecoveredCases = data["Countries"][country_num]["NewRecovered"]
    return [CountryWiseData,LatestUpdatedDate,TotalConfirmedCases,NewConfirmedCases,TotalDeathCases,\
    NewDeathCases,TotalRecoveredCases,NewRecoveredCases]

cn=fetch_country_num(jsondata)
finalapidata=return_data(jsondata,cn)
#---------------------------------------------------------------------------------------------

#---------------Global Cases --------------------------------------------
TotalConfirmedCasesG = jsondata["Global"]["TotalConfirmed"]
NewConfirmetdCasesG = jsondata["Global"]["NewConfirmed"]
TotalDeathCasesG = jsondata["Global"]["TotalDeaths"]
NewDeathCasesG = jsondata["Global"]["NewDeaths"]
TotalRecoveredCasesG = jsondata["Global"]["TotalRecovered"]
NewRecoveredCasesG = jsondata["Global"]["NewRecovered"]
