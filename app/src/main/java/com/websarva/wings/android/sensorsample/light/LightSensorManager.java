package com.websarva.wings.android.sensorsample.light;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

import com.websarva.wings.android.sensorsample.base.BaseSensorFacade;

/**
 * Created by kawate on 2017/06/07.
 */

public class LightSensorManager implements BaseSensorFacade.SensorFacadeListener{

    private LightSensorManagerListener mListener;
    private BaseSensorFacade mSensorFacade;

    public void registerSensor(Context context, LightSensorManagerListener listener) {
        mSensorFacade = new BaseSensorFacade();
        mListener = listener;
        mSensorFacade.registerSensor(context,
                this,
                Sensor.TYPE_LIGHT,
                SensorManager.SENSOR_DELAY_NORMAL);
        Log.d("DEBUG", "Start LightSensor");
    }

    public void unregisterSensor() {
        mSensorFacade.unregisterSensor();
        Log.d("DEBUG", "Stop LightSensor");
    }

    @Override
    public void onSensorValueChanged(float[] values) {
        mListener.onLightSensorValueChanged(values[0]);
    }

    public interface LightSensorManagerListener {
        void onLightSensorValueChanged(float value);
    }
}

