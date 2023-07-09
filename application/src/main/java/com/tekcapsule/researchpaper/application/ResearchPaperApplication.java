package com.tekcapsule.researchpaper.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.tekcapsule.researchpaper","com.tekcapsule.core"})
public class ResearchPaperApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResearchPaperApplication.class, args);
    }
}
