from django.urls import path
from .views import *
urlpatterns = [
    path('getreg/',RegistrationView.as_view()),
    path('postreg/',RegistrationView.as_view())

]
