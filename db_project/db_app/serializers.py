from .models import *
from rest_framework import serializers

class PatientRegistrationSerializer(serializers.ModelSerializer):
    class Meta:
        model=PatientRegistrationModel
        fields='__all__'

class AppointmentSerializer(serializers.ModelSerializer):
    class Meta:
        model=AppointmentModel
        fields='__all__'

class VerifyEmailSerializer(serializers.ModelSerializer):
    class Meta:
        model=VerifyEmailModel
        fields='__all__'

