# Generated by Django 4.0.1 on 2022-03-01 08:12

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('db_app', '0007_registrationmodel_gender'),
    ]

    operations = [
        migrations.CreateModel(
            name='AppointmentModel',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('hospital', models.CharField(max_length=100)),
                ('department', models.CharField(max_length=100)),
                ('doctor', models.CharField(max_length=50)),
                ('date', models.EmailField(max_length=50)),
                ('time', models.CharField(max_length=50)),
            ],
        ),
        migrations.RenameModel(
            old_name='RegistrationModel',
            new_name='PatientRegistrationModel',
        ),
    ]
