package com.joaquinalan.rotationtest.test;

import com.joaquinalan.rotationtest.model.RangeTester;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joaquinalan on 16/06/2017.
 */
public class RangeTesterTest {
    @Test
    public void isRange() throws Exception {
        assertTrue(RangeTester.isRange(-45, -90, 10));
    }

}