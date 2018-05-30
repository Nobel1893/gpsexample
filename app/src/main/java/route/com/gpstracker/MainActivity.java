package route.com.gpstracker;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATIONREQUESTCODE = 20;
    MyLocationProvider locationProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    //request permession
                    // Permission is not granted
                    Toast.makeText(MainActivity.this,"permession not granted",Toast.LENGTH_LONG).show();
                    requestLocationPermession();
                }else {
                    //do your action

                    Permessiongranted();
                    Toast.makeText(MainActivity.this,"permession granted",Toast.LENGTH_LONG).show();

                }
            }
        });

        startActivity(new Intent(this,UsersList.class));

    }


    public void Permessiongranted(){
        locationProvider=new MyLocationProvider(this);

        if (locationProvider.canGetLocation()){
            Toast.makeText(this,"can get location from provider",Toast.LENGTH_LONG).show();
            TextView view=findViewById(R.id.text);
            view.setText("lat : "+locationProvider.getLatitude()+" \n long : "+locationProvider.getLongitude());
        }else {
            Toast.makeText(this,"cann't get location from provider",Toast.LENGTH_LONG).show();

        }
    }

     public void requestLocationPermession(){
         // Permission is not granted
         // Should we show an explanation?
         if (ActivityCompat.
                 shouldShowRequestPermissionRationale(MainActivity.this,
                 Manifest.permission.ACCESS_FINE_LOCATION)) {
             // Show an explanation to the user *asynchronously* -- don't block
             // this thread waiting for the user's response! After the user
             // sees the explanation, try again to request the permission.
             Toast.makeText(MainActivity.this,"show explanation",Toast.LENGTH_LONG).show();

             AlertDialog.Builder builder=new AlertDialog.Builder(this);
             builder.setMessage("please let me know your location");
             builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                     ActivityCompat.requestPermissions(MainActivity.this,
                             new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                             LOCATIONREQUESTCODE);

                 }
             });
             builder.create().show();

         } else {
             // No explanation needed; request the permission
             ActivityCompat.requestPermissions(MainActivity.this,
                     new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                     LOCATIONREQUESTCODE);

             // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
             // app-defined int constant. The callback method gets the
             // result of the request.
         }
     }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LOCATIONREQUESTCODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    //do your action
                    Toast.makeText(MainActivity.this,"user granted permession",Toast.LENGTH_LONG).show();
                    Permessiongranted();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    //don't make your action
                    Toast.makeText(MainActivity.this,"user denied permession",Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

}
