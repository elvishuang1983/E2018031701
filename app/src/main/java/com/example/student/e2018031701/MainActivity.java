package com.example.student.e2018031701;

import android.Manifest;
import android.app.LoaderManager;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new MyListener());
    }

    class MyListener implements LocationListener
    {

        @Override
        public void onLocationChanged(Location location) {
            Log.d("LOC:","change");
            Log.d("LOC:",""+location.getLatitude()+","+location.getLongitude());
            Location loc101 = new Location("myLoc");
            loc101.setLatitude(25.0339639);
            loc101.setLongitude(121.5644722);
            //從所在位置到101的直線距離-->distanceTo
            double distance = loc101.distanceTo(location);
            Log.d("LOC","distance to 101 = "+ distance);
            Geocoder geocoder = new Geocoder(MainActivity.this);
            try {
                List<Address> list = geocoder.getFromLocation(25.024439,121.457815,1);
                Address addr = list.get(0);
                Log.d("LOC",addr.getAddressLine(0));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
