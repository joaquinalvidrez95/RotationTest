package com.joaquinalan.rotationtest.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.joaquinalan.rotationtest.R;
import com.joaquinalan.rotationtest.presenter.MainPresenter;
import com.joaquinalan.rotationtest.presenter.MainPresenterImpl;
import com.joaquinalan.rotationtest.service.AngleServiceConstants;
import com.joaquinalan.rotationtest.service.SteeringWheelListener;
import com.joaquinalan.rotationtest.service.SteeringWheelSensor;
import com.joaquinalan.rotationtest.service.SteeringWheelSensorImpl;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener, SteeringWheelListener {
    private TextView mTextViewAxisX;
    private TextView mTextViewAxisY;
    private TextView mTextViewAxisZ;
    private TextView mTextViewRobotState;
    private TextView mTextViewSteeringWheelState;
    private Button mButtonStart;
    private Button mButtonStop;
    private MainPresenter mMainPresenter;

    private BroadcastSensor broadcastSensor;

    private SteeringWheelSensor mSteeringWheelSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewAxisX = (TextView) findViewById(R.id.textview_main_axisx);
        mTextViewAxisY = (TextView) findViewById(R.id.textview_main_axisy);
        mTextViewAxisZ = (TextView) findViewById(R.id.textview_main_axisz);
        mTextViewRobotState = (TextView) findViewById(R.id.textview_main_robot_state);
        mTextViewSteeringWheelState = (TextView) findViewById(R.id.textview_main_steering_wheel_state);
        mTextViewAxisZ = (TextView) findViewById(R.id.textview_main_axisz);

        mButtonStart = (Button) findViewById(R.id.button_main_start);
        mButtonStop = (Button) findViewById(R.id.button_main_stop);

        mButtonStart.setOnClickListener(this);
        mButtonStop.setOnClickListener(this);

        mMainPresenter = new MainPresenterImpl(this);

        IntentFilter intentFilter = new IntentFilter();
        broadcastSensor = new BroadcastSensor();
        registerReceiver(broadcastSensor, intentFilter);

        mSteeringWheelSensor = new SteeringWheelSensorImpl(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Get updates from the accelerometer and magnetometer at a constant rate.
        // To make batch operations more efficient and reduce power consumption,
        // provide support for delaying updates to the application.
        //
        // In this example, the sensor reporting delay is small enough such that
        // the application receives an update before the system checks the sensor
        // readings again.
        mMainPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastSensor);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMainPresenter.onPause();
    }

    @Override
    public void displayAngleY(String angle) {
        mTextViewAxisY.setText(angle);
    }

    @Override
    public void displayAngleX(String angle) {
        mTextViewAxisX.setText(angle);
    }

    @Override
    public void displayAngleZ(String angle) {
        mTextViewAxisZ.setText(angle);
    }

    @Override
    public void displayRobotState(String robotState) {
        mTextViewRobotState.setText(robotState);
    }

    @Override
    public void displaySteeringWheelState(String steeringWheelState) {
        mTextViewSteeringWheelState.setText(steeringWheelState);
    }

    @Override
    public void startSensorService() {
        //startService(new Intent(this, SteeringWheelSensorImpl.class));
        mSteeringWheelSensor.start();
    }

    @Override
    public void stopSensorService() {
        //stopService(new Intent(this, SteeringWheelSensorImpl.class));
        mSteeringWheelSensor.stop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_main_start:
                mMainPresenter.onButtonStartClicked();
                break;
            case R.id.button_main_stop:
                mMainPresenter.onButtonStopClicked();
                break;
        }
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void onSteeringWheelChanged(int steeringWheelState) {
        mTextViewAxisX.setText(String.valueOf(steeringWheelState));
    }

    public class BroadcastSensor extends BroadcastReceiver {
        public static final String PROCESS_RESPONSE = "com.as400samplecode.intent.action.PROCESS_RESPONSE";

        @Override
        public void onReceive(Context context, Intent intent) {
            float[] orientationAngles = intent.getFloatArrayExtra(AngleServiceConstants.ANGLE_SENSOR);
            String sensor = String.valueOf(orientationAngles[1]);
            //mTextViewAxisX.setText(sensor);
        }
    }
}
