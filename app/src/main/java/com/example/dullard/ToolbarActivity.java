package com.example.dullard;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Message;
import android.telephony.CellInfo;
import android.telephony.CellInfoLte;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import java.lang.reflect.Method;
import java.util.List;


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
            telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            getNetworkType();
            getCellinfo();
        } catch (Exception e) {
            Log.d(TAG, "exception is " + e.toString());
        }

    }

    public void getNetworkType() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
            Log.d(TAG, "no permission to getNetworkType");
        }
        textView.setText(Integer.toString(telephonyManager.getDataNetworkType()));
    }


    public void getCellinfo() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_ACCESS_FINE_LOCATION);
        }
        List<CellInfo> cellinfos = telephonyManager.getAllCellInfo();
        cellinfoTextView.setText(cellinfos.toString());

        for (int i = 0; i < cellinfos.size(); i++){
            CellInfo info = cellinfos.get(i);
//            if(info instanceof CellInfoLte){
                Log.d(TAG,info.toString());
//            }
        }
    }
}

