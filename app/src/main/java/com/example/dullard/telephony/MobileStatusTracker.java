package com.example.dullard.telephony;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.CellSignalStrength;
import android.telephony.CellSignalStrengthLte;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.TelephonyCallback;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.dullard.Factory.cellinfoFactory;

public class MobileStatusTracker {

    private final String TAG = "MobileStatusTracker";

    private Context mContext;
    private TelephonyManager telephonyManager;

    private final SignalTelephonyCallback mTelephonyCallback = new SignalTelephonyCallback();

    public MobileStatusTracker(Context context) {
        mContext = context;
        telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public void getCellinfo() {
        List<Map<String, String>> cellInfos = new ArrayList<>();

        List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();

        for (CellInfo cellinfo : allCellInfo) {
            Log.d(TAG, "cellinfo is " + cellinfo);
//            Map<String, String> map = new HashMap<>();
//            cellinfoFactory.addCellInfo(map, cellinfo);
//            cellInfos.add(map);
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    public class SignalTelephonyCallback extends TelephonyCallback implements TelephonyCallback.SignalStrengthsListener ,
            TelephonyCallback.ServiceStateListener {

        @Override
        public void onServiceStateChanged(@NonNull ServiceState serviceState) {
            ServiceState ss = serviceState;
            int voiceState = ss.getState();
        }

        @Override
        public void onSignalStrengthsChanged(@NonNull SignalStrength signalStrength) {
            SignalStrength signal = signalStrength;
            List<CellSignalStrength> signalStrengths = signal.getCellSignalStrengths();

            for (CellSignalStrength cellSignalStrength:signalStrengths) {
                if (cellSignalStrength instanceof CellSignalStrengthLte) {
                    int lte_rsrp = ((CellSignalStrengthLte) cellSignalStrength).getRsrp();
                    int lte_level = cellSignalStrength.getLevel();
                    Log.d(TAG, "lte rsrp " + lte_rsrp + " level " + lte_level);
                }
            }
        }
    }

    public void startListening() {
        telephonyManager.registerTelephonyCallback(mContext.getMainExecutor(), mTelephonyCallback);
        Log.d(TAG, "start listening");
    }

}
