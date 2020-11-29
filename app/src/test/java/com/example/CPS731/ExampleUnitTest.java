package com.example.CPS731;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private dblayout lay;
    private MainAdapter adapt;
    @Before
    public void setUp() {
        lay = new dblayout();
        adapt = new MainAdapter();
    }
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
}
    @Test
    //testing adding calories use case
    public  void addCalories () {
        double result =  lay.sumTotal(1,10);
        double expected =11;
            assertThat(result, is(expected));
    }
    @Test
    public void updateCalories () {
        double result = adapt.upd(10);
        double expected = 10;
       // Log.d("tester",result +"" + expected);
        assertThat(result, is(expected));
    }

}