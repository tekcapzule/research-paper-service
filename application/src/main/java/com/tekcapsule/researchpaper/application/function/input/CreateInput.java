package com.tekcapsule.researchpaper.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.researchpaper.domain.model.Promotion;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class CreateInput {
    private String title;
    private String topicCode;
    private List<String> authors;
    private List<String> tags;
    private int recommendations;
    private String publisher;
    private String publishedOn;
    private String duration;
    private String resourceUrl;
    private String summary;
    private String description;
    private String imageUrl;
    private Promotion promotion;
}