package com.websarva.wings.android.sensorsample.pressure;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

import com.websarva.wings.android.sensorsample.base.BaseSensorFacade;

/**
 * Created by kawate on 2017/06/07.
 */

public class PressureSensorManager  implements BaseSensorFacade.SensorFacadeListener {
    private PressureSensorManagerListener mListener;
    private BaseSensorFacade mSensorFacade;

    public void registerSensor(Context context, PressureSensorManagerListener listener) {
        mSensorFacade = new BaseSensorFacade();
        mListener = listener;
        mSensorFacade.registerSensor(context,
                this,
                Sensor.TYPE_PRESSURE,
                SensorManager.SENSOR_DELAY_NORMAL);
        Log.d("DEBUG", "Start PressureSensor");
    }

    public void unregisterSensor() {
        mSensorFacade.unregisterSensor();
        Log.d("DEBUG", "Stop PressureSensor");
    }

    @Override
    public void onSensorValueChanged(float[] values) {
        mListener.onPressureSensorValueChanged(values[0]);
    }

    public interface PressureSensorManagerListener {
        void onPressureSensorValueChanged(float value);
    }
}

