package com.websarva.wings.android.sensorsample.light;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.websarva.wings.android.sensorsample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LightSensorFragment extends Fragment implements LightSensorManager.LightSensorManagerListener {
    Context mContext;
    TextView textViewX;
    SeekBar seekBar;

    LightSensorManager mManager;

    public LightSensorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mManager.registerSensor(mContext,this);

        View view = inflater.inflate(R.layout.fragment_light_sensor, container, false);
        textViewX = (TextView) view.findViewById(R.id.text_view);
        seekBar = (SeekBar) view.findViewById(R.id.seek_bar);

        seekBar.setMax(2000);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mManager = new LightSensorManager();
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
    public void onLightSensorValueChanged(float value) {
        textViewX.setText(String.valueOf(value));

        seekBar.setProgress((int)value);
    }
}

