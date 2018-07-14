package com.example.sonja.ui;

import com.example.sonja.ui.HttpTest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;

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

   // @Test
   /* public void httpTest(){
        HttpTest http = new HttpTest();
        try {
            String json = http.sendGet("user", "uuid",);
            JSONArray arr = new JSONArray(json);
            System.out.println(arr.getJSONObject(2).getString("time"));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }*/

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
    @Test
    public void httppost(){
        HttpTest http = new HttpTest();
        /**
         * earliestDepartureTime=00:00:00&latestArrivalTime=00:00:00&direction=towards Office&role=driver&status=not answered&userId=01c62ef0-84ff-11e8-adc0-fa7ae01bbebc&earliestDepartureTime=00:00:00&date=2018-7-11
         */
        try{
            http.postRequest("",0,0,0,0, NeueFahrt1.RequestRole.DRIVER,"towards Office");
        }catch(Exception e){

        }

    }


    public void http_getInfoWithUUID() throws Exception {
       HttpTest httpUUIDTest = new HttpTest();
       httpUUIDTest.sendGet("match", "driverRequestId", "9ae65d36-a209-4670-8ba1-a3aa2fa435ba", "eq", "");

    }

    @Test
    public void http_getUUID() throws Exception {
        HttpTest httpgetUUID = new HttpTest();
        String emailPassenger = "lukasjanssen@posteo.de";
        String emailDriver = "789@456.de";
        String json = httpgetUUID.sendGet("user", "email", emailPassenger, "eq","&select=id");

        JSONArray arr = new JSONArray(json);
        String id = arr.getJSONObject(0).getString("id");
        System.out.println(id);


        String json2 = httpgetUUID.sendGet("request", "userId", id, "eq","");
        System.out.println(json2);

        JSONArray arr2 = new JSONArray(json2);
        System.out.println("Arr2 Length: " + arr2.length());

        List<String> matches = new ArrayList<>();
        int jsnlength = arr2.length();
        for(int i=0; i<jsnlength; i++) {
            System.out.println("Request Id :" + arr2.getJSONObject(i).getString("id"));
            System.out.println("Role: " + arr2.getJSONObject(i).getString("role"));

            String requestID = arr2.getJSONObject(i).getString("id");
            String role = arr2.getJSONObject(i).getString("role");

            if (role.equals("driver")){

                try {
                String jsonDriverMatches = httpgetUUID.sendGet("match", "driverRequestId", requestID, "eq", "");
                JSONArray arrDriverMatches = new JSONArray(jsonDriverMatches);

                String matchID = arrDriverMatches.getJSONObject(0).getString("id");
                System.out.println("matchID: "+matchID);
                matches.add(matchID);
                }
                catch (Exception E) {
                    System.out.println("Es gibt noch kein Match für diesen Request");
                }

            }
            if (role.equals("passenger")){
                System.out.println("passenger aufgerufen");
                try {
                    String jsonPassengerMatches = httpgetUUID.sendGet("match", "passenger0RequestId", requestID, "eq", "");
                    JSONArray arrPassengerMatches = new JSONArray(jsonPassengerMatches);

                    String matchID = arrPassengerMatches.getJSONObject(0).getString("id");
                    System.out.println("matchID: "+matchID);
                    matches.add(matchID);

                    //TODO: muss eigentlich auch noch für die anderen Passengers eingestellt werden
                }
                catch (Exception E) {
                    System.out.println("Es gibt noch kein Match für diesen Request");
                }



            }

            System.out.println("Das sind alle gefundenen Matches: " + matches);

        }

    }

    @Test
    public void http_getUUID1() throws Exception {
        HttpTest httpgetUUID = new HttpTest();
        String json = httpgetUUID.sendGet("user", "email", "123@456.de", "eq","");

        JSONArray arr = new JSONArray(json);
        String id = arr.getJSONObject(0).getString("id");
        System.out.println(id);

    }

    @Test
    public void http_postRating() throws Exception {
        HttpTest httpRatingTest = new HttpTest();
        httpRatingTest.sendPostRating("rating", "Zufriedenstellend", "6a737ef5-4095-4ce3-9e02-0c3d4b9c0539",
                "1b4a0156-7a2f-11e8-a8c9-0a181e304e34", "cbbb7972-97a6-4a12-b6a1-864f2dd7f2e3", 3);

    }

    @Test
    public void http_postRatingComplete() throws Exception {
        HttpTest httpRatingTest = new HttpTest();
        String ratingText = "Großartig!";
        String createdBy = "1b4a0156-7a2f-11e8-a8c9-0a181e304e34";
        String ratedUserId = "6a737ef5-4095-4ce3-9e02-0c3d4b9c0539";
        String matchID = "cbbb7972-97a6-4a12-b6a1-864f2dd7f2e3";
        int stars = 5;

        httpRatingTest.sendPostRating("rating", ratingText, createdBy,
                ratedUserId, matchID, stars);

    }

}