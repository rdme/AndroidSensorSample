package com.websarva.wings.android.sensorsample.base;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

/**
 * Created by kawate on 2017/06/02.
 */

public class BaseSensorFacade implements SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private SensorFacadeListener mListener;

    private void initializeSensor(Context context, int sensorType) {
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(sensorType);
    }

    public void registerSensor(Context context, SensorFacadeListener listener, int sensorType, int samplingPeriod) {
        initializeSensor(context, sensorType);
        mListener = listener;
        mSensorManager.registerListener(this,mSensor,samplingPeriod);
        Log.d("DEBUG", "Start Accelerometer");
    }

    public void unregisterSensor() {
        mSensorManager.unregisterListener(this,mSensor);
        Log.d("DEBUG", "Stop Accelerometer");
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        mListener.onSensorValueChanged(sensorEvent.values);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public interface SensorFacadeListener {
        void onSensorValueChanged(float[] values);
    }
}
