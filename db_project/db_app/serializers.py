from .models import *
from rest_framework import serializers

class RegistrationSerializer(serializers.ModelSerializer):
    class Meta:
        model=RegistrationModel
        fields='__all__'

class VerifyEmailSerializer(serializers.ModelSerializer):
    class Meta:
        model=VerifyEmailModel
        fields='__all__'