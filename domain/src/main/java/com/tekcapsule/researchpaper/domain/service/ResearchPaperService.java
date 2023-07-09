package com.tekcapsule.researchpaper.domain.service;

import com.tekcapsule.researchpaper.domain.command.CreateCommand;
import com.tekcapsule.researchpaper.domain.command.UpdateCommand;
import com.tekcapsule.researchpaper.domain.model.Course;

import java.util.List;


public interface ResearchPaperService {

    void create(CreateCommand createCommand);

    void update(UpdateCommand updateCommand);

    List<Course> findAll();

    List<Course> findAllByTopicCode(String code);
}
