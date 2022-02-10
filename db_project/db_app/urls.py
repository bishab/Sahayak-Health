from django.urls import path
from .views import *
urlpatterns = [
    path('getreg/',RegistrationView.as_view(http_method_names=['get'])),
    path('getreg/<int:pk>/',RegistrationView.as_view(http_method_names=['get'])),
    path('postreg/',RegistrationView.as_view(http_method_names=['post'])),
    path('patchreg/<int:pk>/',RegistrationView.as_view(http_method_names=['patch'])),
    path('delreg/<int:pk>/',RegistrationView.as_view(http_method_names=['delete'])),
]