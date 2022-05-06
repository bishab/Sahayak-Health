from django.urls import path
from .views import *
urlpatterns = [
#---------------------------------------- PATIENT REGISTRATION------------------------------------------
    path('patient/getreg/',PatientRegistrationView.as_view(http_method_names=['get'])),
    path('patient/getreg/<str:email>/',PatientRegistrationView.as_view(http_method_names=['get'])),
    path('patient/postreg/',PatientRegistrationView.as_view(http_method_names=['post'])),
    path('patient/patchreg/<str:email>/',PatientRegistrationView.as_view(http_method_names=['patch'])),
    path('patient/delreg/<str:email>/',PatientRegistrationView.as_view(http_method_names=['delete'])),
#---------------------------------------- PATIENT APPOINTMENT------------------------------------------
    path('patient/getapp/',AppointmentView.as_view(http_method_names=['get'])),
    path('patient/getapp/<str:email>/',AppointmentView.as_view(http_method_names=['get'])),
    path('patient/postapp/',AppointmentView.as_view(http_method_names=['post'])),
    path('patient/delapp/<str:email>',AppointmentView.as_view(http_method_names=['delete'])),
#---------------------------------------- DOCTOR REGISTRATION------------------------------------------
    path('doctor/getreg/',DoctorRegView.as_view(http_method_names=['get'])),
    path('doctor/getreg/<str:email>/',DoctorRegView.as_view(http_method_names=['get'])),
    path('doctor/postreg/',DoctorRegView.as_view(http_method_names=['post'])),
    path('doctor/patchreg/<str:email>/',DoctorRegView.as_view(http_method_names=['patch'])),
    path('doctor/delreg/<str:email>/',DoctorRegView.as_view(http_method_names=['delete'])),
#---------------------------------------- EMAIL AND TOKEN VERIFICATION------------------------------------------
    path('verifyemail/<str:email>',VerifyEmailView.as_view()),
    path('verifytoken/<str:email>/<str:token>',VerifyTokenView.as_view()),
    path('userlogin/',UserLoginView.as_view()),
#---------------------------------------- MISC ENDPOINTS------------------------------------------
    path('hospitals/',HospitalNames.as_view()),
    path('doctors/<str:hospital>',DoctorNames.as_view()),

]