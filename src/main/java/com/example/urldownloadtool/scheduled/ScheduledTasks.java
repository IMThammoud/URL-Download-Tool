package com.example.urldownloadtool.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    // Set Path to video folder
    private static final String userDir = System.getProperty("user.dir");
    private static final String videoFolder= userDir+ "/videos/";

    Date currentDate = new Date();
    long currentDateInMilliSeconds = currentDate.getTime();



    // This scheduled Task runs every 10 Minutes and deletes videos from the folder
    @Scheduled(fixedRate = 60000)
    public void cleanUpVideos(){
        File videoDir = new File(videoFolder);

        // returns the files in the video directory
        File[] videosInFolder = videoDir.listFiles();

        // goes through every video in the folder and deletes it on schedule
        if (videosInFolder != null) {
            for (File file : videosInFolder
            ) {
                if (file.isFile()) {
                    // compare current date and last modified date of file and delete it when it's older than 5 minutes
                    long fileDateInMilliseconds = file.lastModified();
                    if (currentDateInMilliSeconds - fileDateInMilliseconds > 300000) {
                        log.info("currentDate: "+ currentDateInMilliSeconds + " And file date: "+ fileDateInMilliseconds);
                        file.delete();
                    }
                }

            }
        }
    }
}
