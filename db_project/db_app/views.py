from functools import partial
from .models import RegistrationModel, VerifyEmailModel
from .serializers import RegistrationSerializer, VerifyEmailSerializer
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from django.core.mail import send_mail
import random

class RegistrationView(APIView):
    def get(self,request,email=None):
        """
        This function does two things.
            1. If id is given, it displays the data as per that id.
            2. If id is not given, it displays the total data we have stored.
        """
        if email is not None:
            singledata=RegistrationModel.objects.filter(email=email)
            serializer=RegistrationSerializer(singledata,many=True)
            return Response(serializer.data)

        data=RegistrationModel.objects.all()
        serializer=RegistrationSerializer(data,many=True)
        return Response(serializer.data)
    
    def post(self,request):
        """
        This function stores new data into the database.
        """
        serializer=RegistrationSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        else:
            return Response({"status": "error", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

    def patch(self,request,email):
        """
        This function updates the data partially.
        """
        singledata=RegistrationModel.objects.filter(email=email).first()
        serializer=RegistrationSerializer(singledata,data=request.data,partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response({"msg":"Data Partially Updated"})
        else:
            return Response({"status": "error", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

    def delete(self,request,email):
        """
        This function deletes the data based on contact number.
        """
        singledata=RegistrationModel.objects.filter(email=email)
        singledata.delete()
        return Response({"msg":"Data Deleted"})


class VerifyEmailView(APIView):
    def post(self,request,email):
        token=str(random.random()).split(".")[1][0:4]
        data={"email":email,"is_verified":"False","token":token}
        serializer=VerifyEmailSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
        send_mail(
            'VERIFICATION',
            f'This is your token: {token}',
            'constant',
            [email],
            fail_silently=False
        )
        return Response({"msg":"Mail Sent. Please Check It Once!"})

class VerifyTokenView(APIView):
    def post(self,request,email,token):
        user=VerifyEmailModel.objects.filter(email=email)
        serializer=VerifyEmailSerializer(user,many=True)
        if token==serializer.data[0]['token']:
            updated_data={"email":email,"is_verified":"True","token":token}
            serializer=VerifyEmailSerializer(data=updated_data,partial=True)
            if serializer.is_valid():
                serializer.save()
            return Response({"msg":"Email Verified"})
        if token!=serializer.data[0]['token']:
            return Response({"msg":"Wrong Token Provided"})
