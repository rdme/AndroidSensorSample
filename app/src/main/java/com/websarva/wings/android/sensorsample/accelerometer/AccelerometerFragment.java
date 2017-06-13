package com.websarva.wings.android.sensorsample.accelerometer;


import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.websarva.wings.android.sensorsample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccelerometerFragment extends Fragment implements AccelerometerManager.AccelerometerManagerListener {
    Context mContext;
    TextView textViewX;
    TextView textViewY;
    TextView textViewZ;

    SeekBar seekBarX;
    SeekBar seekBarY;
    SeekBar seekBarZ;

    public AccelerometerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        AccelerometerManager.getInstance().registerSensor(mContext,this);

        View view = inflater.inflate(R.layout.fragment_accelerometer, container, false);
        textViewX = (TextView) view.findViewById(R.id.text_view_X);
        textViewY = (TextView) view.findViewById(R.id.text_view_Y);
        textViewZ = (TextView) view.findViewById(R.id.text_view_Z);

        seekBarX = (SeekBar) view.findViewById(R.id.seek_bar_x);
        seekBarY = (SeekBar) view.findViewById(R.id.seek_bar_y);
        seekBarZ = (SeekBar) view.findViewById(R.id.seek_bar_z);

        seekBarX.setMax(200);
        seekBarY.setMax(200);
        seekBarZ.setMax(200);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        AccelerometerManager.getInstance().unregisterSensor();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onAccelerometerValueChanged(float x, float y, float z) {
//        Log.d("DEBUG","x:" + String.valueOf(x) + " y:" + String.valueOf(y) + " z:" + String.valueOf(z));
        textViewX.setText(String.valueOf(x));
        textViewY.setText(String.valueOf(y));
        textViewZ.setText(String.valueOf(z));

        seekBarX.setProgress((int)((x*10)+100), true);
        seekBarY.setProgress((int)((y*10)+100), true);
        seekBarZ.setProgress((int)((z*10)+100), true);

        Log.d("SENSOR",String.valueOf(x));
    }
}
