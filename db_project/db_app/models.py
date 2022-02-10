from enum import unique
from django.db import models

class RegistrationModel(models.Model):
    first_name=models.CharField(max_length=20)
    last_name=models.CharField(max_length=20)
    email=models.EmailField(max_length=50,unique=True)
    password=models.CharField(max_length=20)
    contact_number=models.CharField(max_length=13,unique=True)

    def __str__(self):
        return self.first_name +" "+ self. last_name

   