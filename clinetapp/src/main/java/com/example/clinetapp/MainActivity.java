package com.example.clinetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.dullard.IToastService;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "clientapp";
    Button clientButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clientButton = findViewById(R.id.client);

        clientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("com.dullard.test.openSerivce");
                intent.setComponent(new ComponentName("com.example.dullard","com.example.dullard.ProvideService"));
                boolean bindResult = getApplicationContext().bindService(intent, new MyConnection(), BIND_AUTO_CREATE);
                Log.d(TAG, "bind 结果：" + bindResult);
            }
        });
    }

    class MyConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IToastService mIService = IToastService.Stub.asInterface(service);
            try {
                mIService.showToast(5);
                Log.d(TAG, "onServiceConnected");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}