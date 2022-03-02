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
#Doctor's Basic Registration
class DoctorBasicRegSerializer(serializers.ModelSerializer):
    class Meta:
        model=DoctorBasicRegistrationModel
        fields='__all__'

#Doctor's Speciality Registration
class DoctorSpecialRegSerializer(serializers.ModelSerializer):
    class Meta:
        model=DoctorSpecialRegistrationModel
        fields='__all__'

#---------------------------------------- EMAIL VERIFICATION------------------------------------------
class VerifyEmailSerializer(serializers.ModelSerializer):
    class Meta:
        model=VerifyEmailModel
        fields='__all__'

