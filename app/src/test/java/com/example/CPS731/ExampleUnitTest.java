package com.example.CPS731;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private dblayout lay;
    @Before
    public void setUp() {
        lay = new dblayout();
    }
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    //testing adding calories use case
    public  void addCalories () {

    }
}