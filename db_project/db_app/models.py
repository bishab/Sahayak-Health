import email
from enum import unique
from django.db import models

#---------------------------------------- PATIENT REGISTRATION------------------------------------------
class PatientRegistrationModel(models.Model):
    gender_choices=(('Male','Male'),('Female','Female'),('Other','Other'))
    first_name=models.CharField(max_length=50)
    last_name=models.CharField(max_length=50)
    gender=models.CharField(max_length=15,choices=gender_choices)
    email=models.EmailField(max_length=50,unique=True)
    password=models.CharField(max_length=50)
    address=models.CharField(max_length=50,default='')
    date_of_birth=models.CharField(max_length=50,default='')
    contact_number=models.CharField(max_length=50)

    def __str__(self):
        return self.first_name +" "+ self. last_name


#---------------------------------------- PATIENT APPOINTMENT------------------------------------------
class AppointmentModel(models.Model):
    gender_choices=(('Male','Male'),('Female','Female'),('Other','Other'))
    firstname=models.CharField(max_length=50,default='')
    lastname=models.CharField(max_length=50,default='')
    age=models.CharField(max_length=50,default='')
    patient_email=models.EmailField(max_length=50,default='')
    patient_address=models.CharField(max_length=100,default='')
    gender=models.CharField(max_length=50,choices=gender_choices,default='')
    contact_number=models.CharField(max_length=50,default='')
    hospital=models.CharField(max_length=100,default='')
    department=models.CharField(max_length=100,default='')
    date=models.CharField(max_length=50,default='')
    time=models.CharField(max_length=50,default='')
    previous_reports=models.FileField(upload_to="src/patient_previous_reports/",default=None, blank=True)
    def __str__(self):
        return self.hospital +" "+ self.patient_email+" "+self.date
    class Meta:
        unique_together=('hospital','department','date','time')

#---------------------------------------- DOCTOR REGISTRATION------------------------------------------
#Doctor's Registration
class DoctorRegistrationModel(models.Model):
    gender_choices=(('Male','Male'),('Female','Female'),('Other','Other'))
    marital_status_choices=(('Married','Married'),('Unmarried','Unmarried'),('Divorced','Divorced'))
    first_name=models.CharField(max_length=50)
    last_name=models.CharField(max_length=50)
    age=models.CharField(max_length=50)
    gender=models.CharField(max_length=50,choices=gender_choices)
    email=models.EmailField(max_length=50,unique=True)
    password=models.CharField(max_length=50)
    address=models.CharField(max_length=50,default='')
    contact_number=models.CharField(max_length=53)
    nationality=models.CharField(max_length=50)
    highest_qualification=models.CharField(max_length=100)
    working_hospital=models.CharField(max_length=100)
    experience_years=models.CharField(max_length=50)
    home_visit_availability=models.CharField(max_length=50)
    stay_location=models.CharField(max_length=100)
    marital_status=models.CharField(max_length=50,choices=marital_status_choices)
    working_days=models.CharField(max_length=50,default='')
    def __str__(self):
        return self.first_name +" "+ self.last_name


#---------------------------------------- EMAIL VERIFICATION------------------------------------------
class VerifyEmailModel(models.Model):
    email=models.EmailField(max_length=50)
    is_verified=models.BooleanField(default=False)
    token=models.CharField(max_length=100,default=None)

    def __str__(self):
        return self.email
