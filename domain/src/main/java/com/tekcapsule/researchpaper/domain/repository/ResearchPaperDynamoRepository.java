package com.tekcapsule.researchpaper.domain.repository;

import com.tekcapsule.core.domain.CrudRepository;
import com.tekcapsule.researchpaper.domain.model.Course;

import java.util.List;

public interface ResearchPaperDynamoRepository extends CrudRepository<Course, String> {

    List<Course> findAllByTopicCode(String topicCode);
}
