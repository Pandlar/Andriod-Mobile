package com.example.sonja.ui;

import com.example.sonja.ui.HttpTest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void httpTest(){
        HttpTest http = new HttpTest();
        try {
            String json = http.sendGet();
            JSONArray arr = new JSONArray(json);
            System.out.println(arr.getJSONObject(2).getString("time"));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void httpPOst(){
        HttpTest http = new HttpTest();
        try{
            http.postRequest("",11,11,11,11, NeueFahrt1.RequestRole.DRIVER,"towards Home");
        }catch(Exception e){

        }

    }
    @Test
    public void httpTime(){
        Date date = new Date();
        HttpTest http = new HttpTest();
        System.out.println(date);
    }
}

