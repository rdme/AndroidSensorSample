package com.websarva.wings.android.sensorsample.base;


import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.websarva.wings.android.sensorsample.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseSensorSampleFragment extends Fragment implements BaseSensorFacade.SensorFacadeListener{

    private Context mContext;
    private TextView mTextView;
    private BaseSensorFacade mSensorFacade;
    private int mSensorType;


    public BaseSensorSampleFragment() {
        // Required empty public constructor
    }

    public static BaseSensorSampleFragment newInstance(int sensorType) {
        Bundle args = new Bundle();

        BaseSensorSampleFragment fragment = new BaseSensorSampleFragment();
        fragment.setArguments(args);
        fragment.mSensorType = sensorType;

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_base_sensor_sample, container, false);
        mTextView = (TextView)view.findViewById(R.id.text_view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); mSensorFacade = new BaseSensorFacade();
        mSensorFacade.registerSensor(mContext,this,mSensorType, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onSensorValueChanged(float[] values) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < values.length ; i++) {
            builder.append("value");
            builder.append(i);
            builder.append(": ");
            builder.append(values[i]);
            builder.append("\n");
        }

        mTextView.setText(builder.toString());
    }
}
