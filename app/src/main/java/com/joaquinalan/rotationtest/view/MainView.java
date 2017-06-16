package com.joaquinalan.rotationtest.view;

import android.app.Activity;
import android.content.Context;

/**
 * Created by joaquinalan on 15/06/2017.
 */

public interface MainView {
    void displayAngleY(String angle);

    void displayAngleX(String angle);

    void displayAngleZ(String angle);

    Context getContext();
}
