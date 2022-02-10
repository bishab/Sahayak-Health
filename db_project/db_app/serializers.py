from .models import RegistrationModel
from rest_framework import serializers

class RegistrationSerializer(serializers.ModelSerializer):
    class Meta:
        model=RegistrationModel
        fields=("first_name","last_name","email","password","contact_number")
        