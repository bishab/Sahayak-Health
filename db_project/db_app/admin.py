from django.contrib import admin
from .models import *
admin.site.register(PatientRegistrationModel)       #for patient registration
admin.site.register(AppointmentModel)               #for patient appointment
admin.site.register(DoctorBasicRegistrationModel)   #for doctor basic registration
admin.site.register(DoctorSpecialRegistrationModel) #for doctor special registration
admin.site.register(VerifyEmailModel)               #for email verification
