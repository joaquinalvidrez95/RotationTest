package com.joaquinalan.rotationtest.service;

/**
 * Created by joaquinalan on 18/06/2017.
 */

public interface SteeringWheelSensor {
    int getSteeringWheelState();

    void stop();

    void start();
}
