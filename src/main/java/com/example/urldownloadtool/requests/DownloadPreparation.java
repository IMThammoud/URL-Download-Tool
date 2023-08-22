package com.example.urldownloadtool.requests;

import java.lang.management.OperatingSystemMXBean;

public class DownloadPreparation {
    // this class serves as preparation for the Parameters ( ID and URL)
    // so the yt-dlp tool can get the information from this class Objects

    String pathToBinary = "./yt-dlp";
    String myUrl;

    public String getPathToBinary() {
        return pathToBinary;
    }
    public String getMyUrl() {
        return myUrl;
    }

    public void setPathToBinary(String pathToBinary) {
        this.pathToBinary = pathToBinary;
    }

    public void setMyUrl(String myUrl) {
        this.myUrl = myUrl;
    }

    // determine current OS from which the app is running to
    // choose between Linux or Windows Binary (only for testing locally)
    public void setBinaryType(){
        if (System.getProperty("os.name").equals("Windows 10")) {
            setPathToBinary(".\\yt-dlp.exe");
        }
    }


    
}
