package com.joaquinalan.rotationtest.interactor;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.joaquinalan.rotationtest.model.steeringwheel.ObservableSensor;
import com.joaquinalan.rotationtest.model.steeringwheel.SensorObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joaquinalan on 15/06/2017.
 */

public class AngleSensorService extends Service implements SensorEventListener, ObservableSensor {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mMagnetometer;
    private List<SensorObserver> mSensorObservers;

    private final float[] mAccelerometerReading = new float[3];
    private final float[] mMagnetometerReading = new float[3];

    private final float[] mRotationMatrix = new float[9];
    private final float[] mOrientationAngles = new float[3];

    public AngleSensorService() {
        mSensorObservers = new ArrayList<>();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == mAccelerometer) {
            System.arraycopy(event.values, 0, mAccelerometerReading,
                    0, mAccelerometerReading.length);
        } else if (event.sensor == mMagnetometer) {
            System.arraycopy(event.values, 0, mMagnetometerReading,
                    0, mMagnetometerReading.length);
        }

        updateOrientationAngles();
        for (SensorObserver sensorObserver : mSensorObservers
                ) {
            sensorObserver.onAngleChanged(mOrientationAngles);

        }
    }

    // Compute the three orientation angles based on the most recent readings from
    // the device's accelerometer and magnetometer.
    private void updateOrientationAngles() {
        // Update rotation matrix, which is needed to update orientation angles.
        SensorManager.getRotationMatrix(mRotationMatrix, null,
                mAccelerometerReading, mMagnetometerReading);

        // "mRotationMatrix" now has up-to-date information.

        SensorManager.getOrientation(mRotationMatrix, mOrientationAngles);
    }

    @Override
    public void onCreate() {
        Toast.makeText(getBaseContext(), "Service created", Toast.LENGTH_LONG).show();
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getBaseContext(), "Sampling started", Toast.LENGTH_LONG).show();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mMagnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mSensorManager.unregisterListener(this);
        Toast.makeText(getBaseContext(), "Service killed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void registerObserver(SensorObserver sensorObserver) {
        mSensorObservers.add(sensorObserver);
        //Toast.makeText(getBaseContext(), "Observer added", Toast.LENGTH_LONG).show();
    }

    @Override
    public void removeObserver(SensorObserver sensorObserver) {
        mSensorObservers.remove(sensorObserver);
        Toast.makeText(getBaseContext(), "Observer removed", Toast.LENGTH_LONG).show();
    }
}
