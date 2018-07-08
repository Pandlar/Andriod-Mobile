package com.example.sonja.ui;

import com.example.sonja.ui.aws.HttpTest;

import org.json.JSONObject;
import org.junit.Test;

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
            String json = http.sendGet("request");

            JSONObject obj  = new JSONObject(json.substring(1,json.length()-1));
            System.out.println(obj.toString());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void httpPOst(){
        System.out.println("XXXXX");
        HttpTest http = new HttpTest();
        try{
            http.sendPut();
        }catch(Exception e){

        }

    }
}

