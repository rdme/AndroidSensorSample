package com.websarva.wings.android.sensorsample.gyroscope;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

import com.websarva.wings.android.sensorsample.base.BaseSensorFacade;

/**
 * Created by kawate on 2017/06/07.
 */

public class GyroscopeManager implements BaseSensorFacade.SensorFacadeListener {
        private GyroscopeManagerListener mListener;
        private BaseSensorFacade mSensorFacade;

        public void registerSensor(Context context, GyroscopeManagerListener listener) {
            mSensorFacade = new BaseSensorFacade();
            mListener = listener;
            mSensorFacade.registerSensor(context,
                    this,
                    Sensor.TYPE_GYROSCOPE,
                    SensorManager.SENSOR_DELAY_NORMAL);
            Log.d("DEBUG", "Start Gyroscope");
        }

        public void unregisterSensor() {
            mSensorFacade.unregisterSensor();
            Log.d("DEBUG", "Stop Gyroscope");
        }

        @Override
        public void onSensorValueChanged(float[] values) {
            mListener.onGyroscopeValueChanged(values[0],values[1],values[2]);
        }

        public interface GyroscopeManagerListener {
            void onGyroscopeValueChanged(float x, float y, float z);
        }
    }

