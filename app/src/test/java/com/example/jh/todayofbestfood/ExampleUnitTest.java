package com.example.jh.todayofbestfood;

import android.app.Activity;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void GPS_테스트코드() throws Exception {
        FoodofBestParcelable foodofBestParcelable = new FoodofBestParcelable(null,null,0,"",null,null,null,null);
        System.out.println(foodofBestParcelable.get_foodImgName());

    }
}