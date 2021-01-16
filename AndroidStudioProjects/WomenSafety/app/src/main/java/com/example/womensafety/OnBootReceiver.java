package com.example.womensafety;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class OnBootReceiver extends BroadcastReceiver {
    Sensor sensor;
    @Override
    public void onReceive(Context context, Intent intent){
        SensorManager sManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sManager.registerListener((SensorEventListener) new ShakeEventListener(), sensor, SensorManager.SENSOR_DELAY_NORMAL); // or other delay
    }
}