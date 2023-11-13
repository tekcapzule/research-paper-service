package com.tekcapzule.researchpaper.application.function;

import com.tekcapzule.core.domain.EmptyFunctionInput;
import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.researchpaper.application.config.AppConfig;
import com.tekcapzule.researchpaper.domain.model.ResearchPaper;
import com.tekcapzule.researchpaper.domain.service.ResearchPaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
@Slf4j
public class GetAllFunction implements Function<Message<EmptyFunctionInput>, Message<List<ResearchPaper>>> {

    private final ResearchPaperService researchPaperService;

    private final AppConfig appConfig;

    public GetAllFunction(final ResearchPaperService researchPaperService, final AppConfig appConfig) {
        this.researchPaperService = researchPaperService;
        this.appConfig = appConfig;
    }


    @Override
    public Message<List<ResearchPaper>> apply(Message<EmptyFunctionInput> getAllInputMessage) {

        Map<String, Object> responseHeaders = new HashMap<>();
        List<ResearchPaper> cours = new ArrayList<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            log.info("Entering get all research paper Function");
            cours = researchPaperService.findAll();
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
        }
        return new GenericMessage<>(cours, responseHeaders);
    }
}