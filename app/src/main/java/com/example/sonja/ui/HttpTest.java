package com.example.sonja.ui;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Zuständig für synchrone Http Requests.
 */
public class HttpTest {

    public String urlip = "http://13.58.210.65:3000/";

    private final String USER_AGENT = "Mozilla/5.0";

    public HttpTest(){

    }

    /**
     * Sendet ein get Request an die DB. Hierbei kann man die Tabelle wählen, das Attribut, wie ausgewählt werden soll und was.
     * @param table Die Tabelle in der DB
     * @param attr Das Attribut in der DB
     * @param value Welchen Wert das Attribut haben soll
     * @param compare Wie vergleichen werden soll (z.b. eq für "=", lt für "<" und gt für ">")
     * @param select Welcher Wert ausgelesen werden soll
     * @return
     * @throws Exception
     */
    public String sendGet(String table, String attr, String value, String compare, String select) throws Exception {
        // If you do not want to filter, put "" in select
        String url = urlip+table+"?"+attr+"="+compare+"."+value+select;
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
        String result = response.toString();

        System.out.println("result : "+result);

        return result;

    }
}
