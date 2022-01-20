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
            return [SlotSet("next_question","new symptoms activated")]

        #Taking user back to the beginning of the chat in case user inputs any strange text.
        else:
            print(tracker.latest_message['text'])
            dispatcher.utter_message("Sorry, I didn't understand that.")
            dispatcher.utter_message("Taking you back to the beginning of the chat...")
            dispatcher.utter_message("Hello there!")
            return [SlotSet("next_question","new symptoms activated")]

        return []
