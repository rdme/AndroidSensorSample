package com.websarva.wings.android.sensorsample.magneticfield;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

import com.websarva.wings.android.sensorsample.base.BaseSensorFacade;

/**
 * Created by kawate on 2017/06/06.
 */

public class MagneticFieldManager implements BaseSensorFacade.SensorFacadeListener{

    private MagneticFieldManagerListener mListener;
    private BaseSensorFacade mSensorFacade;

    public void registerSensor(Context context, MagneticFieldManagerListener listener) {
        mSensorFacade = new BaseSensorFacade();
        mListener = listener;
        mSensorFacade.registerSensor(context,
                this,
                Sensor.TYPE_MAGNETIC_FIELD,
                SensorManager.SENSOR_DELAY_NORMAL);
        Log.d("DEBUG", "Start MagneticField");
    }

    public void unregisterSensor() {
        mSensorFacade.unregisterSensor();
        Log.d("DEBUG", "Stop MagneticField");
    }

    @Override
    public void onSensorValueChanged(float[] values) {
        mListener.onMagneticFieldValueChanged(values[0],values[1],values[2]);
    }

    public interface MagneticFieldManagerListener {
        void onMagneticFieldValueChanged(float x, float y, float z);
    }
}
