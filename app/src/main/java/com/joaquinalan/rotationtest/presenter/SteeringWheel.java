package com.joaquinalan.rotationtest.presenter;

/**
 * Created by joaquinalan on 16/06/2017.
 */

public class SteeringWheel {
    private String mRobotState;
    private int mSteeringWheelState;
    private double mAxisXAngle;
    private double mAxisYAngle;
    private int mTranslationState;
    private int mRotationState;

    private final double CONVERSION_RADIANS_TO_DEGREES = 180.00 / Math.PI;
    private final int BACKWARD = 1;
    private final int NOT_TRANSLATE = 2;
    private final int FORWARD = 3;

    private final int TURN_RIGHT = 1;
    private final int NOT_ROTATE = 2;
    private final int TURN_LEFT = 3;

    private final int ROBOT_BACKWARD_RIGHT = 1;
    private final int ROBOT_BACKWARD = 2;
    private final int ROBOT_BACKWARD_LEFT = 3;
    private final int ROBOT_TURN_RIGHT = 4;
    private final int ROBOT_STOP = 5;
    private final int ROBOT_TURN_LEFT = 6;
    private final int ROBOT_FORWARD_RIGHT = 7;
    private final int ROBOT_FORWARD = 8;
    private final int ROBOT_FORWARD_LEFT = 9;

    public SteeringWheel(float[] mOrientationAngles) {
        mAxisXAngle = mOrientationAngles[1] * CONVERSION_RADIANS_TO_DEGREES;
        mAxisYAngle = mOrientationAngles[2] * CONVERSION_RADIANS_TO_DEGREES;
        calculateStates();
    }

    private void calculateStates() {
        MotionState translationState = new TranslationState(mAxisYAngle);
        MotionState rotationState = new RotationState(mAxisXAngle);
        if (translationState.getMotionState() == BACKWARD && rotationState.getMotionState() == TURN_RIGHT) {
            mSteeringWheelState = ROBOT_BACKWARD_RIGHT;
        } else if (translationState.getMotionState() == BACKWARD && rotationState.getMotionState() == NOT_ROTATE) {
            mSteeringWheelState = ROBOT_BACKWARD;
        } else if (translationState.getMotionState() == BACKWARD && rotationState.getMotionState() == TURN_LEFT) {
            mSteeringWheelState = ROBOT_BACKWARD_LEFT;
        } else if (translationState.getMotionState() == NOT_TRANSLATE && rotationState.getMotionState() == TURN_RIGHT) {
            mSteeringWheelState = ROBOT_TURN_RIGHT;
        } else if (translationState.getMotionState() == NOT_TRANSLATE && rotationState.getMotionState() == NOT_ROTATE) {
            mSteeringWheelState = ROBOT_STOP;
        } else if (translationState.getMotionState() == NOT_TRANSLATE && rotationState.getMotionState() == TURN_LEFT) {
            mSteeringWheelState = ROBOT_TURN_LEFT;
        } else if (translationState.getMotionState() == FORWARD && rotationState.getMotionState() == TURN_RIGHT) {
            mSteeringWheelState = ROBOT_FORWARD_RIGHT;
        } else if (translationState.getMotionState() == FORWARD && rotationState.getMotionState() == NOT_ROTATE) {
            mSteeringWheelState = ROBOT_FORWARD;
        } else if (translationState.getMotionState() == FORWARD && rotationState.getMotionState() == TURN_LEFT) {
            mSteeringWheelState = ROBOT_FORWARD_LEFT;
        }
    }

    public int getSteeringWheelState() {
        return mSteeringWheelState;
    }
}