from functools import partial
from pydoc import doc
from .models import *
from .serializers import *
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from django.core.mail import send_mail
import random
from db_app.utils.misc_functions import doctors_fetcher, hospital_fetcher
#---------------------------------------- PATIENT REGISTRATION------------------------------------------
class PatientRegistrationView(APIView):
    def get(self,request,email=None):
        """
        This function does two things.
            1. If id is given, it displays the data as per that id.
            2. If id is not given, it displays the total data we have stored.
        """
        if email is not None:
            singledata=PatientRegistrationModel.objects.filter(email=email)
            serializer=PatientRegistrationSerializer(singledata,many=True)
            return Response(serializer.data)

        data=PatientRegistrationModel.objects.all()
        serializer=PatientRegistrationSerializer(data,many=True)
        return Response(serializer.data)
    
    def post(self,request):
        """
        This function stores new data into the database.
        """
        serializer=PatientRegistrationSerializer(data=request.data)
        if serializer.is_valid():
            user=serializer.save()
            return Response({"msg":"user successfully registered"})
        else:
            return Response({"msg":"data format not valid"})
#            return Response({"msg": "error: data format not valid", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

    def patch(self,request,email):
        """
        This function updates the data partially.
        """
        singledata=PatientRegistrationModel.objects.filter(email=email).first()
        serializer=PatientRegistrationSerializer(singledata,data=request.data,partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response({"msg":"Data Partially Updated"})
        else:
            return Response({"msg":"data format not valid"})
#            return Response({"msg": "error: data format not valid", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

    def delete(self,request,email):
        """
        This function deletes the data based on email address.
        """
        singledata=PatientRegistrationModel.objects.filter(email=email)
        singledata.delete()
        return Response({"msg":"Data Deleted"})

#---------------------------------------- PATIENT APPOINTMENT------------------------------------------
class AppointmentView(APIView):
    def get(self,request,email=None):
        if email is not None:
            singledata=AppointmentModel.objects.filter(patient_email=email)
            serializer=AppointmentSerializer(singledata,many=True)
            return Response(serializer.data)
        data=AppointmentModel.objects.all()
        serializer=AppointmentSerializer(data,many=True)
        return Response(serializer.data)

    def post(self,request):
        data=request.data
        serializer=AppointmentSerializer(data=data)
        if serializer.is_valid():
            serializer.save()
            send_mail(
                'Appointment Booked',
                f'Your appointment on {data["hospital"]} is booked for {data["date"]}, {data["time"]}  with doctor {data["doctor"]}',
                'constant',
                [data["patient_email"]],
                fail_silently=False)
            return Response(serializer.data)
        else:
            return Response({"msg":"data format not valid"})
#            return Response({"msg": "error: data format not valid", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

    def delete(self,request,email):
        data=AppointmentModel.objects.filter(patient_email=email)
        serializer=AppointmentSerializer(data=data,many=True)
        send_mail(
            'Appointment Removed',
            'Your appointment has been deleted. If you want to rebook the appointment, please visit your profile.',
            'constant',
            [email],
            fail_silently=False)
        data.delete()
        return Response({"msg":"Data Deleted"})
    def patch(self,request,email):
        """
        This function updates the data partially.
        """
        singledata=PatientRegistrationModel.objects.filter(email=email).first()
        serializer=PatientRegistrationSerializer(singledata,data=request.data,partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response({"msg":"Data Partially Updated"})
        else:
            return Response({"msg":"data format not valid"})
#            return Response({"msg": "error: data format not valid", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

    def delete(self,request,email):
        """
        This function deletes the data based on email address.
        """
        singledata=PatientRegistrationModel.objects.filter(email=email)
        singledata.delete()
        return Response({"msg":"Data Deleted"})

#---------------------------------------- DOCTOR BASIC REGISTRATION------------------------------------------
class DoctorBasicRegView(APIView):
    def get(self,request,email=None):
        """
        This function does two things.
            1. If id is given, it displays the data as per that id.
            2. If id is not given, it displays the total data we have stored.
        """
        if email is not None:
            singledata=DoctorBasicRegistrationModel.objects.filter(email=email)
            serializer=DoctorBasicRegSerializer(singledata,many=True)
            return Response(serializer.data)

        data=DoctorBasicRegistrationModel.objects.all()
        serializer=DoctorBasicRegSerializer(data,many=True)
        return Response(serializer.data)

    def post(self,request):
        """
        This function stores new data into the database.
        """
        serializer=DoctorBasicRegSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        else:
            return Response({"msg":"data format not valid"})
#            return Response({"msg": "error: data format not valid", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

    def patch(self,request,email):
        """
        This function updates the data partially.
        """
        singledata=DoctorBasicRegistrationModel.objects.filter(email=email).first()
        serializer=DoctorBasicRegSerializer(singledata,data=request.data,partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response({"msg":"Data Partially Updated"})
        else:
            return Response({"msg":"data format not valid"})
#            return Response({"msg": "error: data format not valid", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

    def delete(self,request,email):
        """
        This function deletes the data based on email address.
        """
        singledata=DoctorBasicRegistrationModel.objects.filter(email=email)
        singledata.delete()
        return Response({"msg":"Data Deleted"})

#---------------------------------------- DOCTOR SPECIAL REGISTRATION------------------------------------------
class DoctorSpecialRegView(APIView):
    def get(self,request,email=None):
        """
        This function does two things.
            1. If id is given, it displays the data as per that id.
            2. If id is not given, it displays the total data we have stored.
        """
        if email is not None:
            singledata=DoctorSpecialRegistrationModel.objects.filter(doctor_email=email)
            serializer=DoctorSpecialRegSerializer(singledata,many=True)
            return Response(serializer.data)

        data=DoctorSpecialRegistrationModel.objects.all()
        serializer=DoctorSpecialRegSerializer(data,many=True)
        return Response(serializer.data)

    def post(self,request):
        """
        This function stores new data into the database.
        """
        serializer=DoctorSpecialRegSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        else:
            return Response({"msg":"data format not valid"})
#            return Response({"msg": "error: data format not valid", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

    def patch(self,request,email):
        """
        This function updates the data partially.
        """
        singledata=DoctorSpecialRegistrationModel.objects.filter(doctor_email=email).first()
        serializer=DoctorSpecialRegSerializer(singledata,data=request.data,partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response({"msg":"Data Partially Updated"})
        else:
            return Response({"msg":"data format not valid"})
#            return Response({"msg": "error: data format not valid", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

    def delete(self,request,email):
        """
        This function deletes the data based on email address.
        """
        singledata=DoctorSpecialRegistrationModel.objects.filter(doctor_email=email)
        singledata.delete()
        return Response({"msg":"Data Deleted"})


#---------------------------------------- EMAIL AND TOKEN VERIFICATION------------------------------------------
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

#---------------------------------------- PATIENT LOGIN------------------------------------------
class UserLoginView(APIView):
    def post(self,request):
        userdata=request.data
        email=userdata['email']
        password=userdata['password']
        user=PatientRegistrationModel.objects.filter(email=email)
        serializer=PatientRegistrationSerializer(user,many=True)
        if len(serializer.data)==0:
            return Response({"msg":"user not registered"})
#            return Response({"msg": "user not registered"}, status=status.HTTP_400_BAD_REQUEST)
        else:
            if password==serializer.data[0]['password']:
                return Response({"msg":"correct password"})
            if password!=serializer.data[0]['password']:
                return Response({"msg":"password incorrect"})
#                return Response({"msg": "incorrect password"}, status=status.HTTP_400_BAD_REQUEST)


#---------------------------------------- MISC ENDPOINTS------------------------------------------
class HospitalNames(APIView):
    def get(self,request):
        hospitals=hospital_fetcher()
        return Response({"hospital_names":hospitals})

class DoctorNames(APIView):
    def get(self,request,hospital):
        hospitals=hospital_fetcher()
        if hospital in hospitals:
            doctors=doctors_fetcher()
            return Response({hospital:doctors})
        else:
            return Response({"msg": "error"}, status=status.HTTP_400_BAD_REQUEST)