package com.tekcapsule.researchpaper.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.researchpaper.domain.model.Promotion;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class UpdateInput {
    private String researchPaperId;
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
    private String imageUrl;
    private Promotion promotion;
}
