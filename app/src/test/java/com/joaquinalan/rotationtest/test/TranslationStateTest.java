package com.joaquinalan.rotationtest.test;

import com.joaquinalan.rotationtest.model.MotionState;
import com.joaquinalan.rotationtest.model.TranslationState;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by joaquinalan on 16/06/2017.
 */
public class TranslationStateTest {
    @Test
    public void getMotionState() throws Exception {
        TranslationState translationState = new TranslationState(-45.00);
        assertEquals(MotionState.NOT_TRANSLATE, translationState.getMotionState());
    }

}