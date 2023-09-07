package com.example.urldownloadtool.requests;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.catalina.connector.Response;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    
    @PostMapping("/api/processing")
    public ResponseEntity<InputStreamResource> processVideo(@RequestParam(name="url") String urlParam,DownloadPreparationService downloadprepService) throws IOException, InterruptedException{



        // Safe clients passed Parameter to a variable 
        downloadprepService.safeUrlParam(urlParam);

        // this starts the download process it uses the path to yt-dlp binary and the passed parameter URL to download a video
        downloadprepService.runDownloader();



        // return the video as download to the client
        return downloadprepService.clientDownload();
       
    }

    @PostMapping("/api/processingAudio")
    public ResponseEntity<InputStreamResource> processAudio(@RequestParam(name="url") String urlParam,DownloadPreparationService downloadprepService) throws IOException, InterruptedException{



        // Safe clients passed Parameter to a variable
        downloadprepService.safeUrlParam(urlParam);

        // this starts the download process it uses the path to yt-dlp binary and the passed parameter URL to download a video
        downloadprepService.runDownloaderAudio();



        // return the video as download to the client
        return downloadprepService.clientDownloadAudio();

    }


    

    
}
