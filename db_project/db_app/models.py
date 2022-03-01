from enum import unique
from django.db import models

class RegistrationModel(models.Model):
    first_name=models.CharField(max_length=20)
    last_name=models.CharField(max_length=20)
    gender=models.CharField(max_length=15,default='')
    email=models.EmailField(max_length=50,unique=True)
    password=models.CharField(max_length=20)
    address=models.CharField(max_length=30,default='')
    date_of_birth=models.CharField(max_length=30,default='')
    contact_number=models.CharField(max_length=13)

    def __str__(self):
        return self.first_name +" "+ self. last_name

class VerifyEmailModel(models.Model):
    email=models.EmailField(max_length=50)
    is_verified=models.BooleanField(default=False)
    token=models.CharField(max_length=100,default=None)

    def __str__(self):
        return self.email
