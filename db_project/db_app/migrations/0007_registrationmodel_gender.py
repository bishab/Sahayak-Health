# Generated by Django 4.0.1 on 2022-03-01 06:09

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('db_app', '0006_registrationmodel_date_of_birth'),
    ]

    operations = [
        migrations.AddField(
            model_name='registrationmodel',
            name='gender',
            field=models.CharField(default='', max_length=15),
        ),
    ]