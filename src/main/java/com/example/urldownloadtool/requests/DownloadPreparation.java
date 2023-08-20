package com.example.urldownloadtool.requests;

public class DownloadPreparation {
    // this class serves as preparation for the Parameters ( ID and URL)
    // so the yt-dlp tool can get the information from this class Objects

    final String pathToBinary = "./yt-dlp";
    String myUrl;

    public String getPathToBinary() {
        return pathToBinary;
    }
    public String getMyUrl() {
        return myUrl;
    }

    public void setMyUrl(String myUrl) {
        this.myUrl = myUrl;
    }

    
}
