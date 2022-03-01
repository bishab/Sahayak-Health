from django.urls import path
from .views import *
urlpatterns = [
    #for patient registrations
    path('patient/getreg/',PatientRegistrationView.as_view(http_method_names=['get'])),
    path('patient/getreg/<str:email>/',PatientRegistrationView.as_view(http_method_names=['get'])),
    path('patient/postreg/',PatientRegistrationView.as_view(http_method_names=['post'])),
    path('patient/patchreg/<str:email>/',PatientRegistrationView.as_view(http_method_names=['patch'])),
    path('patient/delreg/<str:email>/',PatientRegistrationView.as_view(http_method_names=['delete'])),
    #for patient appointments
    path('patient/getapp/',AppointmentView.as_view(http_method_names=['get'])),
    path('patient/getapp/<str:email>/',AppointmentView.as_view(http_method_names=['get'])),
    path('patient/postapp/',AppointmentView.as_view(http_method_names=['post'])),
    path('patient/delapp/<str:email>',AppointmentView.as_view(http_method_names=['delete'])),
    #for verification and login
    path('verifyemail/<str:email>',VerifyEmailView.as_view()),
    path('verifytoken/<str:email>/<str:token>',VerifyTokenView.as_view()),
    path('userlogin/',UserLoginView.as_view()),

]