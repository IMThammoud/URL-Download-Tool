package com.example.urldownloadtool.requests;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;



public class DownloadPreparationService {
    DownloadPreparation binaryAndmyURL = new DownloadPreparation();
    
   
    
    
    
    // this safes the URL-Paremeter to the objects myURL variable.
    public void safeUrlParam(String url){
        
        binaryAndmyURL.myUrl = url;
    }

    // to test output
    public String showValueOfUrl(){
        
        return "Value of changed myURL Variable: "+ binaryAndmyURL.myUrl;
    }

    public void runDownloader() throws IOException, InterruptedException{
        // binaryAndMyURL.myURL has to be set first with safeUrlParam() before executing this method here > first safeUrlParam() then runDownloader()
        // Arguments for yt-dlp has to be added to the processbuilder like "-o video.mp4" which will be then the name of the downloaded video 
        ProcessBuilder myProcessBuilder = new ProcessBuilder(binaryAndmyURL.pathToBinary,"-o video.mp4",binaryAndmyURL.myUrl);
        String currentDirectory = System.getProperty("user.dir");
        myProcessBuilder.directory(new File(currentDirectory));
        Process myProcess = myProcessBuilder.start();
        System.out.println("Video Download began...");
        int exitCode = myProcess.waitFor();
        System.out.println("Downloaded Video to Server !");


    }

    // Returns a Video download to the Client called video.mp4
   public ResponseEntity<Resource> clientDownload() {
        Resource videoResource = new ClassPathResource("");

        
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=video.mp4")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(videoResource);
    }



}
