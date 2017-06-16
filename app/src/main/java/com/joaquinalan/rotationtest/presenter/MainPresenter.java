package com.joaquinalan.rotationtest.presenter;

import android.app.Activity;
import android.content.Context;

/**
 * Created by joaquinalan on 16/06/2017.
 */

public interface MainPresenter {

    void onPause();

    void onResume();

    Context getContext();

    void onSensorChanged(float[] mOrientationAngles);
}
