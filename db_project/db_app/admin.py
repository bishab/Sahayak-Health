from django.contrib import admin
from .models import *
admin.site.register(PatientRegistrationModel)       #for patient registration
admin.site.register(AppointmentModel)               #for patient appointment
admin.site.register(DoctorRegistrationModel)        #for doctor registration
admin.site.register(VerifyEmailModel)               #for email verification
