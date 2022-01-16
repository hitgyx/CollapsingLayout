package com.example.dullard;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.example.dullard.telephony.MobileStatusTracker;


public class ToolbarActivity extends AppCompatActivity {
    String TAG = "networkinfo";

    public final static int REQUEST_READ_PHONE_STATE = 1;
    public final static int REQUEST_ACCESS_FINE_LOCATION = 2;

    private TextView textView;
    private TextView cellinfoTextView;
    private TelephonyManager telephonyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        textView = findViewById(R.id.toolbar_title);
        cellinfoTextView = findViewById(R.id.signal);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        try {
            MobileStatusTracker mobileStatusTracker = new MobileStatusTracker(getApplicationContext());
            mobileStatusTracker.startListening();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_ACCESS_FINE_LOCATION);
                return;
            }
            mobileStatusTracker.getCellinfo();
        } catch (Exception e) {
            Log.d(TAG, "exception is " + e.toString());
        }

    }

}

