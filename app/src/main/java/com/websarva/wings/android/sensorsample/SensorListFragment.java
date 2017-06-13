package com.websarva.wings.android.sensorsample;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.websarva.wings.android.sensorsample.sensoritem.SensorItemContent;
import com.websarva.wings.android.sensorsample.sensoritem.SensorItemContent.SensorItem;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class SensorListFragment extends Fragment implements MySensorListItemRecyclerViewAdapter.OnListFragmentInteractionListener {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    private SensorManager mSensorManager;
    private OnSensorItemSelectedListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SensorListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SensorListFragment newInstance(int columnCount) {
        SensorListFragment fragment = new SensorListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensorlistitem_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            // センサーマネージャーからセンサーのリストを取得してSensorItemのリストを作成するそんでAdapterに入れる

            List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

            SensorItemContent.removeContent();

            for (Sensor sensor : sensors) {
                SensorItemContent.addContent(sensor.getName(),sensor.getType());
            }
            recyclerView.setAdapter(new MySensorListItemRecyclerViewAdapter(SensorItemContent.ITEMS, this));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        if (context instanceof OnSensorItemSelectedListener) {
            mListener = (OnSensorItemSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    // リストセルタップ時の挙動
    public void onListFragmentInteraction(SensorItem item) {
        mListener.onSelectedSensorItem(item.type);

        Sensor sensor = mSensorManager.getDefaultSensor(item.type);
        if (null != sensor) {
            Log.d("","Selected Sensor? : " + sensor.getName());
        }
    }

    /**
     * Activityにコールバックするためのリスナを定義
     */
    public interface OnSensorItemSelectedListener {
        void onSelectedSensorItem(int sensorType);
    }
}
