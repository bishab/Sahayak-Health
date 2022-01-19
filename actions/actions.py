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



from typing import Any, Text, Dict, List
from rasa_sdk import Action, Tracker
from rasa_sdk.executor import CollectingDispatcher
from rasa_sdk.events import SlotSet 

class ActionHelloWorld(Action):
    def name(self) -> Text:
        return "from_action_file"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        return []

class ActionCovidBot(Action):
    def name(self) -> Text:
        return "action_covid_bot"
    def run(self, dispatcher: CollectingDispatcher,
            tracker: Tracker,
            domain: Dict[Text, Any]) -> List[Dict[Text, Any]]:
        if tracker.get_slot("fully_vaccinated") is None:
            buttons = [{"title": "Yes", "payload": "Fully Vaccination Pos"},
            {"title": "No", "payload": "Fully Vaccination Neg"}]
            dispatcher.utter_message(text="Are you fully vaccinated against COVID-19?", buttons=buttons)
            return [SlotSet('fully_vaccinated',tracker.latest_message['text'])]
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

        if any(substring in tracker.latest_message["text"] for substring in ["Covid Symptoms Pos","Covid Symptoms Neg"]):
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
            {"title": "None of these symptoms", "payload": "None of these symptoms"},]
            dispatcher.utter_message(text="Are you currently experiencing of these symptoms?", buttons=buttons)
            return [SlotSet("initial_covid_symptoms",tracker.latest_message['text'])]

        if any(substring in tracker.latest_message["text"] for substring in ["fever chills symptoms",\
            "cough or barking cough","shortness of breath","Decrease or loss of taste or smell"\
                ,"Muscle aches or joint pain","Extreme tiredness","Sore throat","Runny, congested or stuffed nose",\
                    "Headache","Nausea, Vomiting or diarrhea"]):
            dispatcher.utter_message("Based on your answers, we recommend you to self-isolate yourself at home or at nearest isolation centre.")
            dispatcher.utter_message("Please follow the below guidelines issued by the government:")
            dispatcher.utter_attachment("")
            return [SlotSet("probable_covid_symptoms",tracker.latest_message['text'])]
        
        if tracker.latest_message=="None of these symptoms":
            buttons = [{"title": "been sick with symptoms like COVID-19?", "payload": "close relative with covid symptoms"},
            {"title": "tested positive for COVIF-19?", "payload": "living with covid patient"}]
            dispatcher.utter_message(text="In the last fifteen days, has someone live you with?", buttons=buttons)
            return [SlotSet("probable_covid_symptoms",tracker.latest_message['text'])]

        if tracker.latest_message=="living with covid patient":
            dispatcher.utter_message("Based on your answers, we recommend you to self-isolate yourself at home or at nearest isolation centre.")
            dispatcher.utter_message("Please follow the below guidelines issued by the government:")
            dispatcher.utter_attachment("")
            return [SlotSet("family_covid_history",tracker.latest_message['text'])]
        
        if tracker.latest_message=="None of these symptoms":
            buttons = [{"title": "been sick with symptoms like COVID-19?", "payload": "close relative with covid symptoms"},
            {"title": "tested positive for COVID-19?", "payload": "living with covid patient"}]
            dispatcher.utter_message(text="In the last ten days, have you tasted positive with rapid antigen test or home based self testing kit?", buttons=buttons)
            return [SlotSet("probable_covid_symptoms",tracker.latest_message['text'])]
            
        return []
