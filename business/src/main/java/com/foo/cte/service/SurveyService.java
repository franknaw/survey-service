package com.foo.cte.service;

import com.foo.cte.response.ResponseStatus;
import com.foo.cte.response.SurveyResponse;
import com.google.inject.Inject;
import com.mongodb.*;
import com.mongodb.util.JSON;
import com.twitter.util.Future;
import com.foo.cte.config.MongoDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;
import java.util.Map;

/**
 * Created by tnaw on 2/2/17.
 */
public class SurveyService extends BaseService {

    private static final Logger LOG = LoggerFactory.getLogger(SurveyService.class);

    private final static String collectionName = "SurveyForms.survey";
    private final DBCollection collection;

    @Inject
    public SurveyService(final MongoDB db) {
        collection = db.getDb().getCollection(collectionName);
    }

    private BasicDBObject getDbObject(final String requestsRaw) {
        return (BasicDBObject) JSON
                .parse(requestsRaw);
    }

    private void printCollection() {
        int i = 0;
        final DBCursor cursorDoc = collection.find();
        while (cursorDoc.hasNext()) {
            i++;
            LOG.info(i + " ====Mongo Row Data====  " + cursorDoc.next());
        }
    }

    public Future<SurveyResponse> submit(final String requestsRaw, final String userDN) {
        try {

            LOG.info("Survey submit: " + requestsRaw);

            final BasicDBObject dbObject = getDbObject(requestsRaw);
            dbObject.put("surveyId", UUID.randomUUID());
            dbObject.put("user", userDN);

            final WriteResult results = collection.save(dbObject, WriteConcern.ACKNOWLEDGED);

            // build response map
            Map<String, Object> map = getMap();
            map.put(ResponseStatus.SUCCESS.name(), "Survey saved: " + results.getN());

            printCollection();

            return Future.value(new SurveyResponse(map, ResponseStatus.SUCCESS));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            getMap().put(ResponseStatus.FAILURE.name(), e.getMessage());
            return Future.value(new SurveyResponse(getMap(), ResponseStatus.FAILURE));
        }
    }

}
