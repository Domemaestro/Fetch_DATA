package com.example.fetchssid.Model;

public class SsidData {
    int id;
    String AccessCode;
    String SSID;
    public SsidData(){}

    public SsidData(String accessCode, String SSID) {
        this.AccessCode = accessCode;
        this.SSID = SSID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessCode() {
        return AccessCode;
    }

    public void setAccessCode(String accessCode) {
        AccessCode = accessCode;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }


}
