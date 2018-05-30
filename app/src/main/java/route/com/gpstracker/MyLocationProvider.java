package route.com.gpstracker;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Mohamed Nabil Mohamed (Nobel) on 5/12/2018.
 * byte code SA
 * m.nabil.fci2015@gmail.com
 */

public class MyLocationProvider implements LocationListener{

    private static final long MIN_TIME_BW_UPDATES = 5*60*1000;
    private static final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    Context context;
    LocationManager locationManager;

    boolean isGPSEnabled;
    boolean isNetworkEnabled;
    boolean canGetLocation;

    double latitude;
    double longitude;
    Location location;

    public MyLocationProvider(Context context){
        this.context=context;
        longitude=0.0f;
        latitude=0.0f;
        location=null;
        isGPSEnabled=false;
        isNetworkEnabled=false;
        canGetLocation=false;
        getLocation();


    }

    Location getLocation(){

        locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);

        isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnabled && !isNetworkEnabled){
            canGetLocation=false;
            latitude=0.0f;
            longitude=0.0f;
            location=null;
            return location;
        }
        else{
            canGetLocation=true;
            String provider="";
            if (isGPSEnabled)
               provider= LocationManager.GPS_PROVIDER;
            else if (isNetworkEnabled) provider=LocationManager.NETWORK_PROVIDER;


            try {
                locationManager.requestLocationUpdates(
                        provider,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                Log.e("provider", provider);
                if (locationManager != null) {
                    location = locationManager
                            .getLastKnownLocation(provider);
                    Log.e("location manager","!null");

                }else {
                    Log.e("location manager","is null");

                }

                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    Log.e("location","! null");

                }else {
                    Log.e("location","null");
                }

            } catch (SecurityException e) {
                Log.e("SecurityException1", e.toString());
            }

            return location;
        }
    }

    boolean canGetLocation(){
        return this.canGetLocation;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Log.e("provider enabled",s);
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.e("provider disabled",s);

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location!=null){
            this.location=location;
            latitude=location.getLatitude();
            longitude=location.getLongitude();
        }
    }
}
