package com.tekcapsule.researchpaper.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.Command;
import com.tekcapsule.researchpaper.domain.model.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class CreateCommand extends Command {
    private String title;
    private String topicCode;
    private List<String> authors;
    private List<String> tags;
    private int recommendations;
    private String publisher;
    private String publishedOn;
    private String duration;
    private String researchPaperUrl;
    private String summary;
    private String description;
    private List<Module> modules;
    private String imageUrl;
    private Promotion promotion;
}