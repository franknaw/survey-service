package com.foo.cte.service;

import com.foo.cte.response.FormsResponse;
import com.foo.cte.response.ResponseStatus;
import com.google.inject.Inject;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.twitter.util.Future;
import com.foo.cte.config.MongoDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by tnaw on 2/1/17.
 */
public class FormService extends BaseService {

    private static final Logger LOG = LoggerFactory.getLogger(FormService.class);

    private final static String collectionName = "SurveyForms.forms";
    private final DBCollection collection;

    @Inject
    public FormService(final MongoDB db) {
        collection = db.getDb().getCollection(collectionName);
    }


    public Future<FormsResponse> getFormsTitles(String userDn) {
        try {

            BasicDBObject allQuery = new BasicDBObject();
            BasicDBObject fields = new BasicDBObject();
            fields.put("formId", 1);
            fields.put("formName", 1);

            DBCursor cursor = collection.find(allQuery, fields);
            List<DBObject> list = cursor.toArray();

            // build response map
            Map<String, Object> map = getMap();
            map.put("items", list);


            return Future.value(new FormsResponse(map, ResponseStatus.SUCCESS));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            getMap().put(ResponseStatus.FAILURE.name(), e.getMessage());
            return Future.value(new FormsResponse(getMap(), ResponseStatus.FAILURE));
        }
    }

    public Future<FormsResponse> getForm(String formId) {
        try {

            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("formId", formId);
            DBCursor cursor = collection.find(whereQuery);
            final DBObject result = cursor.next();

            // build response map
            Map<String, Object> map = getMap();
            map.put("items", result);

            return Future.value(new FormsResponse(map, ResponseStatus.SUCCESS));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            getMap().put(ResponseStatus.FAILURE.name(), e.getMessage());
            return Future.value(new FormsResponse(getMap(), ResponseStatus.FAILURE));
        }
    }

    public Future<FormsResponse> getForms(String userDN) {
        try {

            final DBCursor cursor = collection.find();
            List<DBObject> list = cursor.toArray();

            // build response map
            Map<String, Object> map = getMap();
            map.put("items", list);

            return Future.value(new FormsResponse(map, ResponseStatus.SUCCESS));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            getMap().put(ResponseStatus.FAILURE.name(), e.getMessage());
            return Future.value(new FormsResponse(getMap(), ResponseStatus.FAILURE));
        }
    }

}
