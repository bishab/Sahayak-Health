from enum import unique
from django.db import models

#---------------------------------------- PATIENT REGISTRATION------------------------------------------
class PatientRegistrationModel(models.Model):
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


#---------------------------------------- PATIENT APPOINTMENT------------------------------------------
class AppointmentModel(models.Model):
    patient_email=models.EmailField(max_length=50)
    hospital=models.CharField(max_length=100)
    department=models.CharField(max_length=100)
    doctor=models.CharField(max_length=50)
    date=models.CharField(max_length=50)
    time=models.CharField(max_length=50)

    def __str__(self):
        return self.hospital +" "+ self.patient_email+" "+self.date
    class Meta:
        unique_together=('hospital','department','doctor','date','time')

#---------------------------------------- DOCTOR REGISTRATION------------------------------------------
#Doctor's Basic Registration
class DoctorBasicRegistrationModel(models.Model):
    first_name=models.CharField(max_length=20)
    last_name=models.CharField(max_length=20)
    age=models.CharField(max_length=10)
    gender=models.CharField(max_length=15,default='')
    email=models.EmailField(max_length=50,unique=True)
    password=models.CharField(max_length=20)
    address=models.CharField(max_length=30,default='')
    contact_number=models.CharField(max_length=13)
    nationality=models.CharField(max_length=50)

    def __str__(self):
        return self.first_name +" "+ self. last_name

#Doctor's Special Registration
class DoctorSpecialRegistrationModel(models.Model):
    doctor_email=models.EmailField(max_length=50,unique=True,default='')
    highest_qualification=models.CharField(max_length=100)
    working_hospital=models.CharField(max_length=100)
    license_proof=models.FileField(upload_to='license_proofs/')
    experience_years=models.CharField(max_length=20)
    home_visit_availability=models.CharField(max_length=50)
    stay_location=models.CharField(max_length=100)
    marital_status=models.CharField(max_length=30)
    working_date=models.CharField(max_length=100)

    def __str__(self):
        return self.doctor_email +" "+ self.working_hospital


#---------------------------------------- EMAIL VERIFICATION------------------------------------------
class VerifyEmailModel(models.Model):
    email=models.EmailField(max_length=50)
    is_verified=models.BooleanField(default=False)
    token=models.CharField(max_length=100,default=None)

    def __str__(self):
        return self.email
