package com.joaquinalan.rotationtest.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.joaquinalan.rotationtest.R;
import com.joaquinalan.rotationtest.presenter.MainPresenter;
import com.joaquinalan.rotationtest.presenter.MainPresenterImpl;

public class MainActivity extends AppCompatActivity implements MainView {
    private TextView mTextViewAxisX;
    private TextView mTextViewAxisY;
    private TextView mTextViewAxisZ;

    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewAxisX = (TextView) findViewById(R.id.textview_main_axisx);
        mTextViewAxisY = (TextView) findViewById(R.id.textview_main_axisy);
        mTextViewAxisZ = (TextView) findViewById(R.id.textview_main_axisz);

        mMainPresenter = new MainPresenterImpl(this);
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
    public Context getContext() {
        return getApplicationContext();
    }
}
