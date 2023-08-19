package com.example.urldownloadtool.requests;

public class RequestClass {
    
   

    private int id = 1;
    private String urlPassed = "example URL";

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getUrlPassed() {
        return urlPassed;
    }
    public void setUrlPassed(String urlPassed) {
        this.urlPassed = urlPassed;
    }

    public String printMe(){
        return getId()+ "passed this URL:" + getUrlPassed();
    }
}
