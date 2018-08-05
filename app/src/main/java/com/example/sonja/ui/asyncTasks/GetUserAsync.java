package com.example.sonja.ui.asyncTasks;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import com.example.sonja.ui.R;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetUserAsync extends AsyncTask<GetUserParams, Void, JSONArray> {
    private final String USER_AGENT = "Mozilla/5.0";
    public String urlip = "http://13.58.210.65:3000/";


    @Override
    protected JSONArray doInBackground(GetUserParams... getUserParams) {

        String uuid = getUserParams[0].uuid;

        String result = null;
        JSONArray arr = null;

        try {
            String url = urlip + "user" + "?id=eq." + uuid;
            System.out.println(url);

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

            arr = new JSONArray(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }
}
