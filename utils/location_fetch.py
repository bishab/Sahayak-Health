from gettext import find
import geocoder
from geopy.geocoders import Nominatim
def find_location():
    """
    Returns the location of the user based on ip address.
    Args:
        None
    Returns:
        City, State and Country
    """
    g = geocoder.ip('me')
    latitude=g.latlng[0]
    longitude=g.latlng[1]
    geolocator=Nominatim(user_agent="geoapiExercises")
    locstring=str(latitude)+","+str(longitude)
    location=geolocator.reverse(locstring,language='en')
    address= location.raw['address']
    city=address.get('city','')
    state=address.get('state','')
    country=address.get('country','')
    return country