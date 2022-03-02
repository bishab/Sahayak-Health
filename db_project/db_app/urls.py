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
#---------------------------------------- DOCTOR BASIC REGISTRATION------------------------------------------
    path('bdoctor/getreg/',DoctorBasicRegView.as_view(http_method_names=['get'])),
    path('bdoctor/getreg/<str:email>/',DoctorBasicRegView.as_view(http_method_names=['get'])),
    path('bdoctor/postreg/',DoctorBasicRegView.as_view(http_method_names=['post'])),
    path('bdoctor/patchreg/<str:email>/',DoctorBasicRegView.as_view(http_method_names=['patch'])),
    path('bdoctor/delreg/<str:email>/',DoctorBasicRegView.as_view(http_method_names=['delete'])),
#---------------------------------------- DOCTOR SPECIAL REGISTRATION------------------------------------------
    path('sdoctor/getreg/',DoctorSpecialRegView.as_view(http_method_names=['get'])),
    path('sdoctor/getreg/<str:email>/',DoctorSpecialRegView.as_view(http_method_names=['get'])),
    path('sdoctor/postreg/',DoctorSpecialRegView.as_view(http_method_names=['post'])),
    path('sdoctor/patchreg/<str:email>/',DoctorSpecialRegView.as_view(http_method_names=['patch'])),
    path('sdoctor/delreg/<str:email>/',DoctorSpecialRegView.as_view(http_method_names=['delete'])),
#---------------------------------------- EMAIL AND TOKEN VERIFICATION------------------------------------------
    path('verifyemail/<str:email>',VerifyEmailView.as_view()),
    path('verifytoken/<str:email>/<str:token>',VerifyTokenView.as_view()),
    path('userlogin/',UserLoginView.as_view()),

]