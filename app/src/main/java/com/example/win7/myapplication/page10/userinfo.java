package com.example.win7.myapplication.page10;

public class userinfo {

    String trekname;
    String trekdate;
    String companions;

    public userinfo(){

    }

    public userinfo(String trekname, String trekdate, String companions) {
        this.trekname = trekname;
        this.trekdate = trekdate;
        this.companions = companions;
    }

    public String getTrekname() {
        return trekname;
    }

    public String getTrekdate() {
        return trekdate;
    }

    public String getCompanions() {
        return companions;
    }



}
