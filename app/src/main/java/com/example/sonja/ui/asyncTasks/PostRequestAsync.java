package com.example.sonja.ui.asyncTasks;

import android.os.AsyncTask;

import com.example.sonja.ui.NeueFahrt1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;


public class PostRequestAsync extends AsyncTask<PostRequestParams, Void, Void> {
    private final String USER_AGENT = "Mozilla/5.0";

    @Override
    protected Void doInBackground(PostRequestParams... postRequestParams) {
        String id = postRequestParams[0].id;
        int earliest_minute = postRequestParams[0].earliest_minute;
        int earliest_hour = postRequestParams[0].earliest_hour;
        int latest_hour = postRequestParams[0].latest_hour;
        int latest_minute = postRequestParams[0].latest_minute;
        NeueFahrt1.RequestRole requestRole = postRequestParams[0].requestRole;
        String direction = postRequestParams[0].direction;

        try {
            String url = "http://13.58.210.65:3000/request";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            String earliestDepartureTime = timeFormatter(earliest_hour, earliest_minute);
            String latestArrivalTime = timeFormatter(latest_hour, latest_minute);
            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            int year = Calendar.getInstance().get(Calendar.YEAR);
            int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
            int day = Calendar.getInstance().get(Calendar.DATE)+1;
            System.out.println(year + "-" + month + "-" + day);
            String date = year + "-" + month + "-" + day;
            String urlParameters = "earliestDepartureTime=" + earliestDepartureTime +
                    "&latestArrivalTime=" + latestArrivalTime +
                    "&direction=" + direction +
                    "&role=" + requestRole.toString().toLowerCase() +
                    "&status=not answered" +
                    "&userId="+id +
                    "&earliestDepartureTime=" + earliestDepartureTime +
                    "&date=" + date;
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
