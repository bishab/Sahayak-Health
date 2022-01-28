import requests

#For Covid Data
response = requests.get("https://api.covid19api.com/summary")
#------------------Nepal Cases------------------------------------
CountryWiseData = response.json()["Countries"][121]
LatestUpdatedDate = response.json()["Countries"][121]["Date"][0:10]
TotalConfirmedCases = response.json()["Countries"][121]["TotalConfirmed"]
NewConfirmedCases = response.json()["Countries"][121]["NewConfirmed"]
TotalDeathCases = response.json()["Countries"][121]["TotalDeaths"]
NewDeathCases = response.json()["Countries"][121]["NewDeaths"]
TotalRecoveredCases = response.json()["Countries"][121]["TotalRecovered"]
NewRecoveredCases = response.json()["Countries"][121]["NewRecovered"]

#---------------Global Cases --------------------------------------------
TotalConfirmedCasesG = response.json()["Global"]["TotalConfirmed"]
NewConfirmetdCasesG = response.json()["Global"]["NewConfirmed"]
TotalDeathCasesG = response.json()["Global"]["TotalDeaths"]
NewDeathCasesG = response.json()["Global"]["NewDeaths"]
TotalRecoveredCasesG = response.json()["Global"]["TotalRecovered"]
NewRecoveredCasesG = response.json()["Global"]["NewRecovered"]
