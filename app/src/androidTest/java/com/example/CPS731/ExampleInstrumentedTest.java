package com.example.CPS731;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.junit.Assert.assertEquals;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void test1() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Log.d("tester",appContext.getPackageName());
        assertEquals("com.example.CPS731", appContext.getPackageName());

    }
    @Test
    //test login into the app
    public void test2() {

        onView(withId(R.id.emailogin_edittext)).perform(typeText("kirtanpatel604@gmail.com"));
        onView(withId(R.id.password_edittext)).perform(typeText("RyeUni"));
        onView(withId(R.id.emailogin_edittext)).check(matches(withText("kirtanpatel604@gmail.com")));
        onView(withId(R.id.button)).perform(click());
    }

    @Test
    //test adding item use case
    public void test3() {

        onView(withId(R.id.Caloriecounter)).perform(click());
        onView(withId(R.id.DBEditText)).perform(typeText("Maggi")) ;
        onView(withId(R.id.DBcal)).perform(typeText("512"));
        onView(withId(R.id.DBbtnAdd)).perform(click());
    }



}