package com.tekcapzule.researchpaper.domain.repository;

import com.tekcapzule.core.domain.CrudRepository;
import com.tekcapzule.researchpaper.domain.model.ResearchPaper;

import java.util.List;

public interface ResearchPaperDynamoRepository extends CrudRepository<ResearchPaper, String> {

    List<ResearchPaper> findAllByTopicCode(String topicCode);
}
