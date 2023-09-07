package com.example.urldownloadtool.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.Instant;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    // Set Path to video folder
    private static final String userDir = System.getProperty("user.dir");
    private static final String videoFolder= userDir+ "/videos/";





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
                    // Store fileDate in Millisecs, format the fileDate to LocalDateTime-Format to compare currentDate and FileDate
                    long fileDateInMilliSeconds = file.lastModified();

                    // difference between currentDateinMilliseconds and fileDateInMilliseconds
                    long difference = Instant.now().toEpochMilli() - fileDateInMilliSeconds;
                    log.info("Difference: {}{}",file.toString(),difference);

                    // If there are more than 5 Minutes between the currentDate and FileDate, then delete the file
                    if (difference > 300000) {
                        log.info("currentDate: "+ Instant.now().toEpochMilli() + " And file date: "+ fileDateInMilliSeconds);

                        log.info("Deleted: "+ file.toString()+ " from server.");
                        file.delete();
                    }
                }

            }
        }
    }
}
