package com.tekcapsule.researchpaper.domain.service;

import com.tekcapsule.researchpaper.domain.command.CreateCommand;
import com.tekcapsule.researchpaper.domain.command.UpdateCommand;
import com.tekcapsule.researchpaper.domain.model.Course;
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

        log.info(String.format("Entering create course service - Module Code :%s", createCommand.getTopicCode()));

        Course course = Course.builder()
                .title(createCommand.getTitle())
                .topicCode(createCommand.getTopicCode())
                .author(createCommand.getAuthor())
                .publisher(createCommand.getPublisher())
                .duration(createCommand.getDuration())
                .courseUrl(createCommand.getCourseUrl())
                .summary(createCommand.getSummary())
                .description(createCommand.getDescription())
                .modules(createCommand.getModules())
                .prizingModel(createCommand.getPrizingModel())
                .deliveryMode(createCommand.getDeliveryMode())
                .learningMode(createCommand.getLearningMode())
                .imageUrl(createCommand.getImageUrl())
                .promotion(createCommand.getPromotion())
                .status(Status.ACTIVE)
                .build();

        course.setAddedOn(createCommand.getExecOn());
        course.setAddedBy(createCommand.getExecBy().getUserId());

        researchPaperDynamoRepository.save(course);
    }

    @Override
    public void update(UpdateCommand updateCommand) {

        log.info(String.format("Entering update course service - Course ID:%s", updateCommand.getCourseId()));

        Course course = researchPaperDynamoRepository.findBy(updateCommand.getCourseId());
        if (course != null) {
            course.setTitle(updateCommand.getTitle());
            course.setTopicCode(updateCommand.getTopicCode());
            course.setAuthor(updateCommand.getAuthor());
            course.setPublisher(updateCommand.getPublisher());
            course.setDuration(updateCommand.getDuration());
            course.setCourseUrl(updateCommand.getCourseUrl());
            course.setSummary(updateCommand.getSummary());
            course.setDescription(updateCommand.getDescription());
            course.setModules(updateCommand.getModules());
            course.setPrizingModel(updateCommand.getPrizingModel());
            course.setDeliveryMode(updateCommand.getDeliveryMode());
            course.setLearningMode(updateCommand.getLearningMode());
            course.setPromotion(updateCommand.getPromotion());
            course.setImageUrl(updateCommand.getImageUrl());
            course.setUpdatedOn(updateCommand.getExecOn());
            course.setUpdatedBy(updateCommand.getExecBy().getUserId());
            researchPaperDynamoRepository.save(course);
        }
    }

   /* @Override
    public void disable(DisableCommand disableCommand) {

        log.info(String.format("Entering disable topic service - Module Code:%s", disableCommand.getCode()));

        researchPaperDynamoRepository.findBy(disableCommand.getCode());
        Module topic = researchPaperDynamoRepository.findBy(disableCommand.getCode());
        if (topic != null) {
            topic.setStatus("INACTIVE");
            topic.setUpdatedOn(disableCommand.getExecOn());
            topic.setUpdatedBy(disableCommand.getExecBy().getUserId());
            researchPaperDynamoRepository.save(topic);
        }
    }*/

    @Override
    public List<Course> findAll() {

        log.info("Entering findAll Course service");

        return researchPaperDynamoRepository.findAll();
    }

    @Override
    public List<Course> findAllByTopicCode(String topicCode) {

        log.info(String.format("Entering findAllByTopicCode Course service - Module code:%s", topicCode));

        return researchPaperDynamoRepository.findAllByTopicCode(topicCode);
    }


}
