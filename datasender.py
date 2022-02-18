import requests
data={"first_name":"Khadga",\
    "last_name":"Oli",\
        "email":"khadgaoli@gmail.com",\
            "password":"emaale",\
                "address":"Balkot",\
                    "date_of_birth":"aaja ho",\
                        "contact_number":"981212111"}
a=requests.post("http://20.41.221.66:7000/postreg/",data=data)
print(a)