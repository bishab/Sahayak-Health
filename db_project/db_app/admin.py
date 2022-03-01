from django.contrib import admin
from .models import *
admin.site.register(PatientRegistrationModel)
admin.site.register(AppointmentModel)
admin.site.register(VerifyEmailModel)
