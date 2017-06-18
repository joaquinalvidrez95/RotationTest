package com.joaquinalan.rotationtest.service;

import android.os.Handler;
import android.os.Message;

import com.joaquinalan.rotationtest.model.steeringwheel.ObservableSensorImpl;

/**
 * Created by joaquinalan on 17/06/2017.
 */

public class AngleSensorHandler extends Handler {
    private ObservableSensorImpl mObservableSensor;

    public AngleSensorHandler(ObservableSensorImpl observableSensor) {
        this.mObservableSensor = observableSensor;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        float[] readBuf = (float[]) msg.obj;
        mObservableSensor.setAngles(readBuf);
    }
}
