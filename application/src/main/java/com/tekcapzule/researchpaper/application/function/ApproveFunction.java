package com.tekcapzule.researchpaper.application.function;

import com.tekcapzule.core.domain.Origin;
import com.tekcapzule.core.utils.HeaderUtil;
import com.tekcapzule.core.utils.Outcome;
import com.tekcapzule.core.utils.PayloadUtil;
import com.tekcapzule.core.utils.Stage;
import com.tekcapzule.researchpaper.application.config.AppConfig;
import com.tekcapzule.researchpaper.application.function.input.ApproveResearchPaperInput;
import com.tekcapzule.researchpaper.application.mapper.InputOutputMapper;
import com.tekcapzule.researchpaper.domain.command.ApproveCommand;
import com.tekcapzule.researchpaper.domain.service.ResearchPaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
@Slf4j
public class ApproveFunction implements Function<Message<ApproveResearchPaperInput>, Message<Void>> {

    private final ResearchPaperService researchPaperService;

    private final AppConfig appConfig;

    public ApproveFunction(final ResearchPaperService researchPaperService, final AppConfig appConfig) {
        this.researchPaperService = researchPaperService;
        this.appConfig = appConfig;
    }

    @Override
    public Message<Void> apply(Message<ApproveResearchPaperInput> approveResearchPaperInputMessage) {
        Map<String, Object> responseHeaders = new HashMap<>();
        Map<String, Object> payload = new HashMap<>();
        String stage = appConfig.getStage().toUpperCase();
        try {
            ApproveResearchPaperInput approveResearchPaperInput = approveResearchPaperInputMessage.getPayload();
            log.info(String.format("Entering approve researchpaper Function -  ResearchPaper Id:%s", approveResearchPaperInput.getResearchPaperId()));
            Origin origin = HeaderUtil.buildOriginFromHeaders(approveResearchPaperInputMessage.getHeaders());
            ApproveCommand approveCommand = InputOutputMapper.buildApproveCommandFromApproveResearchPaperInput.apply(approveResearchPaperInput, origin);
            researchPaperService.approve(approveCommand);
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