from django.urls import path
from .views import *
urlpatterns = [
    path('getreg/',RegistrationView.as_view(http_method_names=['get'])),
    path('getreg/emailverify/<str:pk>/',RegistrationView.as_view(http_method_names=['get'])),
    path('postreg/',RegistrationView.as_view(http_method_names=['post'])),
    path('patchreg/<int:pk>/',RegistrationView.as_view(http_method_names=['patch'])),
    path('delreg/<int:pk>/',RegistrationView.as_view(http_method_names=['delete'])),
    path('verifyemail/<str:email>',VerifyEmailView.as_view()),
    path('verifytoken/<str:email>/<str:token>',VerifyTokenView.as_view()),
]