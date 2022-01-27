# This files contains your custom actions which can be used to run
# custom Python code.
#
# See this guide on how to implement these action:
# https://rasa.com/docs/rasa/custom-actions


# This is a simple example for a custom action which utters "Hello World!"

# from typing import Any, Text, Dict, List
#
# from rasa_sdk import Action, Tracker
# from rasa_sdk.executor import CollectingDispatcher
#
#
# class ActionHelloWorld(Action):
#
#     def name(self) -> Text:
#         return "action_hello_world"
#
#     def run(self, dispatcher: CollectingDispatcher,
#             tracker: Tracker,
#             domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
#
#         dispatcher.utter_message(text="Hello World!")
#
#         return []

loc = ["Pokhara","Damauli","Biratnagar","Nepal","Ktm","Syangja"]

from typing import Any, Text, Dict, List
from rasa_sdk import Action, Tracker
from rasa_sdk.executor import CollectingDispatcher
from rasa_sdk.events import SlotSet, FollowupAction
from utils.time_extractor import time_extract
from rasa_sdk.events import AllSlotsReset

from utils.logging import log_setup
from utils.database_connector import *

logger=log_setup()
import requests

response = requests.get("https://api.covid19api.com/summary")


CountryWiseData = response.json()["Countries"][121]
print(CountryWiseData)
LatestUpdatedDate = response.json()["Countries"][121]["Date"][0:10]
TotalConfirmedCases = response.json()["Countries"][121]["TotalConfirmed"]
NewConfirmetdCases = response.json()["Countries"][121]["NewConfirmed"]
TotalDeathCases = response.json()["Countries"][121]["TotalDeaths"]
NewDeathCases = response.json()["Countries"][121]["NewDeaths"]
TotalRecoveredCases = response.json()["Countries"][121]["TotalRecovered"]
NewRecoveredCases = response.json()["Countries"][121]["NewRecovered"]

#---------------------------------- APPOINTMENT ENTRY START----------------------------------------------------
class ActionAppointment1(Action):
    def name(self) -> Text:
        return "action_appointment_bot_activate1"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        if tracker.latest_message['text']=="from the appointment button":
            dispatcher.utter_message(f"{time_extract()}! Please tell your first name")
            logger.info(f"--------------------------------------------------------------------------------------")
            logger.info(f"--------------------------------------------------------------------------------------")
            logger.info(f"NEW USER INITIALIZED")
            logger.info("appointment form activated")
            return [SlotSet("appointment_activate","activated")]
        if tracker.get_slot("appointment_activate")=="activated":
            dispatcher.utter_message("Please tell your last name")
            logger.info(f"first name {tracker.latest_message['text']} accepted")
            return [SlotSet("app_first_name",tracker.latest_message['text']),SlotSet("appointment_activate",None)]
        return []

class ActionAppointment2(Action):
    def name(self) -> Text:
        return "action_appointment_bot_activate2"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        if tracker.get_slot("appointment_activate") is None:
            dispatcher.utter_message("Please give your address.")
            logger.info(f"last name {tracker.latest_message['text']} accepted")
            return [SlotSet("app_last_name",tracker.latest_message['text']),SlotSet("appointment_activate","not none")]
        if tracker.get_slot("appointment_activate") is not None:
            dispatcher.utter_message("Please write the date you want to book appointment on.")
            logger.info(f"address {tracker.latest_message['text']} accepted")
            return [SlotSet("app_address",tracker.latest_message['text']),SlotSet("appointment_activate",None)]
        return []


class ActionAppointment3(Action):
    def name(self) -> Text:
        return "action_appointment_bot_activate3"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        if tracker.get_slot("appointment_activate") is None:
            dispatcher.utter_message("Please give your contact number.")
            logger.info(f"appointment date {tracker.latest_message['text']} accepted")
            return [SlotSet("app_date",tracker.latest_message['text']),SlotSet("appointment_activate","not none")]
        if tracker.get_slot("appointment_activate") is not None:
            buttons = [{"title": "Yes", "payload": "proceed further"},
            {"title": "No","payload": "dont proceed further"}]
            dispatcher.utter_message(text="Do you want to proceed further?", buttons=buttons)
            logger.info(f"contact number {tracker.latest_message['text']} accepted")
            return [SlotSet("app_contact_number",tracker.latest_message['text']),SlotSet("appointment_activate",None)]
        return []



class ActionAppointment4(Action):
    def name(self) -> Text:
        return "action_appointment_proceed"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        if tracker.get_slot("appointment_activate") is None:
            if tracker.latest_message['text']=="proceed further":
                dispatcher.utter_message(f"Your first name: {tracker.get_slot('app_first_name')}")
                dispatcher.utter_message(f"Your last name: {tracker.get_slot('app_last_name')}")
                dispatcher.utter_message(f"Your address: {tracker.get_slot('app_address')}")
                dispatcher.utter_message(f"Your appointment date: {tracker.get_slot('app_date')}")
                dispatcher.utter_message(f"Your contact number: {tracker.get_slot('app_contact_number')}")
                logger.info("appointment data dumped")
#                appointment_entry_dumper("bishab","pokharel","biratnagar","tomorrow","986665544")
                appointment_entry_dumper(tracker.get_slot("app_first_name"),tracker.get_slot("app_last_name"),\
                    tracker.get_slot("app_address"),tracker.get_slot("app_date"),tracker.get_slot("app_contact_number"))
                logger.info(f"Slots reset done")
                logger.info(f"--------------------------------------------------------------------------------------")
                logger.info(f"--------------------------------------------------------------------------------------")
                return [AllSlotsReset()]
            if tracker.latest_message['text']=="dont proceed further":
                dispatcher.utter_message("The data are reset. Please restart with registration")
                appointment_table_creator()
                logger.info("appointment data not dumped")
                logger.info(f"Slots reset done")
                logger.info(f"--------------------------------------------------------------------------------------")
                logger.info(f"--------------------------------------------------------------------------------------")
                return [AllSlotsReset()]
#                return [SlotSet("app_first_name",None),SlotSet("app_last_name",None),\
#                    SlotSet("app_address",None),SlotSet("appointment_activate",None)]
        return []


#---------------------------------- APPOINTMENT ENTRY END----------------------------------------------------

#---------------------------------- APPOINTMENT REMOVAL START----------------------------------------------------
class ActionAppointmentRemoval(Action):
    def name(self) -> Text:
        return "action_appointment_removal"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        if tracker.latest_message['text']=="from the appointment removal button":
            dispatcher.utter_message(" Please write the word 'remove' with your contact number")
            logger.info("Appointment removal option triggered")
            return [SlotSet("appointment_activate","activated")]
        if tracker.get_slot("appointment_activate")=="activated":
            tracker.latest_message['text']=tracker.latest_message['text'].replace("remove ","")
            appointment_entry_deletor(tracker.latest_message['text'])
            logger.info("Appointment data removed from the database")
            dispatcher.utter_message(f"Your data is removed")
            return [SlotSet("appointment_activate",None)]
        return []

#---------------------------------- APPOINTMENT REMOVAL END----------------------------------------------------


class ActionCovidBot(Action):
    def name(self) -> Text:
        return "action_covid_bot"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        if (tracker.get_slot("fully_vaccinated")==None) or (tracker.get_slot("next_question")=="new symptoms activated"):
            buttons = [{"title": "Yes", "payload": "Fully Vaccination Pos"},
            {"title": "No", "payload": "Fully Vaccination Neg"}]
            dispatcher.utter_message(text="Are you fully vaccinated against COVID-19?", buttons=buttons)
            return [SlotSet('fully_vaccinated',tracker.latest_message['text']),SlotSet("next_question",None)]
        if any(substring in tracker.latest_message["text"] for substring in ["Fully Vaccination Pos","Fully Vaccination Neg"]):
            buttons = [{"title": "Yes", "payload": "Covid Symptoms Pos"},
            {"title": "No", "payload": "Covid Symptoms Neg"}]
            dispatcher.utter_message("The following are the probable symptoms of Covid-19:")
            dispatcher.utter_message("Severe difficulty in breathing")
            dispatcher.utter_message("Severe chest pain")
            dispatcher.utter_message("Losing consciousness")
            dispatcher.utter_message("Strong headache")
            dispatcher.utter_message(text="Are you currently struggling with any of these issues?", buttons=buttons)
            return [SlotSet("fully_vaccinated",tracker.latest_message['text'])]

        if tracker.latest_message['text']=="Covid Symptoms Pos":
            dispatcher.utter_message("Based on your answers, we recommend you to self-isolate yourself at home or at nearest isolation centre.")
            dispatcher.utter_message("Please follow the below guidelines issued by the government:")
            buttons = [{"title": "Yes", "payload": "check for other symptoms pos"},
            {"title": "No", "payload": "check for other symptoms neg"}]
            dispatcher.utter_message("Do you want to check for other symptoms?", buttons=buttons)
            return [SlotSet("initial_covid_symptoms",tracker.latest_message['text']),SlotSet("final_covid_assessment_outcome","most probable for covid")]

        if tracker.latest_message['text']=="Covid Symptoms Neg":
            buttons = [{"title": "Fever and/or chills", "payload": "fever chills symptoms"},
            {"title": "Cough or barking cough", "payload": "cough or barking cough"},
            {"title": "Shortness of breath", "payload": "shortness of breath"},
            {"title": "Decrease or loss of taste or smell", "payload": "Decrease or loss of taste or smell"},
            {"title": "Muscle aches or joint pain", "payload": "Muscle aches or joint pain"},
            {"title": "Extreme tiredness", "payload": "Extreme tiredness"},
            {"title": "Sore throat", "payload": "Sore throat"},
            {"title": "Runny, congested or stuffed nose", "payload": "Runny, congested or stuffed nose"},
            {"title": "Headache", "payload": "Headache"},
            {"title": "Nausea, Vomiting or diarrhea", "payload": "Nausea, Vomiting or diarrhea"},
            {"title": "None of these symptoms", "payload": "does not match"}]
            dispatcher.utter_message(text="Are you currently experiencing any of these symptoms?", buttons=buttons)
            return [SlotSet("initial_covid_symptoms",tracker.latest_message['text'])]

        if any(substring in tracker.latest_message["text"] for substring in ["fever chills symptoms",\
            "cough or barking cough","shortness of breath","Decrease or loss of taste or smell"\
                ,"Muscle aches or joint pain","Extreme tiredness","Sore throat","Runny, congested or stuffed nose",\
                    "Headache","Nausea, Vomiting or diarrhea"]):
            dispatcher.utter_message("Based on your answers, we recommend you to self-isolate yourself at home or at nearest isolation centre.")
            dispatcher.utter_message("Please follow the below guidelines issued by the government:")
            buttons = [{"title": "Yes", "payload": "check for other symptoms pos"},
            {"title": "No", "payload": "check for other symptoms neg"}]
            dispatcher.utter_message("Do you want to check for other symptoms?", buttons=buttons)
            return [SlotSet("probable_covid_symptoms",tracker.latest_message['text']),SlotSet("final_covid_assessment_outcome","most probable for covid")]
        
        if tracker.latest_message=="does not match":
            buttons = [{"title": "been sick with symptoms like COVID-19?", "payload": "close relative with covid symptoms"},
            {"title": "tested positive for COVID-19?", "payload": "living with covid patient"}]
            dispatcher.utter_message(text="In the last fifteen days, has someone live you with...", buttons=buttons)
            return [SlotSet("probable_covid_symptoms",tracker.latest_message['text'])]

        if tracker.latest_message=="living with covid patient":
            dispatcher.utter_message("Based on your answers, we recommend you to self-isolate yourself at home or at nearest isolation centre.")
            dispatcher.utter_message("Please follow the below guidelines issued by the government:")
            buttons = [{"title": "Yes", "payload": "check for other symptoms pos"},
            {"title": "No", "payload": "check for other symptoms neg"}]
            dispatcher.utter_message("Do you want to check for other symptoms?", buttons=buttons)
            return [SlotSet("family_covid_history",tracker.latest_message['text']),SlotSet("final_covid_assessment_outcome","most probable for covid")]
        
        if tracker.latest_message=="close relative with covid symptoms":
            buttons = [{"title": "Yes", "payload": "tested positive on rapid antigen test"},
            {"title": "No", "payload": "no rapid antigen positive test occured"}]
            dispatcher.utter_message(text="In the last ten days, have you tasted positive with rapid antigen test or home based self testing kit?", buttons=buttons)
            return [SlotSet("family_covid_history",tracker.latest_message['text'])]
            
        if tracker.latest_message=="tested positive on rapid antigen test":
            dispatcher.utter_message("Based on your answers, we recommend you to self-isolate yourself at home or at nearest isolation centre.")
            dispatcher.utter_message("Please follow the below guidelines issued by the government:")
            buttons = [{"title": "Yes", "payload": "check for other symptoms pos"},
            {"title": "No", "payload": "check for other symptoms neg"}]
            dispatcher.utter_message("Do you want to check for other symptoms?", buttons=buttons)
            return [SlotSet("rapid_antigen_test_result",tracker.latest_message['text']),SlotSet("final_covid_assessment_outcome","most probable for covid")]
        
        if tracker.latest_message=="no rapid antigen positive test occured":
            buttons = [{"title": "Yes", "payload": "visited covid exposed places"},
            {"title": "No", "payload": "not visited covid exposed places"}]
            dispatcher.utter_message(text="Do you remember visiting Covid-19 Exposure Alert places in recent ten days?", buttons=buttons)
            return [SlotSet("rapid_antigen_test_result",tracker.latest_message['text'])]

        if tracker.latest_message=="visited covid exposed places":
            dispatcher.utter_message("Based on your answers, we recommend you to self-isolate yourself at home or at nearest isolation centre.")
            dispatcher.utter_message("Please follow the below guidelines issued by the government:")
            buttons = [{"title": "Yes", "payload": "check for other symptoms pos"},
            {"title": "No", "payload": "check for other symptoms neg"}]
            dispatcher.utter_message("Do you want to check for other symptoms?", buttons=buttons)
            return [SlotSet("covid_exposed_place_visit",tracker.latest_message['text']),SlotSet("final_covid_assessment_outcome","most probable for covid")]
        
        if tracker.latest_message=="not visited covid exposed places":
            buttons = [{"title": "Yes", "payload": "close contact of covid patient pos"},
            {"title": "No", "payload": "close contact of covid patient neg"}]
            dispatcher.utter_message(text="In the last ten days, have you been recognized as a 'Close Contact' of someone who currently has COVID-19?", buttons=buttons)
            return [SlotSet("covid_exposed_place_visit",tracker.latest_message['text'])]

        if tracker.latest_message=="close contact of covid patient pos":
            dispatcher.utter_message("Based on your answers, we recommend you to self-isolate yourself at home or at nearest isolation centre.")
            dispatcher.utter_message("Please follow the below guidelines issued by the government:")
            buttons = [{"title": "Yes", "payload": "check for other symptoms pos"},
            {"title": "No", "payload": "check for other symptoms neg"}]
            dispatcher.utter_message("Do you want to check for other symptoms?", buttons=buttons)
            return [SlotSet("close_contact_with_patient",tracker.latest_message['text']),SlotSet("final_covid_assessment_outcome","most probable for covid")]

        if tracker.latest_message=="close contact of covid patient neg":
            buttons = [{"title": "Yes", "payload": "travelled abroad pos"},
            {"title": "No", "payload": "travelled abroad neg"}]
            dispatcher.utter_message(text="Have you travelled outside of the country in the last 14 days?", buttons=buttons)
            return [SlotSet("close_contact_with_patient",tracker.latest_message['text'])]
        
        if tracker.latest_message=="travelled abroad pos":
            dispatcher.utter_message("Based on your answers, we recommend you to self-isolate yourself at home or at nearest isolation centre.")
            dispatcher.utter_message("Please follow the below guidelines issued by the government:")
            buttons = [{"title": "Yes", "payload": "check for other symptoms pos"},
            {"title": "No", "payload": "check for other symptoms neg"}]
            dispatcher.utter_message("Do you want to check for other symptoms?", buttons=buttons)
            return [SlotSet("travelled_abroad_recently",tracker.latest_message['text']),SlotSet("final_covid_assessment_outcome","most probable for covid")]

        if tracker.latest_message=="travelled abroad neg":
            dispatcher.utter_message("Based on your answers, you do not need to self-isolate or get tested")
            dispatcher.utter_message("If you feel sick or not well, please stay home until symptoms improve for at least 24 hours or 48 hours if you have gastrointestinal symptoms (nausea, vomiting, diarrhea). Your household members don't need to self-isolate. Talk with a doctor if necessary.")
            dispatcher.utter_message("To protect your community and the health care system, wear a face covering or mask when required, keep a physical distance from others, and wash your hands as much as possible..")
            dispatcher.utter_message("Please follow the below guidelines issued by the government:")
            buttons = [{"title": "Yes", "payload": "check for other symptoms pos"},
            {"title": "No", "payload": "check for other symptoms neg"}]
            dispatcher.utter_message("Do you want to check for other symptoms?", buttons=buttons)
            return [SlotSet("travelled_abroad_recently",tracker.latest_message['text']),SlotSet("final_covid_assessment_outcome","most probably safe from covid")]

        if tracker.latest_message=="check for other symptoms pos":
            dispatcher.utter_message("Hello there! Please tell me your symptoms")
            return [SlotSet("next_question","new symptoms activated")]

        if tracker.latest_message=="check for other symptoms neg":
            dispatcher.utter_message("Thank you so much for the interaction. Taking you back to the menu.")
            dispatcher.utter_message("Hi! What can I do for you?")
            return [SlotSet("next_question","new symptoms disactivate")]

        #Taking user back to the beginning of the chat in case user inputs any strange text.
        else:
            print(tracker.latest_message['text'])
            dispatcher.utter_message("Sorry, I didn't understand that.")
            dispatcher.utter_message("Taking you back to the beginning of the chat...")
            dispatcher.utter_message("Hello there!")
            return [SlotSet("next_question","new symptoms activated")]

        return []


#---------------------------------- BLOOD BOT START----------------------------------------------------

class ActionAskBlood(Action):

    def name(self) -> Text:
        return "asking_for_blood"

    def run(self, dispatcher: CollectingDispatcher,
             tracker: Tracker,
             domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
             
        logger.info(f"--------------------------------------------------------------------------------------")
        logger.info(f"--------------------------------------------------------------------------------------")
        logger.info(f"Blood Bank Details Activated")

        if tracker.get_slot("looking_for_blood") is None:
            if tracker.get_slot("blood_location") is None:
                logger.info("Looking for Blood is Activated")
                buttons = [ {"title": "Yes", "payload": "Looking For Blood"},
                {"title": "NO", "payload": "Not looking For Blood"}]
                dispatcher.utter_message(text = "Are you Looking For Blood Bank", buttons = buttons)        
                return [SlotSet("looking_for_blood", "Yes")]   
            
            return [SlotSet("looking_for_blood", "Yes"), FollowupAction("ask_location")]


        if tracker.latest_message['text'] == "Looking For Blood":
            if tracker.get_slot("blood_location") is None:
                logger.info("Looking for Blood Address")
                dispatcher.utter_message("Please Provide your Locataion:")
        else:
            return [FollowupAction("ask_location")]
        return [SlotSet("looking_for_blood", "Yes")]


        return []

    def myname(self, dispatcher: CollectingDispatcher,
             tracker: Tracker,
             domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        return []
            


class ActionAskLoc(Action):

    def name(self) -> Text:
        return "ask_location"

    def run(self, dispatcher: CollectingDispatcher,
             tracker: Tracker,
             domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:

        SlotSet("blood_location",tracker.latest_message['text'])
        x = tracker.get_slot("blood_location")
        if tracker.get_slot("looking_for_blood") == "Yes" and tracker.get_slot("blood_location") is not None:
            if x in loc:
                #here we fetch data from Json file and put that data or share link with them
                logger.info("Details shown")
                dispatcher.utter_message("Ganesh Blood Bank")
                dispatcher.utter_message("Bishab Blood Bank")
                dispatcher.utter_message("Bigyan Blood Bank")
                dispatcher.utter_message("Gigyan Blood Bank")
                return [SlotSet("blood_location",None),
                SlotSet("looking_for_blood",None) ]

            else:
                dispatcher.utter_message("Blood is not available in your Location")   
        if tracker.get_slot("looking_for_blood") is None:
            logger.info("Looking For Hospital or Blood Bank?")
            buttons = [ {"title": "Blood Bank", "payload": "Looking For Blood"},
            {"title": "Hospital", "payload": "Not looking For Blood"}]
            dispatcher.utter_message(text = "What are you Looking For?", buttons = buttons)        
            return [SlotSet("looking_for_blood","Maybe")]
        if tracker.latest_message['text'] == "Looking For Blood":
            logger.info("Looking for Blood But Address is not Provided")
            if tracker.get_slot("blood_location") is None:
                return [SlotSet("looking_for_blood", "Yes"), FollowupAction("asking_for_blood")]
                    
            else:
                userLoc = tracker.get_slot("blood_location")
                logger.info("User is Looking For Blood Bank at ")
                dispatcher.utter_message(f"Ohh! You are Looking For Blood Bank at {userLoc}")
                return [SlotSet("looking_for_blood", "Yes"), FollowupAction("ask_location")]


        logger.info("Blood Bank Details Ended Sucessfully")
        logger.info(f"--------------------------------------------------------------------------------------")
        logger.info(f"--------------------------------------------------------------------------------------")
        

        return [SlotSet("blood_location",None),
        SlotSet("looking_for_blood",None) ]  

#---------------------------------- BLOOD BOT END----------------------------------------------------

#-----------------------------------COVID19 CASES DETAILS -------------------------------------------
class ActionCovidData(Action):

    def name(self) -> Text:
        return "showing_covid_data"

    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
             domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:

        dispatcher.utter_message(text=f"According to {LatestUpdatedDate}")
        dispatcher.utter_message(text=f"Total Confirmed Cases: {TotalConfirmedCases}")
        dispatcher.utter_message(text=f"New Confirmed Cases: {NewConfirmetdCases}")
        dispatcher.utter_message(text=f"Total Death: {TotalDeathCases}")
        dispatcher.utter_message(text=f"New Death:  {NewDeathCases}")
        dispatcher.utter_message(text=f"Total Recovered: {TotalRecoveredCases}")
        dispatcher.utter_message(text=f"New Recovered:  {NewRecoveredCases}")

        return []