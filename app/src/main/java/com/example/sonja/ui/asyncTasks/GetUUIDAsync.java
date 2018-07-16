package com.example.sonja.ui.asyncTasks;

import android.os.AsyncTask;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class GetUUIDAsync extends AsyncTask<UUIDParams, Void, String> {
    private final String USER_AGENT = "Mozilla/5.0";

    @Override
    protected String doInBackground(UUIDParams... param) {

        String username = param[0].username;

        String url = "http://13.58.210.65:3000/user?username=eq." + username + "&select=id";
        System.out.println(url);

        URL obj = null;
        String result="";
        try {
            obj = new URL(url);
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

            JSONArray arr = new JSONArray(result);
            String id = arr.getJSONObject(0).getString("id");
            System.out.println("ID: "+id);

            result = id;



        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    }
