package com.example.urldownloadtool.requests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class DownloadPreparationService {
    DownloadPreparation myDownloadObject = new DownloadPreparation();

    // Provide the path to the downloaded video file
    // use the System.property "user.dir" and add the location of the downloaded File so this code can process it
    String currentDirectory = System.getProperty("user.dir");
    // The Path to the Video
    File videoFile = new File(currentDirectory +"/"+ myDownloadObject.uniqueVideoName);
    File audioFile = new File(currentDirectory +"/"+ myDownloadObject.getUniqueVideoNameAudio);
   
    
    
    
    // these safes the URL-Parameter to the objects myURL variable.
    public void safeUrlParam(String url){
        
        myDownloadObject.myUrl = url;
    }

    // to test output
    public String showValueOfUrl(){
        
        return "Value of changed myURL Variable: "+ myDownloadObject.myUrl;
    }

    // downloads a video file in .mp4 format
    public void runDownloader() throws IOException, InterruptedException{
        // myDownloadObject.myURL has to be set first with safeUrlParam() before executing this method here > first safeUrlParam() then runDownloader()
        // Arguments for yt-dlp has to be added to the processbuilder like "-o videos/video.mp4" which will specify the path where the video will be downloaded
        myDownloadObject.setBinaryType();

        // -S prefers mp4 and m4a downloading, if its not available some other formats will be downloaded and maybe result in error because clientDownload() only finds files with .mp4 ending.
        ProcessBuilder myProcessBuilder = new ProcessBuilder(myDownloadObject.pathToBinary,"-S res,ext:mp4:m4a --recode mp4","-o", myDownloadObject.uniqueVideoName, myDownloadObject.myUrl);
        String currentDirectory = System.getProperty("user.dir");

        myProcessBuilder.directory(new File(currentDirectory));
        Process myProcess = myProcessBuilder.start();



        System.out.println("Video Download began...");
        int exitCode = myProcess.waitFor();
        // videoFile needs a fresh LastModified Date, set it to NOW
        videoFile.setLastModified(Instant.now().toEpochMilli());
        
        System.out.println("Downloaded Video to Server !");


    }
    // Downloads an audioFile in .m4a format
    public void runDownloaderAudio() throws IOException, InterruptedException{
        // myDownloadObject.myURL has to be set first with safeUrlParam() before executing this method here > first safeUrlParam() then runDownloader()
        // Arguments for yt-dlp has to be added to the processbuilder like "-o videos/video.mp4" which will specify the path where the video will be downloaded
        myDownloadObject.setBinaryType();
        ProcessBuilder myProcessBuilder = new ProcessBuilder(myDownloadObject.pathToBinary,"-f","140","-o" + myDownloadObject.getUniqueVideoNameAudio, myDownloadObject.myUrl);
        String currentDirectory = System.getProperty("user.dir");

        myProcessBuilder.directory(new File(currentDirectory));
        Process myProcess = myProcessBuilder.start();



        System.out.println("Video Download began...");
        int exitCode = myProcess.waitFor();
        // videoFile needs a fresh LastModified Date, set it to NOW
        audioFile.setLastModified(Instant.now().toEpochMilli());

        System.out.println("Downloaded Video to Server !");


    }

    // Returns a Video download to the Client with unique name
   public ResponseEntity<InputStreamResource> clientDownload() throws IOException, InterruptedException {


        // Put the Video into an Input Stream and make a InputStream Resource out of it
        InputStream videoInputStream = new FileInputStream(videoFile);
        InputStreamResource streamResource = new InputStreamResource(videoInputStream);
        // after the InputStream wrote the video to inputStreamResource, the stream can be closed



        // Initialize new HTTP-Headers 
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", myDownloadObject.uniqueVideoName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        // if the video File exists then delete it from server
        // this makes sure that the clients Videos don't stay on the server after download



        // Return the video as a ResponseEntity
        // Put the Video in the Body of the HTTP-Response
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(videoFile.length())
                .body(streamResource);
    }

    // Returns a audio download to the Client in .m4a format
    public ResponseEntity<InputStreamResource> clientDownloadAudio() throws IOException, InterruptedException {


        // Put the Video into an Input Stream and make a InputStream Resource out of it
        InputStream audioInputStream = new FileInputStream(audioFile);
        InputStreamResource streamResource = new InputStreamResource(audioInputStream);
        // after the InputStream wrote the video to inputStreamResource, the stream can be closed



        // Initialize new HTTP-Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", myDownloadObject.getUniqueVideoNameAudio);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        // if the video File exists then delete it from server
        // this makes sure that the clients Videos don't stay on the server after download



        // Return the video as a ResponseEntity
        // Put the Video in the Body of the HTTP-Response
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(audioFile.length())
                .body(streamResource);
    }



}
