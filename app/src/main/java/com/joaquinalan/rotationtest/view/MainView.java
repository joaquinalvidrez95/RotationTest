package com.joaquinalan.rotationtest.view;

/**
 * Created by joaquinalan on 15/06/2017.
 */

public interface MainView {
    void displayAngleY(String angle);

    void displayAngleX(String angle);

    void displayAngleZ(String angle);

    void displayRobotState(String robotState);

    void displaySteeringWheelState(String steeringWheelState);

    void startSensorService();

    void stopSensorService();
}
