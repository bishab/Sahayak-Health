from .models import RegistrationModel
from .serializers import RegistrationSerializer
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status


class RegistrationView(APIView):

    def get(self,request,pk=None):
        """
        This function does two things.
            1. If id is given, it displays the data as per that id.
            2. If id is not given, it displays the total data we have stored.
        """
        id=pk
        if id is not None:
            print(id)
            singledata=RegistrationModel.objects.filter(contact_number=id)
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

    def patch(self,request,pk):
        """
        This function updates the data partially.
        """
        singledata=RegistrationModel.objects.filter(contact_number=pk).first()
        serializer=RegistrationSerializer(singledata,data=request.data,partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response({"msg":"Data Partially Updated"})
        else:
            return Response({"status": "error", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

    def delete(self,request,pk):
        """
        This function deletes the data based on contact number.
        """
        id=pk
        singledata=RegistrationModel.objects.filter(contact_number=id)
        singledata.delete()
        return Response({"msg":"Data Deleted"})




