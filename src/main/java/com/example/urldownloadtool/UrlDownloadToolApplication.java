package com.example.urldownloadtool;

import com.example.urldownloadtool.scheduled.ScheduledTasks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UrlDownloadToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlDownloadToolApplication.class, args);

	}

}
