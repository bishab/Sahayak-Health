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

from datetime import time
from typing import Any, Text, Dict, List
from rasa_sdk import Action, Tracker
from rasa_sdk.executor import CollectingDispatcher
from rasa_sdk.events import SlotSet, FollowupAction
from utils.time_extractor import time_extract
from rasa_sdk.events import AllSlotsReset

from utils.logging import log_setup
from utils.database_connector import *
from utils.location_fetch import *
from utils.api_fetch import *
logger=log_setup()


class ActionGreetUser(Action):

    def name(self) -> Text:
        return "action_user_greet"

    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:

        dispatcher.utter_message(f"{time_extract()} How may I help you?")

        return []

#---------------------------------- APPOINTMENT AND REGISTRATION CHECK START----------------------------------------------------

class ActionAppointmentCheck(Action):
    def name(self) -> Text:
        return "action_appointment_check"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        if tracker.get_slot("check_for_appointment") is None:
            dispatcher.utter_message("Please provide your email address")
            return [SlotSet("check_for_appointment","changed")]
        else:
            data=view_patient_reg(tracker.latest_message['text'])
            if data=="No record":
                dispatcher.utter_message("You have not registered yet. Please register yourself first.")
                logger.info("User has not registered yet.")
                return [SlotSet("check_for_appointment",None)]
            else:
                data=view_patient_appointment(tracker.latest_message['text'])
                if data=="No record":
                    dispatcher.utter_message("You do not have any appointments booked as of now. Please proceed to My Appointments section to book one.")
                    logger.info("User has not booked any appointments.")
                    return [SlotSet("check_for_appointment",None)]
                else:
                    dispatcher.utter_message(f"Below are your appointment details.")
                    dispatcher.utter_message(f"Hospital: {data['hospital']}\nDepartment: {data['department']}\nDate: {data['date']}\nTime: {data['time']}\n")
                    logger.info("Appointment data checked in the database")
                    dispatcher.utter_message("Taking you back to the menu...")
                    dispatcher.utter_message(f"{time_extract()}! what can I do for you?")
                    return [SlotSet("check_for_appointment",None)]

#---------------------------------- APPOINTMENT AND REGISTRATION CHECK END----------------------------------------------------

#---------------------------------- COVID SELF ASSESSMENT BOT START----------------------------------------------------
class ActionCovidBotOne(Action):
    def name(self) -> Text:
        return "action_covid_bot_begin"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
#        if tracker.latest_message['text']=="check for covid self assessment":
#        if (tracker.get_slot("fully_vaccinated")==None) or (tracker.get_slot("next_question")=="new symptoms activated"):
        if tracker.get_slot("appointment_activate") is None:
            buttons = [{"title": "Yes", "payload": "Fully Vaccination Pos"},
            {"title": "No", "payload": "Fully Vaccination Neg"}]
            dispatcher.utter_message(f"Hello there. {time_extract()} Please help me by answering few questions I am going to ask. This is for your self-assessment of Covid-19.")
            dispatcher.utter_message(text="Are you fully vaccinated against COVID-19?", buttons=buttons)
            return [SlotSet("appointment_activate","not none")]
#            return [SlotSet('fully_vaccinated',tracker.latest_message['text']),SlotSet("next_question",None)]

#        if any(substring in tracker.latest_message["text"] for substring in ["Fully Vaccination Pos","Fully Vaccination Neg"]):
        if tracker.get_slot("appointment_activate") is not None:
            buttons = [{"title": "Yes", "payload": "Covid Symptoms Pos"},
            {"title": "No", "payload": "Covid Symptoms Neg"}]
            dispatcher.utter_message("The following are the probable symptoms of Covid-19:")
            dispatcher.utter_message("Severe difficulty in breathing")
            dispatcher.utter_message("Severe chest pain")
            dispatcher.utter_message("Losing consciousness")
            dispatcher.utter_message("Strong headache")
            dispatcher.utter_message(text="Are you currently struggling with any of these issues?", buttons=buttons)
            return [SlotSet("fully_vaccinated",tracker.latest_message['text']),SlotSet("appointment_activate",None)]



class ActionCovidSymptomsBot(Action):
    def name(self) -> Text:
        return "action_covid_symptoms_bot"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        if tracker.get_slot("appointment_activate") is None:
            if tracker.latest_message['text']=="Covid Symptoms Pos":
                dispatcher.utter_message("Based on your answers, we recommend you to self-isolate yourself at home or at nearest isolation centre.")
                dispatcher.utter_message("Please follow the below guidelines issued by the government:")
                buttons = [{"title": "Yes", "payload": "check for other symptoms pos"},
                {"title": "No", "payload": "check for other symptoms neg"}]
                dispatcher.utter_message("Do you want to check for other symptoms?", buttons=buttons)
                return [SlotSet("initial_covid_symptoms",tracker.latest_message['text']),SlotSet("appointment_activate","not none")]
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
                {"title": "None of these symptoms", "payload": "none of these symptoms"}]
                dispatcher.utter_message(text="Are you currently experiencing any of these symptoms?", buttons=buttons)
                return [SlotSet("initial_covid_symptoms",tracker.latest_message['text']),SlotSet("appointment_activate","not none")]

        if tracker.get_slot("appointment_activate") is not None:
            if any(substring in tracker.latest_message["text"] for substring in ["fever chills symptoms",\
                "cough or barking cough","shortness of breath","Decrease or loss of taste or smell"\
                    ,"Muscle aches or joint pain","Extreme tiredness","Sore throat","Runny, congested or stuffed nose",\
                        "Headache","Nausea, Vomiting or diarrhea"]):
                dispatcher.utter_message("Based on your answers, we recommend you to self-isolate yourself at home or at nearest isolation centre.")
                dispatcher.utter_message("Please follow the below guidelines issued by the government:")
                buttons = [{"title": "Yes", "payload": "check for other symptoms pos"},
                {"title": "No", "payload": "check for other symptoms neg"}]
                dispatcher.utter_message("Do you want to check for other symptoms?", buttons=buttons)
                return [SlotSet("probable_covid_symptoms",tracker.latest_message['text']),SlotSet("appointment_activate",None),SlotSet("final_covid_assessment_outcome","Probable for Covid-19")]
        
            if tracker.latest_message['text']=="none of these symptoms":
                buttons = [{"title": "been sick with symptoms like COVID-19?", "payload": "close relative with covid symptoms"},
                {"title": "tested positive for COVID-19?", "payload": "living with covid patient"}]
                dispatcher.utter_message(text="In the last fifteen days, has someone live you with...", buttons=buttons)
                return [SlotSet("probable_covid_symptoms",tracker.latest_message['text']),SlotSet("appointment_activate",None)]

class ActionCovidSymptomsBotTwo(Action):
    def name(self) -> Text:
        return "action_covid_symptoms_bot_two"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        if tracker.get_slot("appointment_activate") is None:
            if tracker.latest_message['text']=="living with covid patient":
                dispatcher.utter_message("Based on your answers, we recommend you to self-isolate yourself at home or at nearest isolation centre.")
                dispatcher.utter_message("Please follow the below guidelines issued by the government:")
                buttons = [{"title": "Yes", "payload": "check for other symptoms pos"},
                {"title": "No", "payload": "check for other symptoms neg"}]
                dispatcher.utter_message("Do you want to check for other symptoms?", buttons=buttons)
                return [SlotSet("family_covid_history",tracker.latest_message['text']),SlotSet("final_covid_assessment_outcome","Probable for Covid-19")]
        
            if tracker.latest_message['text']=="close relative with covid symptoms":
                buttons = [{"title": "Yes", "payload": "tested positive on rapid antigen test"},
                {"title": "No", "payload": "no rapid antigen positive test occured"}]
                dispatcher.utter_message(text="In the last ten days, have you tasted positive with rapid antigen test or home based self testing kit?", buttons=buttons)
                return [SlotSet("family_covid_history",tracker.latest_message['text']),SlotSet("appointment_activate","not none")]
         

        if tracker.get_slot("appointment_activate") is not None:
            if tracker.latest_message['text']=="tested positive on rapid antigen test":
                dispatcher.utter_message("Based on your answers, we recommend you to self-isolate yourself at home or at nearest isolation centre.")
                dispatcher.utter_message("Please follow the below guidelines issued by the government:")
                buttons = [{"title": "Yes", "payload": "check for other symptoms pos"},
                {"title": "No", "payload": "check for other symptoms neg"}]
                dispatcher.utter_message("Do you want to check for other symptoms?", buttons=buttons)
                return [SlotSet("rapid_antigen_test_result",tracker.latest_message['text']),SlotSet("appointment_activate",None),SlotSet("final_covid_assessment_outcome","Probable for Covid-19")]
            
            if tracker.latest_message['text']=="no rapid antigen positive test occured":
                buttons = [{"title": "Yes", "payload": "visited covid exposed places"},
                {"title": "No", "payload": "not visited covid exposed places"}]
                dispatcher.utter_message(text="Do you remember visiting Covid-19 Exposure Alert places in recent ten days?", buttons=buttons)
                return [SlotSet("rapid_antigen_test_result",tracker.latest_message['text']),SlotSet("appointment_activate",None)]

class ActionCovidSymptomsBotThree(Action):
    def name(self) -> Text:
        return "action_covid_symptoms_bot_three"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        if tracker.get_slot("appointment_activate") is None:
            if tracker.latest_message['text']=="visited covid exposed places":
                dispatcher.utter_message("Based on your answers, we recommend you to self-isolate yourself at home or at nearest isolation centre.")
                dispatcher.utter_message("Please follow the below guidelines issued by the government:")
                buttons = [{"title": "Yes", "payload": "check for other symptoms pos"},
                {"title": "No", "payload": "check for other symptoms neg"}]
                dispatcher.utter_message("Do you want to check for other symptoms?", buttons=buttons)
                return [SlotSet("covid_exposed_place_visit",tracker.latest_message['text']),SlotSet("appointment_activate","not none"),SlotSet("final_covid_assessment_outcome","Probable for Covid-19")]
            
            if tracker.latest_message['text']=="not visited covid exposed places":
                buttons = [{"title": "Yes", "payload": "close contact of covid patient pos"},
                {"title": "No", "payload": "close contact of covid patient neg"}]
                dispatcher.utter_message(text="In the last ten days, have you been recognized as a 'Close Contact' of someone who currently has COVID-19?", buttons=buttons)
                return [SlotSet("covid_exposed_place_visit",tracker.latest_message['text']),SlotSet("appointment_activate","not none")]

        if tracker.get_slot("appointment_activate") is not None:
            if tracker.latest_message['text']=="close contact of covid patient pos":
                dispatcher.utter_message("Based on your answers, we recommend you to self-isolate yourself at home or at nearest isolation centre.")
                dispatcher.utter_message("Please follow the below guidelines issued by the government:")
                buttons = [{"title": "Yes", "payload": "check for other symptoms pos"},
                {"title": "No", "payload": "check for other symptoms neg"}]
                dispatcher.utter_message("Do you want to check for other symptoms?", buttons=buttons)
                return [SlotSet("close_contact_with_patient",tracker.latest_message['text']),SlotSet("appointment_activate",None),SlotSet("final_covid_assessment_outcome","Probable for Covid-19")]

            if tracker.latest_message['text']=="close contact of covid patient neg":
                buttons = [{"title": "Yes", "payload": "travelled abroad pos"},
                {"title": "No", "payload": "travelled abroad neg"}]
                dispatcher.utter_message(text="Have you travelled outside of the country in the last 14 days?", buttons=buttons)
                return [SlotSet("close_contact_with_patient",tracker.latest_message['text']),SlotSet("appointment_activate",None)]

class ActionCovidSymptomsBotFour(Action):
    def name(self) -> Text:
        return "action_covid_symptoms_bot_four"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        if tracker.get_slot("appointment_activate") is None:
            if tracker.latest_message['text']=="travelled abroad pos":
                dispatcher.utter_message("Based on your answers, we recommend you to self-isolate yourself at home or at nearest isolation centre.")
                dispatcher.utter_message("Please follow the below guidelines issued by the government:")
                buttons = [{"title": "Yes", "payload": "check for other symptoms pos"},
                {"title": "No", "payload": "check for other symptoms neg"}]
                dispatcher.utter_message("Do you want to check for other symptoms?", buttons=buttons)
                return [SlotSet("travelled_abroad_recently",tracker.latest_message['text']),SlotSet("appointment_activate","not none"),SlotSet("final_covid_assessment_outcome","Probable for Covid-19")]
            if tracker.latest_message['text']=="travelled abroad neg":
                dispatcher.utter_message("Based on your answers, you do not need to self-isolate or get tested")
                dispatcher.utter_message("If you feel sick or not well, please stay home until symptoms improve for at least 24 hours or 48 hours if you have gastrointestinal symptoms (nausea, vomiting, diarrhea). Your household members don't need to self-isolate. Talk with a doctor if necessary.")
                dispatcher.utter_message("To protect your community and the health care system, wear a face covering or mask when required, keep a physical distance from others, and wash your hands as much as possible..")
                dispatcher.utter_message("Please follow the below guidelines issued by the government:")
                buttons = [{"title": "Yes", "payload": "check for other symptoms pos"},
                {"title": "No", "payload": "check for other symptoms neg"}]
                dispatcher.utter_message("Do you want to check for other symptoms?", buttons=buttons)
                return [SlotSet("travelled_abroad_recently",tracker.latest_message['text']),SlotSet("appointment_activate","not none"),SlotSet("final_covid_assessment_outcome","Less Probable for Covid-19")]


        return []
#---------------------------------- COVID SELF ASSESSMENT BOT END----------------------------------------------------

class ActionCovidSymptomsBotFour(Action):
    def name(self) -> Text:
        return "action_other_symptoms"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        if tracker.latest_message['text']=="check for other symptoms pos":
            dispatcher.utter_message("Hello there! Please tell me your symptoms")
            return [SlotSet("next_question","new symptoms activated")]

        if tracker.latest_message['text']=="check for other symptoms neg":
            dispatcher.utter_message("Thank you so much for the interaction. Taking you back to the menu.")
            dispatcher.utter_message("Hi! What can I do for you?")
            return [SlotSet("next_question","new symptoms disactivate")]

        #Taking user back to the beginning of the chat in case user inputs any strange text.
        else:
            print(tracker.latest_message['text'])
            dispatcher.utter_message("Sorry, I didn't understand that.")
            return [SlotSet("next_question","new symptoms activated"), FollowupAction("action_farewall")]




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
                return [SlotSet("looking_for_blood", "Looking For Blood")]   
            
            return [SlotSet("looking_for_blood", "Looking For Blood"), FollowupAction("ask_location")]
        print("I am invisible")

        if tracker.latest_message['text'] == "Looking For Blood":
            if tracker.get_slot("blood_location") is None:
                logger.info("Looking for Blood Address")
                dispatcher.utter_message("Please Provide your Location:")
        else:
            return [FollowupAction("ask_location")]
        return [SlotSet("looking_for_blood", "Looking For Blood")]


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
        if tracker.get_slot("looking_for_blood") == "Looking For Blood" and tracker.get_slot("blood_location") is not None:
            if x in loc:
                #here we fetch data from Json file and put that data or share link with them
                logger.info("Details shown")
                dispatcher.utter_message("Maitidevi Blood Bank")
                dispatcher.utter_message("Bishab Blood Bank")
                dispatcher.utter_message("Bigyan Blood Bank")
                dispatcher.utter_message("Gigyan Blood Bank")
                dispatcher.utter_message("Taking you back to the beginning of the chat...")
                dispatcher.utter_message("Hello there! What can I do for you?")
                return [SlotSet("blood_location",None),
                SlotSet("looking_for_blood",None) ]
            else:
                dispatcher.utter_message("Blood is not available in your Location")   
                dispatcher.utter_message("Taking you back to the beginning of the chat...")
                dispatcher.utter_message("Hello there! What can I do for you?")

        if tracker.get_slot("looking_for_blood") is None:
            logger.info("Looking For Hospital or Blood Bank?")
            buttons = [ {"title": "Blood Bank", "payload": "Looking For Blood"},
            {"title": "Hospital", "payload": "Not looking For Blood"}]
            dispatcher.utter_message(text = "What are you Looking For?", buttons = buttons)        
            return [SlotSet("looking_for_blood","Maybe")]
        if tracker.latest_message['text'] == "Looking For Blood":
            logger.info("Looking for Blood But Address is not Provided")
            if tracker.get_slot("blood_location") is None:
                return [SlotSet("looking_for_blood", "Looking For Blood"), FollowupAction("asking_for_blood")]
            else:
                userLoc = tracker.get_slot("blood_location")
                logger.info("User is Looking For Blood Bank at ")
                dispatcher.utter_message(f"Ohh! You are Looking For Blood Bank at {userLoc}")
                return [SlotSet("looking_for_blood", "Looking For Blood"), FollowupAction("ask_location")]


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
        logger.info("user location being fetched")
        user_country=find_location()
        logger.info(f"user location being fetched as {user_country}")
        dispatcher.utter_message(f"Your country is {user_country}. Showing covid details for {user_country}:")
        dispatcher.utter_message(text=f"According to {finalapidata[0]['Date']}")
        dispatcher.utter_message(text=f"Total Confirmed Cases: {finalapidata[0]['TotalConfirmed']}")
        dispatcher.utter_message(text=f"New Confirmed Cases: {finalapidata[0]['NewConfirmed']}")
        dispatcher.utter_message(text=f"Total Death: {finalapidata[0]['TotalDeaths']}")
        dispatcher.utter_message(text=f"New Death:  {finalapidata[0]['NewDeaths']}")
        dispatcher.utter_message(text=f"Total Recovered: {finalapidata[0]['TotalRecovered']}")
        dispatcher.utter_message(text=f"New Recovered:  {finalapidata[0]['NewRecovered']}")
        
        return [FollowupAction("action_farewall")]

class ActionCovidGlobalData(Action):

    def name(self) -> Text:
        return "showing_covid_data_globally"

    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
             domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:

        logger.info("displaying global data")
        dispatcher.utter_message(text=f"Total Confirmed Cases: {TotalConfirmedCasesG}")
        dispatcher.utter_message(text=f"New Confirmed Cases: {NewConfirmetdCasesG}")
        dispatcher.utter_message(text=f"Total Death: {TotalDeathCasesG}")
        dispatcher.utter_message(text=f"New Death:  {NewDeathCasesG}")
        dispatcher.utter_message(text=f"Total Recovered: {TotalRecoveredCasesG}")
        dispatcher.utter_message(text=f"New Recovered:  {NewRecoveredCasesG}")

        return [FollowupAction("action_farewall")]        

class ActionShowingSymptoms(Action):

    def name(self) -> Text:
        return "action_showing_covid_symptoms"

    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        if tracker.get_slot("showing_symptoms") is None:
            dispatcher.utter_message(text = "These are the commom Symptoms of COVID19")
            dispatcher.utter_message(text = "Fever")
            dispatcher.utter_message(text = "Cough")
            dispatcher.utter_message(text = "Loss of Taste and Smell")
            dispatcher.utter_message(text = "Headache")
            dispatcher.utter_message(text = "Aches and Pain")
            dispatcher.utter_message(text = "  ")
            dispatcher.utter_message(text = "But, Symptoms are different for Different varient")
            buttons = [ 
            {"title": "Alpha", "payload": "Show Alpha Varient Symptoms"},
            {"title": "Beta", "payload": "Show Beta Varient Symptoms"},
            {"title": "Gamma", "payload": "Show Gamma Varient Symptoms"},
            {"title": "Delta", "payload": "Show Delta Varient Symptoms"},
            {"title": "Omicrom", "payload": "Show Omicrom Varient Symptoms"}
            ]
            dispatcher.utter_message(text = "Choose the varient", buttons = buttons)
            return[SlotSet("showing_symptoms","Activated")]  


        if tracker.latest_message['text'] =="Show Alpha Varient Symptoms":
            dispatcher.utter_message(text = "Fever or chills")
            dispatcher.utter_message(text = "Shortness of breath or difficulty breathing")
            dispatcher.utter_message(text = "Cough")
            dispatcher.utter_message(text = "Fatigue")
            dispatcher.utter_message(text = "Muscle or body Aches")
            dispatcher.utter_message(text = "Headache")
            dispatcher.utter_message(text = "Sore throat")
            dispatcher.utter_message(text = "New loss of taste or smell")
            dispatcher.utter_message(text = "Congesstion or runny nose")
            dispatcher.utter_message(text = "Nausea or vomiting")
            dispatcher.utter_message(text = "Diarrhea")
            return [SlotSet("showing_symptoms", None), FollowupAction("action_farewall")]

        if tracker.latest_message['text'] =="Show Beta Varient Symptoms":
            dispatcher.utter_message(text = "Fever or chills")
            dispatcher.utter_message(text = "Shortness of breath or difficulty breathing")
            dispatcher.utter_message(text = "Cough")
            dispatcher.utter_message(text = "Fatigue")
            dispatcher.utter_message(text = "Muscle or body Aches")
            dispatcher.utter_message(text = "Headache")
            dispatcher.utter_message(text = "Sore throat")
            dispatcher.utter_message(text = "New loss of taste or smell")
            dispatcher.utter_message(text = "Congesstion or runny nose")
            dispatcher.utter_message(text = "Nausea or vomiting")
            dispatcher.utter_message(text = "Diarrhea")
            return [SlotSet("showing_symptoms", None), FollowupAction("action_farewall")]

        if tracker.latest_message['text'] =="Show Gamma Varient Symptoms":
            dispatcher.utter_message(text = "Fever")
            dispatcher.utter_message(text = "Headache")
            dispatcher.utter_message(text = "Sore throat")
            dispatcher.utter_message(text = "Cough")
            dispatcher.utter_message(text = "Coryza")
            dispatcher.utter_message(text = "Shortness of breath")
            dispatcher.utter_message(text = "Gastrointestinal")
            dispatcher.utter_message(text = "Diarrhea") 
            return [SlotSet("showing_symptoms", None), FollowupAction("action_farewall")]

        if tracker.latest_message['text'] =="Show Delta Varient Symptoms": 
            dispatcher.utter_message(text = "Fever")
            dispatcher.utter_message(text = "Headache")
            dispatcher.utter_message(text = "Cold")
            dispatcher.utter_message(text = "Cough")
            dispatcher.utter_message(text = "Lost of smell")
            dispatcher.utter_message(text = "Diarrhea")   
            return [SlotSet("showing_symptoms", None), FollowupAction("action_farewall")]  

        if tracker.latest_message['text'] =="Show Omicrom Varient Symptoms": 
            dispatcher.utter_message(text = "Fever")
            dispatcher.utter_message(text = "Headache")
            dispatcher.utter_message(text = "Running Nose")
            dispatcher.utter_message(text = "Fatigue(mild or severe")
            dispatcher.utter_message(text = "Sneezing")
            dispatcher.utter_message(text = "Sore throat") 
            return [SlotSet("showing_symptoms", None), FollowupAction("action_farewall")]

            
            #return [SlotSet("showing_symptoms", "Activated")]

      #  return [FollowupAction("action_farewall")]
        return []

class ActionHelloWorld(Action):

    def name(self) -> Text:
        return "action_farewall"

    def run(self, dispatcher: CollectingDispatcher,
             tracker: Tracker,
             domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:

        dispatcher.utter_message(" ")
        dispatcher.utter_message(" ")
        dispatcher.utter_message("Thank you for interacting with the bot.")
        dispatcher.utter_message("I Think We are able to solve your Problem")
        dispatcher.utter_message("Taking you back to the beginning of the chat...")
        dispatcher.utter_message("Hello there! What can I do for you?")

        return []        