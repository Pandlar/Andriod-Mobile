package com.example.sonja.ui.asyncTasks;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class PostCacheLocationAsync extends AsyncTask<PostCacheLocationsParams, Void, Void> {
    private final String USER_AGENT = "Mozilla/5.0";

    /**
     * Methode zieht sich alle Parameter aus PostCacheLocationsParams und vollführt die HTTP-Post-Funktion in die
     * CacheLocations-Tabelle der Datenbank
     * @param params
     * @return
     */
    @Override
    protected Void doInBackground(PostCacheLocationsParams... params) {
        String userId = params[0].userId;
        String homeCoordinates = params[0].homeCoordinates;
        String officeCoordinates = params[0].officeCoordinates;

        try {
            String url = "http://13.58.210.65:3000/cacheLocations";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = "userId=" + userId +
                    "&homeCoordinates=" + homeCoordinates +
                    "&officeCoordinates=" + officeCoordinates;
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
        return null;
    }

    public String timeFormatter(int hour, int min){
        String formattedTime = "";
        String formattedHour = hour+"";
        String formattedMin = min+"";
        if(hour<10){
            formattedHour = "0"+hour;
        }
        if(min<10){
            formattedMin = "0"+min;
        }
        formattedTime=formattedHour+":"+formattedMin+":00";
        return formattedTime;
    }
}
