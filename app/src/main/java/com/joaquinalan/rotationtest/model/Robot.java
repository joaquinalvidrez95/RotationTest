package com.joaquinalan.rotationtest.model;

/**
 * Created by joaquinalan on 16/06/2017.
 */

public class Robot {
    private final int ROBOT_BACKWARD_RIGHT = 1;
    private final int ROBOT_BACKWARD = 2;
    private final int ROBOT_BACKWARD_LEFT = 3;
    private final int ROBOT_TURN_RIGHT = 4;
    private final int ROBOT_STOP = 5;
    private final int ROBOT_TURN_LEFT = 6;
    private final int ROBOT_FORWARD_RIGHT = 7;
    private final int ROBOT_FORWARD = 8;
    private final int ROBOT_FORWARD_LEFT = 9;
    private String mRobotState;
    private int mSteeringWheelState;

    public Robot(int steeringWheelState) {
        mSteeringWheelState = steeringWheelState;
    }

    public String getRobotState() {
        switch (mSteeringWheelState) {
            case ROBOT_BACKWARD_RIGHT:
                mRobotState = "Robot moving backward and right";
            case ROBOT_BACKWARD:
                mRobotState = "Robot moving backward";
            case ROBOT_BACKWARD_LEFT:
                mRobotState = "Robot moving backward and left";
            case ROBOT_TURN_RIGHT:
                mRobotState = "Robot moving turning right";
            case ROBOT_STOP:
                mRobotState = "Robot stopped";
            case ROBOT_TURN_LEFT:
                mRobotState = "Robot turning left";
            case ROBOT_FORWARD_RIGHT:
                mRobotState = "Robot moving forward and right";
            case ROBOT_FORWARD:
                mRobotState = "Robot moving forward";
            case ROBOT_FORWARD_LEFT:
                mRobotState = "Robot moving forward and left";
        }
        return mRobotState;
    }
}
