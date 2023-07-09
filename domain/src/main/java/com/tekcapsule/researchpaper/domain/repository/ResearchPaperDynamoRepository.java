package com.tekcapsule.researchpaper.domain.repository;

import com.tekcapsule.core.domain.CrudRepository;
import com.tekcapsule.researchpaper.domain.model.ResearchPaper;

import java.util.List;

public interface ResearchPaperDynamoRepository extends CrudRepository<ResearchPaper, String> {

    List<ResearchPaper> findAllByTopicCode(String topicCode);
}
