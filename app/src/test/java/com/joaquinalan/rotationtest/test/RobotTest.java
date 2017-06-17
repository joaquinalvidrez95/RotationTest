package com.joaquinalan.rotationtest.test;

import com.joaquinalan.rotationtest.model.steeringwheel.Robot;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by joaquinalan on 16/06/2017.
 */
public class RobotTest {
    @Test
    public void getRobotState() throws Exception {
        Robot robot = new Robot(5);
        assertEquals("Robot stopped", robot.getRobotState());
    }

}