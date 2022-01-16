package com.example.dullard.Factory;

import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.util.Log;

import java.util.Map;

public class cellinfoFactory {

    private static final String TAG = "cellinfoFactory";

    public static void addCellInfo(Map<String, String> map, CellInfo cellInfo) {
        if(cellInfo instanceof CellInfoGsm) {
            addCellInfo(map, (CellInfoGsm)cellInfo);
        } else if(cellInfo instanceof CellInfoWcdma) {
            addCellInfo(map, (CellInfoWcdma)cellInfo);
        } else if(cellInfo instanceof CellInfoLte) {
            addCellInfo(map, (CellInfoLte)cellInfo);
        } else if(cellInfo instanceof CellInfoCdma) {
            addCellInfo(map, (CellInfoCdma)cellInfo);
        } else {
            Log.w(TAG, "Unsupported type of cellInfo.");
        }
    }
}
