package com.joaquinalan.rotationtest.service;

import android.content.Context;

/**
 * Created by joaquinalan on 18/06/2017.
 */

public interface SteeringWheelListener {
    Context getContext();

    void onSteeringWheelChanged(int steeringWheelState);
}
