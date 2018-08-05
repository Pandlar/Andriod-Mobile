package com.example.sonja.ui;

import android.location.Address;
import android.location.Geocoder;

import com.example.sonja.ui.HttpTest;
import com.example.sonja.ui.asyncTasks.PostUserAsync;
import com.example.sonja.ui.asyncTasks.PostUserParams;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

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

    private final String USER_AGENT = "Mozilla/5.0";
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
   public void testPostCacheLocation(){
       try {
           String url = "http://13.58.210.65:3000/cacheLocations";
           URL obj = new URL(url);
           HttpURLConnection con = (HttpURLConnection) obj.openConnection();

           //add reuqest header
           con.setRequestMethod("POST");
           con.setRequestProperty("User-Agent", USER_AGENT);
           con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

           String urlParameters = "userId=" + "457ec386-7a0b-4f29-ab57-f69753e24498";
           // Send post request
           con.setDoOutput(true);

           DataOutputStream wr = new DataOutputStream(con.getOutputStream());

           wr.writeBytes(urlParameters);

           wr.flush();

           wr.close();
           int responseCode = con.getResponseCode();
           System.out.println("\nSending 'POST' request to URL : " + url);
           System.out.println("Post parameters : " + urlParameters);
           System.out.println("Response Code : " + responseCode);
           BufferedReader in = new BufferedReader(
                   new InputStreamReader(con.getInputStream()));
           String inputLine;
           StringBuffer response = new StringBuffer();
           while ((inputLine = in.readLine()) != null) {
               response.append(inputLine);
           }
           in.close();
           System.out.println(response.toString());
       }catch(Exception e){
           e.printStackTrace();
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
    @Test
    public void firstDoubleFromPoint(){
        String s = "(52.48643,13.4247398)";
        s = s.substring(1,s.length()-1);
        StringTokenizer tokenizer = new StringTokenizer(s, ",");
        double result =  Double.parseDouble(tokenizer.nextToken());

    }


    @Test
    public void http_getInfoWithUUID() throws Exception {
       HttpTest httpUUIDTest = new HttpTest();
       httpUUIDTest.sendGet("match", "driverRequestId", "9ae65d36-a209-4670-8ba1-a3aa2fa435ba", "eq", "");

    }


    public void http_getRideInfoPast() throws Exception {
        HttpTest httpUUIDTest = new HttpTest();
        String json = httpUUIDTest.sendGet("ridesFuture", "userId", "6a737ef5-4095-4ce3-9e02-0c3d4b9c0539", "eq", "&order=date.asc,latestArrivalTime.asc");

        JSONArray arr = new JSONArray(json);
        String role = arr.getJSONObject(0).getString("role");
        String home = arr.getJSONObject(0).getString("homeAddress");
        String work = arr.getJSONObject(0).getString("officeAddress");
        String date = arr.getJSONObject(0).getString("date");
        String time = arr.getJSONObject(0).getString("latestArrivalTime");
        System.out.println("role: " + role + ", home: " + home + ", work: " + work + ", date: " + date + ", Time: " + time);

        String role2 = arr.getJSONObject(1).getString("role");
        String home2 = arr.getJSONObject(1).getString("homeAddress");
        String work2 = arr.getJSONObject(1).getString("officeAddress");
        String date2 = arr.getJSONObject(1).getString("date");
        String time2 = arr.getJSONObject(1).getString("latestArrivalTime");
        System.out.println("Zweiter Datensatz: \nrole: " + role2 + ", home: " + home2 + ", work: " + work2 + ", date: " + date2+ ", Time: " + time2);

    }

    @Test
    public void http_getUUID() throws Exception {
        HttpTest httpgetUUID = new HttpTest();
        String emailPassenger = "enrico.boos@test.com";
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

    @Test
    public void http_postUserData() {
        String USER_AGENT = "Mozilla/5.0";
        try {
            String url = "http://13.58.210.65:3000/user";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = "name=" + "Sarah" +
                    "&surname=" + "Gosten" +
                    "&phoneNumber=" + "014253627182" +
                    "&email=" + "test@web.de" +
                    "&homeAddress="+ "Musterstr.14" +
                    "&officeAddress=" + "Arbeitsadresse";

            // Send post request
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());

            wr.writeBytes(urlParameters);

            wr.flush();

            wr.close();
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void postUserTest(){
        String user_email = "maria.rest@web.de";
        String user_vorname = "Maria";
        String user_nachname = "Test";
        String user_username = "mariaT";
        String user_password = "xxxxxx";

        String user_stadt_home = "Berlin";
        String user_treffpunkt_home = "schnittstelle";
        String user_treffpunkt_work = "workki";
        String user_str_home = "hauptstr";
        String user_str_work = "marina lanke 14";
        String user_plz_home = "19273";
        String user_plz_work = "14194";
        String user_handynr = "0152345262";

        String car_marke = "VW";
        String car_farbe = "Indigo";
        String car_modell= "Volvo XC 90";
        String car_nummernschild = "BTC5000";
        String car_sitzplaetze = "5";

        try {
            String url = "http://13.58.210.65:3000/user";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            if(user_vorname.equals("")){user_vorname="null";}
            if(user_nachname.equals("")){user_vorname="null";}
            if(user_handynr.equals("")){user_handynr="null";}
            if(user_email.equals("")){user_email="null";}
            if(user_str_home.equals("")){user_str_home="null";}
            if(user_str_work.equals("")){user_str_work="null";}
            if(car_nummernschild.equals("")){car_nummernschild="null";}
            if(car_marke.equals("")){car_marke="null";}
            if(car_farbe.equals("")){car_farbe="null";}

            String urlParameters = "name=" + user_vorname +
                    "&surname=" + user_nachname +
                    "&phoneNumber=" + user_handynr +
                    "&email=" + user_email +
                    "&homeAddress="+ user_str_home+
                    "&officeAddress=" + user_str_work +
                    "&vehicleLicensePlate=" + car_nummernschild +
                    "&vehicleBrand=" + car_marke +
                    "&vehicleColour=" + car_farbe;

            // Send post request
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());

            wr.writeBytes(urlParameters);

            wr.flush();

            wr.close();
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getUser(){

       final String USER_AGENT = "Mozilla/5.0";
        String urlip = "http://13.58.210.65:3000/";
        String result = null;

        String url = urlip + "user" + "?id=eq." + "3a0bdce9-b8f0-4b69-b338-2668703bf55f";
        System.out.println(url);

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            result = response.toString();

            System.out.println("result : " + result);

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /*@Test
    public void adressToCoordinates(String adresse) { //gibt Koordinaten einer eingegeben Adresse zurück
        try {

            adresse = "Trautenaustr. 14, 10717 Berlin";
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses;
            addresses = geocoder.getFromLocationName(adresse, 1);
            if (addresses.size() > 0) {
                double latitude = addresses.get(0).getLatitude();
                double longitude = addresses.get(0).getLongitude();
                System.out.println("Koordinaten Test:" + latitude + longitude);
            }
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/

}

