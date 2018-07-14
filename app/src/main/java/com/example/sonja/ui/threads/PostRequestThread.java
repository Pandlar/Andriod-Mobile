package com.example.sonja.ui.threads;

import com.example.sonja.ui.NeueFahrt1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;


public class PostRequestThread implements Runnable {
    String email;
    int earliest_minute;
    int earliest_hour;
    int latest_minute;
    int latest_hour;
    NeueFahrt1.RequestRole requestRole;
    String direction;
    private final String USER_AGENT = "Mozilla/5.0";

    public PostRequestThread(String email, int earliest_minute, int earliest_hour,
                             int latest_minute, int latest_hour,
                             NeueFahrt1.RequestRole requestRole, String direction){
        this.email=email;
        this.earliest_minute=earliest_minute;
        this.earliest_hour=earliest_hour;
        this.latest_hour=latest_hour;
        this.latest_minute=latest_minute;
        this.requestRole=requestRole;
        this.direction=direction;
    }

    @Override
    public void run(){
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
            int day = Calendar.getInstance().get(Calendar.DATE);
            System.out.println(year + "-" + month + "-" + day);
            String date = year + "-" + month + "-" + day;

            String urlParameters = "earliestDepartureTime=" + earliestDepartureTime +
                    "&latestArrivalTime=" + latestArrivalTime +
                    "&direction=" + direction +
                    "&role=" + requestRole.toString().toLowerCase() +
                    "&status=not answered" +
                    "&userId=6a737ef5-4095-4ce3-9e02-0c3d4b9c0539" +
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
