package com.websarva.wings.android.sensorsample.accelerometer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.websarva.wings.android.sensorsample.base.BaseSensorFacade;

/**
 * Created by kawate on 2017/06/01.
 */

public class AccelerometerManager implements BaseSensorFacade.SensorFacadeListener {
    private static AccelerometerManager INSTANCE;
    private AccelerometerManagerListener mListener;
    private BaseSensorFacade mSensorFacade;

    public static AccelerometerManager getInstance() {
        if (INSTANCE == null)  {
            INSTANCE = new AccelerometerManager();
        }
        return INSTANCE;
    }

    public void registerSensor(Context context, AccelerometerManagerListener listener) {
        mSensorFacade = new BaseSensorFacade();
        mListener = listener;
        mSensorFacade.registerSensor(context,
                this,
                Sensor.TYPE_ACCELEROMETER,
                SensorManager.SENSOR_DELAY_NORMAL);
        Log.d("DEBUG", "Start Accelerometer");
    }

    public void unregisterSensor() {
        mSensorFacade.unregisterSensor();
        Log.d("DEBUG", "Stop Accelerometer");
    }

    @Override
    public void onSensorValueChanged(float[] values) {
        mListener.onAccelerometerValueChanged(values[0],values[1],values[2]);
    }

    public interface AccelerometerManagerListener {
        void onAccelerometerValueChanged(float x, float y, float z);
    }
}
