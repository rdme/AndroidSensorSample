package com.websarva.wings.android.sensorsample.pressure;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.websarva.wings.android.sensorsample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PressureSensorFragment extends Fragment implements PressureSensorManager.PressureSensorManagerListener {
    Context mContext;
    TextView textViewX;

    PressureSensorManager mManager;

    public PressureSensorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mManager.registerSensor(mContext,this);

        View view = inflater.inflate(R.layout.fragment_pressure_sensor, container, false);
        textViewX = (TextView) view.findViewById(R.id.text_view);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mManager = new PressureSensorManager();
    }

    @Override
    public void onPause() {
        super.onPause();
        mManager.unregisterSensor();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onPressureSensorValueChanged(float value) {
        textViewX.setText(String.valueOf(value));
    }
}

