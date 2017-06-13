package com.websarva.wings.android.sensorsample.gyroscope;


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
public class GyroscopeFragment extends Fragment implements GyroscopeManager.GyroscopeManagerListener {
        Context mContext;
        TextView textViewX;
        TextView textViewY;
        TextView textViewZ;

        SeekBar seekBarX;
        SeekBar seekBarY;
        SeekBar seekBarZ;

        GyroscopeManager mManager;

        public GyroscopeFragment() {
            // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            mManager.registerSensor(mContext,this);

            View view = inflater.inflate(R.layout.fragment_gyroscope, container, false);
            textViewX = (TextView) view.findViewById(R.id.text_view_X);
            textViewY = (TextView) view.findViewById(R.id.text_view_Y);
            textViewZ = (TextView) view.findViewById(R.id.text_view_Z);

            seekBarX = (SeekBar) view.findViewById(R.id.seek_bar_x);
            seekBarY = (SeekBar) view.findViewById(R.id.seek_bar_y);
            seekBarZ = (SeekBar) view.findViewById(R.id.seek_bar_z);

            seekBarX.setMax(20);
            seekBarY.setMax(20);
            seekBarZ.setMax(20);

            return view;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mManager = new GyroscopeManager();
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
        public void onGyroscopeValueChanged(float x, float y, float z) {
            textViewX.setText(String.valueOf(x));
            textViewY.setText(String.valueOf(y));
            textViewZ.setText(String.valueOf(z));

            seekBarX.setProgress((int)Math.abs(x),true);
            seekBarY.setProgress((int)Math.abs(y),true);
            seekBarZ.setProgress((int)Math.abs(z),true);
        }
    }

