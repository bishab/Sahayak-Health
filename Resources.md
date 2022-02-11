# Frameworks Used
<i>Date: 11 Feb, 2022</i>
<br><br>
<b> So far in this project, we have made use of following frameworks.</b>

- For Chatbot:

	- Python v3.x

	- RASA v3.x
			-  RASA's NLU feature (for conversational bot-text)
			- RASA SDK for Action Server (for fulfillment bot-buttons) 
	- Geocoder v1.38.1 (for fetching user's location with IP)
	- Python's logging module for maintaining logs
	- Python datetime for greeting user with user's localtime
	- APIs
		- Covid-19 API Summary for fetching country-wise and global covid data (https://api.covid19api.com/summary)

- For Frontend:
	- React v17.0.2 (as a frontend framework)
        - Mantine v3.6.3 (React components library)
        - D3 Visualization of Covid data with react-simple-maps v2.3.0 (for Maps)
            - API used:
                - https://corona.lmao.ninja/v2/countries
        - Axios v0.25.0 (for handling API CRUD operations)
	- Other APIs used in website
		- Myths about Covid-19 (https://corona.askbhunte.com/api/v1/myths)
        - FAQs about Covid-19 (https://corona.askbhunte.com/api/v1/faqs)
        - List of hospitals, temporary hospitals, and health posts of Nepal with capacity information. (https://corona.askbhunte.com/api/v1/hospitals) 
        - Covid-19 News (https://corona.askbhunte.com/api/v1/news)


- For Database
	- djangorestframework v3.13.1 (for developing RESTful APIs)
	- Django v4.0.1 (implementing RESTful APIs for overall database)
    - django-filter (for filter operations in API)
	
- For Deployment on remote machine
	- Ubuntu Server (Microsoft Azure's Virtual Machine Service)
        - Version: 20.04.3 LTS
		- Public IP: 13.71.80.137 
		- vCPU: 1
		- RAM: 2GiB (will be adjusted per need)
		- Location: South India
	- TMUX  v3.0a (for multitasking in a server)
	- Python http.server (for hosting webchat UI for the bot)
	