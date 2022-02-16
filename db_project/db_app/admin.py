from django.contrib import admin
from .models import *
admin.site.register(RegistrationModel)
admin.site.register(VerifyEmailModel)