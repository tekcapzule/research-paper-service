package com.tekcapzule.researchpaper.domain.service;

import com.tekcapzule.researchpaper.domain.command.ApproveCommand;
import com.tekcapzule.researchpaper.domain.command.CreateCommand;
import com.tekcapzule.researchpaper.domain.command.RecommendCommand;
import com.tekcapzule.researchpaper.domain.command.UpdateCommand;
import com.tekcapzule.researchpaper.domain.model.ResearchPaper;

import java.util.List;


public interface ResearchPaperService {

    void create(CreateCommand createCommand);

    void update(UpdateCommand updateCommand);

    List<ResearchPaper> findAll();

    List<ResearchPaper> findAllByTopicCode(String code);
    void recommend(RecommendCommand recommendCommand);
    void approve(ApproveCommand approveCommand);

}
