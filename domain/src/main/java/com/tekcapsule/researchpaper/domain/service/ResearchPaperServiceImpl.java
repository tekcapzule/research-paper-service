package com.tekcapsule.researchpaper.domain.service;

import com.tekcapsule.researchpaper.domain.command.CreateCommand;
import com.tekcapsule.researchpaper.domain.command.UpdateCommand;
import com.tekcapsule.researchpaper.domain.model.ResearchPaper;
import com.tekcapsule.researchpaper.domain.model.Status;
import com.tekcapsule.researchpaper.domain.repository.ResearchPaperDynamoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class ResearchPaperServiceImpl implements ResearchPaperService {
    private ResearchPaperDynamoRepository researchPaperDynamoRepository;

    @Autowired
    public ResearchPaperServiceImpl(ResearchPaperDynamoRepository researchPaperDynamoRepository) {
        this.researchPaperDynamoRepository = researchPaperDynamoRepository;
    }

    @Override
    public void create(CreateCommand createCommand) {

        log.info(String.format("Entering create researchPaper service - Module Code :%s", createCommand.getTopicCode()));

        ResearchPaper researchPaper = ResearchPaper.builder()
                .title(createCommand.getTitle())
                .topicCode(createCommand.getTopicCode())
                .authors(createCommand.getAuthors())
                .publisher(createCommand.getPublisher())
                .recommendations(createCommand.getRecommendations())
                .resourceUrl(createCommand.getResourceUrl())
                .summary(createCommand.getSummary())
                .description(createCommand.getDescription())
                .tags(createCommand.getTags())
                .imageUrl(createCommand.getImageUrl())
                .promotion(createCommand.getPromotion())
                .publishedOn(createCommand.getPublishedOn())
                .status(Status.ACTIVE)
                .build();

        researchPaper.setAddedOn(createCommand.getExecOn());
        researchPaper.setAddedBy(createCommand.getExecBy().getUserId());

        researchPaperDynamoRepository.save(researchPaper);
    }

    @Override
    public void update(UpdateCommand updateCommand) {

        log.info(String.format("Entering update researchPaper service - ResearchPaper ID:%s", updateCommand.getResearchPaperId()));

        ResearchPaper researchPaper = researchPaperDynamoRepository.findBy(updateCommand.getResearchPaperId());
        if (researchPaper != null) {
            researchPaper.setTitle(updateCommand.getTitle());
            researchPaper.setTopicCode(updateCommand.getTopicCode());
            researchPaper.setAuthors(updateCommand.getAuthors());
            researchPaper.setPublisher(updateCommand.getPublisher());
            researchPaper.setRecommendations(updateCommand.getRecommendations());
            researchPaper.setResourceUrl(updateCommand.getResourceUrl());
            researchPaper.setSummary(updateCommand.getSummary());
            researchPaper.setDescription(updateCommand.getDescription());
            researchPaper.setTags(updateCommand.getTags());
            researchPaper.setPublishedOn(updateCommand.getPublishedOn());
            researchPaper.setPromotion(updateCommand.getPromotion());
            researchPaper.setImageUrl(updateCommand.getImageUrl());
            researchPaper.setUpdatedOn(updateCommand.getExecOn());
            researchPaper.setUpdatedBy(updateCommand.getExecBy().getUserId());
            researchPaperDynamoRepository.save(researchPaper);
        }
    }

    @Override
    public List<ResearchPaper> findAll() {

        log.info("Entering findAll ResearchPaper service");

        return researchPaperDynamoRepository.findAll();
    }

    @Override
    public List<ResearchPaper> findAllByTopicCode(String topicCode) {

        log.info(String.format("Entering findAllByTopicCode ResearchPaper service - Module code:%s", topicCode));

        return researchPaperDynamoRepository.findAllByTopicCode(topicCode);
    }


}
