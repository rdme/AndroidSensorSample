package com.websarva.wings.android.sensorsample;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.websarva.wings.android.sensorsample.accelerometer.AccelerometerFragment;
import com.websarva.wings.android.sensorsample.base.BaseSensorSampleFragment;
import com.websarva.wings.android.sensorsample.gyroscope.GyroscopeFragment;
import com.websarva.wings.android.sensorsample.light.LightSensorFragment;
import com.websarva.wings.android.sensorsample.magneticfield.MagneticFieldFragment;
import com.websarva.wings.android.sensorsample.pressure.PressureSensorFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        SensorListFragment.OnSensorItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (!(f instanceof SensorListFragment)) {
            f = new SensorListFragment();
        }
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.content_frame, f);
        t.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sensor) {

            Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_frame);
            if (!(f instanceof SensorListFragment)) {
                f = SensorListFragment.newInstance(1);
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,f).commit();
            }

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (!(f instanceof SensorListFragment)) {
            f = new SensorListFragment();
        }
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        t.replace(R.id.content_frame, f);
        t.commit();
    }

    // センサーを選択したときのコールバック
    @Override
    public void onSelectedSensorItem(int sensorType) {
        Log.d("Debug","SENSOR_TYPE : " + sensorType);
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER: // 加速度
            {
                setTitle("Accelerometer");
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_frame);
                if (!(f instanceof AccelerometerFragment)) {
                    f = new AccelerometerFragment();
                }
                FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                t.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                t.replace(R.id.content_frame, f);
                t.commit();
            } break;
            case Sensor.TYPE_MAGNETIC_FIELD: // 磁界
            {
                setTitle("Magnetic Field");
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_frame);
                if (!(f instanceof MagneticFieldFragment)) {
                    f = new MagneticFieldFragment();
                }
                FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                t.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                t.replace(R.id.content_frame, f);
                t.commit();
            } break;
            case Sensor.TYPE_GYROSCOPE: // ジャイロ
            {
                setTitle("Gyroscope");
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_frame);
                if (!(f instanceof GyroscopeFragment)) {
                    f = new GyroscopeFragment();
                }
                FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                t.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                t.replace(R.id.content_frame, f);
                t.commit();
            } break;
            case Sensor.TYPE_LIGHT: // 輝度
            {
                setTitle("Light Sensor");
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_frame);
                if (!(f instanceof LightSensorFragment)) {
                    f = new LightSensorFragment();
                }
                FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                t.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                t.replace(R.id.content_frame, f);
                t.commit();
            } break;
            case Sensor.TYPE_PRESSURE: // 気圧
            {
                setTitle("Pressure Sensor");
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_frame);
                if (!(f instanceof PressureSensorFragment)) {
                    f = new PressureSensorFragment();
                }
                FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                t.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                t.replace(R.id.content_frame, f);
                t.commit();
            } break;
            default:
            {
                setTitle("SensorTypeID:" + String.valueOf(sensorType));
                Fragment f = BaseSensorSampleFragment.newInstance(sensorType);
                FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                t.replace(R.id.content_frame, f);
                t.commit();
            } break;
        }
    }
}
