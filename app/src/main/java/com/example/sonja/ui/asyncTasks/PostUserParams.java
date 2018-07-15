package com.example.sonja.ui.asyncTasks;

import com.example.sonja.ui.NeueFahrt1;

public class PostUserParams {

    String user_email;
    String user_vorname;
    String user_nachname;
    String user_username;
    String user_password;

    String user_stadt_home;
    String user_treffpunkt_home;
    String user_treffpunkt_work;
    String user_str_home;
    String user_str_work;
    String user_plz_home;
    String user_plz_work;
    String user_handynr;

    String car_marke;
    String car_farbe;
    String car_modell;
    String car_nummernschild;
    String car_sitzplaetze;

    public PostUserParams( String user_email, String user_vorname, String user_nachname, String user_username, String user_password,String user_stadt_home,
            String user_treffpunkt_home, String user_treffpunkt_work, String user_str_home, String user_str_work, String user_plz_home,
            String user_plz_work, String user_handynr, String car_marke, String car_farbe, String car_modell, String car_nummernschild,
            String car_sitzplaetze){
        this.user_email = user_email;
        this.user_vorname = user_vorname;
        this.user_nachname = user_nachname;
        this.user_username = user_username;
        this.user_password = user_password;

        this.user_stadt_home = user_stadt_home;
        this.user_treffpunkt_home = user_treffpunkt_home;
        this.user_treffpunkt_work = user_treffpunkt_work;
        this.user_str_home = user_str_home;
        this.user_str_work = user_str_work;
        this.user_plz_home = user_plz_home;
        this.user_plz_work = user_plz_work;
        this.user_handynr = user_handynr;

        this.car_marke = car_marke;
        this.car_farbe = car_farbe;
        this.car_modell = car_modell;
        this.car_nummernschild = car_nummernschild;
        this.car_sitzplaetze = car_sitzplaetze;
    }
}
