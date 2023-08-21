package com.example.urldownloadtool.requests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
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
        ProcessBuilder myProcessBuilder = new ProcessBuilder(binaryAndmyURL.pathToBinary,"-o","video.mp4",binaryAndmyURL.myUrl);
        String currentDirectory = System.getProperty("user.dir");

        myProcessBuilder.directory(new File(currentDirectory));
        Process myProcess = myProcessBuilder.start();

        System.out.println("Video Download began...");
        int exitCode = myProcess.waitFor();
        
        System.out.println("Downloaded Video to Server !");


    }

    // Returns a Video download to the Client called video.mp4
   public ResponseEntity<InputStreamResource> clientDownload() throws IOException, InterruptedException {
        // Provide the path to the downloaded video file
        // use the System.property "user.dir" and add the location of the downloaded File so this code can process it
        String currentDirectory = System.getProperty("user.dir");
        File videoFile = new File(currentDirectory + "/video.mp4");

        // Put the Video into a Input Stream and make a InputStream Resource out of it
        InputStream videoInputStream = new FileInputStream(videoFile);
        InputStreamResource inputStreamResource = new InputStreamResource(videoInputStream);

        // Initialize new HTTP-Headers 
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", "video.mp4");
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        // Return the video as a ResponseEntity
        // Put the Video in the Body of the HTTP-Response
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(videoFile.length())
                .body(inputStreamResource);
    }



}
