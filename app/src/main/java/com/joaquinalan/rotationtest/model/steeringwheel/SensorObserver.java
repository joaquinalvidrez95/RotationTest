package com.joaquinalan.rotationtest.model.steeringwheel;

/**
 * Created by joaquinalan on 17/06/2017.
 */

public interface SensorObserver {
    void onAngleChanged(float[] orientationAngles);
}
