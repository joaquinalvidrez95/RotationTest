package com.joaquinalan.rotationtest.presenter;

import com.joaquinalan.rotationtest.interactor.AngleSensorService;
import com.joaquinalan.rotationtest.model.steeringwheel.CalculatorSteeringWheelState;
import com.joaquinalan.rotationtest.model.steeringwheel.ObservableSensor;
import com.joaquinalan.rotationtest.model.steeringwheel.Robot;
import com.joaquinalan.rotationtest.model.steeringwheel.SensorObserver;
import com.joaquinalan.rotationtest.view.MainView;

/**
 * Created by joaquinalan on 16/06/2017.
 */

public class MainPresenterImpl implements MainPresenter, SensorObserver {
    private MainView mMainView;
    private ObservableSensor mObservableSensor;

    public MainPresenterImpl(MainView mainView) {
        mObservableSensor = new AngleSensorService();
        this.mMainView = mainView;
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onButtonStartClicked() {
        mMainView.startSensorService();
        mObservableSensor.registerObserver(this);
    }

    @Override
    public void onButtonStopClicked() {
        mMainView.stopSensorService();
        //mObservableSensor.removeObserver(this);
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onAngleChanged(float[] orientationAngles) {
        mMainView.displayAngleX("Axis X: " + (int) (orientationAngles[1] * 57.3));
        mMainView.displayAngleY("Axis Y: " + (int) (orientationAngles[2] * 57.3));
        mMainView.displayAngleZ("Axis -Z: " + (int) (orientationAngles[0] * 57.3));

        mMainView.displaySteeringWheelState(String.valueOf(CalculatorSteeringWheelState.calculateSteeringWheelState(orientationAngles)));
        Robot robot = new Robot(CalculatorSteeringWheelState.calculateSteeringWheelState(orientationAngles));
        mMainView.displayRobotState(robot.getRobotState());
    }
}
