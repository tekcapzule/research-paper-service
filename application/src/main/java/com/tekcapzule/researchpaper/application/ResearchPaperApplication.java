package com.tekcapzule.researchpaper.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.tekcapzule.researchpaper","com.tekcapzule.core"})
public class ResearchPaperApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResearchPaperApplication.class, args);
    }
}
