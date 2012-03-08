package jedi.gps;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class GpsActivity extends Activity {

	List<Address> l;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		l = null;
		LocationManager locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);

		//Define a listener that responds to location updates
		LocationListener locationListener = new LocationListener() {

			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				/*Geocoder gc = new Geocoder(getApplicationContext());
				try{
					l = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 5);
				}catch (Exception e) {
					e.printStackTrace();
				}
				for (int i = 0; i < l.size(); i++) {
					Log.v("HOLA", l.get(i).getAddressLine(0).toString());
				}
				String message = ((Double)location.getLatitude()).toString();
				Log.v("GPS", message);*/
			}
		};

		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		locationManager.requestLocationUpdates(
				LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		Location a =  locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		//System.out.println();
			Log.v ("GPS", ((Double)a.getLatitude()).toString());
			Log.v("GPS", "HOLA");
	}
}