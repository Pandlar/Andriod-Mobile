package com.example.sonja.ui;

import com.example.sonja.ui.HttpTest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    public ExampleUnitTest(){

    }

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
            System.out.println(arr.length());
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
    public void httppost(){
        HttpTest http = new HttpTest();
        /**
         * earliestDepartureTime=00:00:00&latestArrivalTime=00:00:00&direction=towards Office&role=driver&status=not answered&userId=01c62ef0-84ff-11e8-adc0-fa7ae01bbebc&earliestDepartureTime=00:00:00&date=2018-7-11
         */
        try{
            http.postRequest("",0,0,0,0, NeueFahrt1.RequestRole.DRIVER,"towards Home");
            http.postRequest("",0,0,0,0, NeueFahrt1.RequestRole.DRIVER,"towards Home");
            http.postRequest("",0,0,0,0, NeueFahrt1.RequestRole.DRIVER,"towards Home");
            http.postRequest("",0,0,0,0, NeueFahrt1.RequestRole.DRIVER,"towards Home");
        }catch(Exception e){

        }

    }

    @Test
    public void httpTime(){
        Date date = new Date();
        HttpTest http = new HttpTest();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH)+1;
        int day = Calendar.getInstance().get(Calendar.DATE);
        System.out.println(year+"-"+month+"-"+day);
    }

}

