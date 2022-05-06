from .models import *
from rest_framework import serializers
#---------------------------------------- PATIENT REGISTRATION------------------------------------------
class PatientRegistrationSerializer(serializers.ModelSerializer):
    class Meta:
        model=PatientRegistrationModel
        fields='__all__'

#---------------------------------------- PATIENT APPOINTMENT------------------------------------------
class AppointmentSerializer(serializers.ModelSerializer):
    class Meta:
        model=AppointmentModel
        fields='__all__'

#---------------------------------------- DOCTOR REGISTRATION------------------------------------------
class DoctorRegSerializer(serializers.ModelSerializer):
    class Meta:
        model=DoctorRegistrationModel
        fields='__all__'

#---------------------------------------- EMAIL VERIFICATION------------------------------------------
class VerifyEmailSerializer(serializers.ModelSerializer):
    class Meta:
        model=VerifyEmailModel
        fields='__all__'

