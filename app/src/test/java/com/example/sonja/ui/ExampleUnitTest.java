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
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
    public void httpTime(){
        Date date = new Date();
        HttpTest http = new HttpTest();
        System.out.println(date);
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
        String user_email = "sonderzeichen1.rest@web.de";
        String user_vorname = "Maria";
        String user_nachname = "Test";
        String user_username = "sonderzeichen1";
        String user_password = "xxxxxx";

        String user_stadt_home = "Berlin";
        String user_treffpunkt_home = "schnittstelle";
        String user_treffpunkt_work = "workki";
        String user_str_home = "hauptstr?";
        String user_str_work = "marina lanke 14";
        String user_plz_home = "19273";
        String user_plz_work = "14194";
        String user_handynr = "0152345262";

        String car_marke = "VW";
        String car_farbe = "Indigo";
        String car_modell= "Volvo XC 90";
        String car_nummernschild = "BTC5000";
        String car_sitzplaetze = "5";

        String adresse_home = user_str_home + ", " + user_plz_home + " " + user_stadt_home;
        String adresse_office = user_str_work + ", " + user_plz_work + " " + user_stadt_home;
        System.out.println("############# user post username: "+user_username);
        try {
            String url = "http://13.58.210.65:3000/user";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            if(user_vorname.equals("")){user_vorname="null";}
            if(user_nachname.equals("")){user_nachname="null";}
            if(user_username.equals("")){user_username="null";}
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
                    "&homeAddress="+ adresse_home+
                    "&officeAddress=" + adresse_office +
                    "&vehicleLicensePlate=" + car_nummernschild +
                    "&vehicleBrand=" + car_marke +
                    "&vehicleColour=" + car_farbe +
                    "&username=" + user_username;

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
                    new InputStreamReader(con.getInputStream(),"UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
            System.out.println("############# postUser beendet: "+user_username);
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


    @Test
    public void putTestSarah()  {
        String id = "b939fe1a-8288-4abc-8eae-481620b2ba6e";
        String name= "Ex Machina";

        String ip = "http://13.58.210.65:3000/Test";
        String urlString = ip + "?id=eq." + id;
        try{
            URL url = new URL(urlString);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("PUT");
            httpCon.setRequestProperty("User-Agent", USER_AGENT);
            httpCon.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            DataOutputStream wr = new DataOutputStream(httpCon.getOutputStream());

            wr.writeBytes("{\"id\":\""+id+"\",\"name\":\""+name+"\"}");
            //wr.writeBytes("test");
            wr.flush();
            wr.close();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpCon.getInputStream(),"UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void putTest() throws Exception{
       try{
           URL url = new URL("http://13.58.210.65:3000/Test");
           HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
           httpCon.setDoOutput(true);
           httpCon.setRequestMethod("PUT");
           OutputStreamWriter out = new OutputStreamWriter(
                   httpCon.getOutputStream());
           out.write("{\"uuid\":\"\"b939fe1a-8288-4abc-8eae-481620b2ba6e\"\",\"name\"=\"erfolgreicherPut\"");
           out.close();
           httpCon.getInputStream();
       }catch(Exception e) {

       }
    }


}

