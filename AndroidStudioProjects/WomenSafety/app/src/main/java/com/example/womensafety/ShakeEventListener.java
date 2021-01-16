package com.example.womensafety;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.telephony.SmsManager;

public class ShakeEventListener implements SensorEventListener {
    @Override
    public void onSensorChanged(SensorEvent event) {
        SmsManager smsMgrVar = SmsManager.getDefault();
        smsMgrVar.sendTextMessage("7780498174", null, "From Women Safety:", null, null);; // see below
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
