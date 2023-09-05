package com.example.urldownloadtool.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ScheduledTasks {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    // Set Path to video folder
    private static final String userDir = System.getProperty("user.dir");
    private static final String videoFolder= userDir+ "/videos/";

    // This scheduled Task runs every 10 Minutes and deletes videos from the folder
    @Scheduled(fixedRate = 600000)
    public void cleanUpVideos(){
        File videoDir = new File(videoFolder);

        // returns the files in the video directory
        File[] videosInFolder = videoDir.listFiles();

        // goes through every video in the folder and deletes it on schedule
        if (videosInFolder != null) {
            for (File file : videosInFolder
            ) {
                if (file.isFile()) {
                    log.info("current File deleted:" + file.toString());
                    file.delete();
                }

            }
        }
    }
}
