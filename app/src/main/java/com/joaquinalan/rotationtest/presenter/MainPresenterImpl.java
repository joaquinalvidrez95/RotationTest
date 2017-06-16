package com.joaquinalan.rotationtest.presenter;

import android.content.Context;

import com.joaquinalan.rotationtest.interactor.MainInteractor;
import com.joaquinalan.rotationtest.interactor.MainInteractorImpl;
import com.joaquinalan.rotationtest.model.Robot;
import com.joaquinalan.rotationtest.model.SteeringWheel;
import com.joaquinalan.rotationtest.view.MainView;

/**
 * Created by joaquinalan on 16/06/2017.
 */

public class MainPresenterImpl implements MainPresenter {
    private MainView mMainView;
    private MainInteractor mMainInteractor;

    public MainPresenterImpl(MainView mainView) {
        this.mMainView = mainView;
        mMainInteractor = new MainInteractorImpl(this);
    }

    @Override
    public void onResume() {
        mMainInteractor.start();
    }

    @Override
    public Context getContext() {
        return mMainView.getContext();
    }

    @Override
    public void onSensorChanged(float[] mOrientationAngles) {
        mMainView.displayAngleX("Axis X: " + (int) (mOrientationAngles[1] * 57.3));
        mMainView.displayAngleY("Axis Y: " + (int) (mOrientationAngles[2] * 57.3));
        mMainView.displayAngleZ("Axis -Z: " + (int) (mOrientationAngles[0] * 57.3));

        SteeringWheel steeringWheel = new SteeringWheel(mOrientationAngles);
        mMainView.displaySteeringWheelState(String.valueOf(steeringWheel.getSteeringWheelState()));
        Robot robot = new Robot(steeringWheel.getSteeringWheelState());
        mMainView.displayRobotState(robot.getRobotState());
    }

    @Override
    public void onPause() {
        mMainInteractor.stop();
    }
}
