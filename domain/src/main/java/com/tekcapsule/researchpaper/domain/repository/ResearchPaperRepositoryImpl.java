package com.tekcapsule.researchpaper.domain.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.tekcapsule.researchpaper.domain.model.ResearchPaper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Repository
public class ResearchPaperRepositoryImpl implements ResearchPaperDynamoRepository {

    private DynamoDBMapper dynamo;
    public static final String ACTIVE_STATUS = "ACTIVE";

    @Autowired
    public ResearchPaperRepositoryImpl(DynamoDBMapper dynamo) {
        this.dynamo = dynamo;
    }

    @Override
    public List<ResearchPaper> findAll() {

        return dynamo.scan(ResearchPaper.class,new DynamoDBScanExpression());
    }

    @Override
    public List<ResearchPaper> findAllByTopicCode(String topicCode) {

        HashMap<String, AttributeValue> expAttributes = new HashMap<>();
        expAttributes.put(":status", new AttributeValue().withS(ACTIVE_STATUS));
        expAttributes.put(":topicCode", new AttributeValue().withS(topicCode));

        HashMap<String, String> expNames = new HashMap<>();
        expNames.put("#status", "status");
        expNames.put("#topicCode", "topicCode");


        DynamoDBQueryExpression<ResearchPaper> queryExpression = new DynamoDBQueryExpression<ResearchPaper>()
                .withIndexName("topicGSI").withConsistentRead(false)
                .withKeyConditionExpression("#status = :status and #topicCode = :topicCode")
                .withExpressionAttributeValues(expAttributes)
                .withExpressionAttributeNames(expNames);

        return dynamo.query(ResearchPaper.class, queryExpression);

    }

    @Override
    public ResearchPaper findBy(String code) {
        return dynamo.load(ResearchPaper.class, code);
    }

    @Override
    public ResearchPaper save(ResearchPaper researchPaper) {
        dynamo.save(researchPaper);
        return researchPaper;
    }
}
