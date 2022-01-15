package com.example.dullard;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class ProvideService extends Service {

    private final String TAG = "ProvideService";
    public ProvideService() {
    }

    private class MyBinder extends IToastService.Stub {

        Context context = getApplicationContext();
        @Override
        public void showToast(int times){
            try {
                Log.d(TAG, "service toast");
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Server say hello world " + times + " times!", Toast.LENGTH_LONG).show();
                        Log.d(TAG,"toast success");
                    }
                });
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }

        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
}