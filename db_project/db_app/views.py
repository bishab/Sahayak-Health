from django.shortcuts import render
from .models import RegistrationModel
from .serializers import RegistrationSerializer
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
import requests
class RegistrationView(APIView):
    
    def get(self,request):
        data=RegistrationModel.objects.all()
        serializer=RegistrationSerializer(data,many=True)
        return Response(serializer.data)
    
    def postreg(self,request):
        serializer=RegistrationSerializer(data=requests.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        else:
            return Response({"status": "error", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)
