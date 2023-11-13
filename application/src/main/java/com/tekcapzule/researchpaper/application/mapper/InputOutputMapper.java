package com.tekcapzule.researchpaper.application.mapper;

import com.tekcapzule.core.domain.Command;
import com.tekcapzule.core.domain.ExecBy;
import com.tekcapzule.core.domain.Origin;
import com.tekcapzule.researchpaper.application.function.input.ApproveResearchPaperInput;
import com.tekcapzule.researchpaper.application.function.input.CreateInput;
import com.tekcapzule.researchpaper.application.function.input.RecommendInput;
import com.tekcapzule.researchpaper.application.function.input.UpdateInput;
import com.tekcapzule.researchpaper.domain.command.ApproveCommand;
import com.tekcapzule.researchpaper.domain.command.CreateCommand;
import com.tekcapzule.researchpaper.domain.command.RecommendCommand;
import com.tekcapzule.researchpaper.domain.command.UpdateCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.function.BiFunction;

@Slf4j
public final class InputOutputMapper {
    private InputOutputMapper() {

    }

    public static final BiFunction<Command, Origin, Command> addOrigin = (command, origin) -> {
        OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC);
        command.setChannel(origin.getChannel());
        command.setExecBy(ExecBy.builder().tenantId(origin.getTenantId()).userId(origin.getUserId()).build());
        command.setExecOn(utc.toString());
        return command;
    };

    public static final BiFunction<CreateInput, Origin, CreateCommand> buildCreateCommandFromCreateInput = (createInput, origin) -> {
        CreateCommand createCommand =  CreateCommand.builder().build();
        BeanUtils.copyProperties(createInput, createCommand);
        addOrigin.apply(createCommand, origin);
        return createCommand;
    };

    public static final BiFunction<UpdateInput, Origin, UpdateCommand> buildUpdateCommandFromUpdateInput = (updateInput, origin) -> {
        UpdateCommand updateCommand = UpdateCommand.builder().build();
        BeanUtils.copyProperties(updateInput, updateCommand);
        addOrigin.apply(updateCommand, origin);
        return updateCommand;
    };

    public static final BiFunction<ApproveResearchPaperInput, Origin, ApproveCommand> buildApproveCommandFromApproveResearchPaperInput = (approveResearchPaperInput, origin) -> {
        ApproveCommand approveCommand =  ApproveCommand.builder().build();
        BeanUtils.copyProperties(approveResearchPaperInput, approveCommand);
        addOrigin.apply(approveCommand, origin);
        return approveCommand;
    };

    public static final BiFunction<RecommendInput, Origin, RecommendCommand> buildRecommendCommandFromRecommendInput = (recommendInput, origin) -> {
        RecommendCommand recommendCommand =  RecommendCommand.builder().build();
        BeanUtils.copyProperties(recommendInput, recommendCommand);
        addOrigin.apply(recommendCommand, origin);
        return recommendCommand;
    };

}
