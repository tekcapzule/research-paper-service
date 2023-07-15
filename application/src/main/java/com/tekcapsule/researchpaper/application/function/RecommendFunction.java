package com.tekcapsule.researchpaper.application.function;

import com.tekcapsule.core.domain.Origin;
import com.tekcapsule.core.utils.HeaderUtil;
import com.tekcapsule.core.utils.Outcome;
import com.tekcapsule.core.utils.PayloadUtil;
import com.tekcapsule.core.utils.Stage;
import com.tekcapsule.researchpaper.application.config.AppConfig;
import com.tekcapsule.researchpaper.application.function.input.RecommendInput;
import com.tekcapsule.researchpaper.application.mapper.InputOutputMapper;
import com.tekcapsule.researchpaper.domain.command.RecommendCommand;
import com.tekcapsule.researchpaper.domain.service.ResearchPaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
@Slf4j
public class RecommendFunction implements Function<Message<RecommendInput>, Message<Void>> {

    private final ResearchPaperService researchPaperService;

    private final AppConfig appConfig;

    public RecommendFunction(final ResearchPaperService researchPaperService, final AppConfig appConfig) {
        this.researchPaperService = researchPaperService;
        this.appConfig = appConfig;
    }


    @Override
    public Message<Void> apply(Message<RecommendInput> recommendInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            RecommendInput recommendInput = recommendInputMessage.getPayload();
            log.info(String.format("Entering recommend researchpaper Function -  ResearchPaper Id:%s", recommendInput.getResearchPaperId()));
            Origin origin = HeaderUtil.buildOriginFromHeaders(recommendInputMessage.getHeaders());
            RecommendCommand recommendCommand = InputOutputMapper.buildRecommendCommandFromRecommendInput.apply(recommendInput, origin);
            researchPaperService.recommend(recommendCommand);
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.SUCCESS);
            payload = PayloadUtil.composePayload(Outcome.SUCCESS);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            responseHeaders = HeaderUtil.populateResponseHeaders(responseHeaders, Stage.valueOf(stage), Outcome.ERROR);
            payload = PayloadUtil.composePayload(Outcome.ERROR);
        }
        return new GenericMessage(payload, responseHeaders);

    }
}