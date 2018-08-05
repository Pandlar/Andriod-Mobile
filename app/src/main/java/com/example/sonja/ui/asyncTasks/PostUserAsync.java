package com.example.sonja.ui.asyncTasks;

import android.os.AsyncTask;

import com.example.sonja.ui.NeueFahrt1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;


public class PostUserAsync extends AsyncTask<PostUserParams, Void, Void> {
    private final String USER_AGENT = "Mozilla/5.0";

    @Override
    protected Void doInBackground(PostUserParams... postUserParams) {

        String user_email = postUserParams[0].user_email;
        String user_vorname = postUserParams[0].user_vorname;
        String user_nachname = postUserParams[0].user_nachname;
        String user_username = postUserParams[0].user_username;
        String user_password = postUserParams[0].user_password;

        String user_stadt_home = postUserParams[0].user_stadt_home;
        String user_treffpunkt_home = postUserParams[0].user_treffpunkt_home;
        String user_treffpunkt_work = postUserParams[0].user_treffpunkt_work;
        String user_str_home = postUserParams[0].user_str_home;
        String user_str_work = postUserParams[0].user_str_work;
        String user_plz_home = postUserParams[0].user_plz_home;
        String user_plz_work = postUserParams[0].user_plz_work;
        String user_handynr = postUserParams[0].user_handynr;

        String car_marke = postUserParams[0].car_marke;
        String car_farbe = postUserParams[0].car_farbe;
        String car_modell= postUserParams[0].car_modell;
        String car_nummernschild = postUserParams[0].car_nummernschild;
        String car_sitzplaetze = postUserParams[0].car_sitzplaetze;

        String adresse_home = user_str_home + ", " + user_plz_home + " " + user_stadt_home;
        String adresse_office = user_str_work + ", " + user_plz_work + " " + user_stadt_home;

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
            if(user_treffpunkt_home.equals("")){user_treffpunkt_home="null";}
            if(user_treffpunkt_work.equals("")){user_treffpunkt_work="null";}

            String urlParameters = "name=" + user_vorname +
                    "&surname=" + user_nachname +
                    "&phoneNumber=" + user_handynr +
                    "&email=" + user_email +
                    "&homeAddress="+ adresse_home+
                    "&officeAddress=" + adresse_office +
                    "&vehicleLicensePlate=" + car_nummernschild +
                    "&vehicleBrand=" + car_marke +
                    "&vehicleColour=" + car_farbe +
                    "&username=" + user_username +
                    "&homeMeetingPointDescription=" + user_treffpunkt_home+
                    "&officeMeetingPointDescription=" + user_treffpunkt_work;

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


}
