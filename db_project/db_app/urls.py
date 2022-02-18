from django.urls import path
from .views import *
urlpatterns = [
    path('getreg/',RegistrationView.as_view(http_method_names=['get'])),
    path('getreg/<str:email>/',RegistrationView.as_view(http_method_names=['get'])),
    path('postreg/',RegistrationView.as_view(http_method_names=['post'])),
    path('patchreg/<str:email>/',RegistrationView.as_view(http_method_names=['patch'])),
    path('delreg/<str:email>/',RegistrationView.as_view(http_method_names=['delete'])),
    path('verifyemail/<str:email>',VerifyEmailView.as_view()),
    path('verifytoken/<str:email>/<str:token>',VerifyTokenView.as_view()),
    path('userlogin/<str:email>/<str:password>',UserLoginView.as_view()),
]