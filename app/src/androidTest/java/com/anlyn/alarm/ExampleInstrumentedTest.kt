package com.anlyn.alarm

import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.anlyn.alarm.presentation.ui.ringingalarm.RingingActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@MediumTest
@RunWith(AndroidJUnit4ClassRunner::class)
public class ExampleInstrumentedTest {

    @get:Rule
    public val rule= ActivityTestRule<RingingActivity>(RingingActivity::class.java,true,false)

    @Test
    public fun useAppContext() {
        // Context of the app under test.
        rule.launchActivity(Intent())
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.anlyn.alarm", appContext.packageName)
    }
}