package com.foo.cte.service;

import com.foo.cte.response.AdminResponse;
import com.foo.cte.response.ResponseStatus;
import com.google.inject.Inject;
import com.mongodb.*;
import com.mongodb.util.JSON;
import com.twitter.util.Future;
import com.foo.cte.config.MongoDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by tnaw on 2/9/17.
 */
public class AdminService extends BaseService {

    private static final Logger LOG = LoggerFactory.getLogger(AdminService.class);
    private final static String collectionName = "SurveyForms.forms";
    private final DBCollection collection;
    final MongoDB db;

    @Inject
    public AdminService(final MongoDB db) {
        this.db = db;
        collection = this.db.getDb().getCollection(collectionName);
    }

    private BasicDBObject getDbObject(final String requestsRaw) {
        return (BasicDBObject) JSON
                .parse(requestsRaw);
    }

    private String getFormName(BasicDBObject dbObject) {
        // Chain for embedded
        // ((DBObject) dbObject.get("form")).get("formName")
        return String.valueOf(dbObject.get("formName"));
    }

    private String getFormId(BasicDBObject dbObject) {
        return String.valueOf(dbObject.get("formId"));
    }

    private void printCollection() {
        int i = 0;
        final DBCursor cursorDoc = collection.find();
        while (cursorDoc.hasNext()) {
            i++;
            LOG.info(i + " ====Mongo Row Data====  " + cursorDoc.next());
        }
    }


    public Future<AdminResponse> createOrUpdateForm(final String requestsRaw, final String userDn) {
        try {

            // Hack to remove top json property "form"
            final BasicDBObject dbObjectFoo = (BasicDBObject) getDbObject(requestsRaw);

            final BasicDBObject dbObject = getDbObject(String.valueOf(dbObjectFoo.get("form")));

            final BasicDBObject searchQuery = new BasicDBObject().append("formId", getFormId(dbObject));
            final WriteResult results = collection.update(searchQuery, dbObject, true, false, WriteConcern.ACKNOWLEDGED);

            // build response map
            Map<String, Object> map = getMap();
            map.put(ResponseStatus.SUCCESS.name(), "is update" + results.isUpdateOfExisting());

            printCollection();


            return Future.value(new AdminResponse(map, ResponseStatus.SUCCESS));

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            getMap().put(ResponseStatus.FAILURE.name(), e.getMessage());
            return Future.value(new AdminResponse(getMap(), ResponseStatus.FAILURE));
        }
    }


    public Future<AdminResponse> getForm(final String formId, final String userDn) {
        try {

            printCollection();

            BasicDBObject whereQuery = new BasicDBObject();
            whereQuery.put("formId", formId);
            DBCursor cursor = collection.find(whereQuery);
            final DBObject result = cursor.next();

            // build response map
            Map<String, Object> map = getMap();
            map.put(ResponseStatus.SUCCESS.name(), result);

            return Future.value(new AdminResponse(map, ResponseStatus.SUCCESS));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            getMap().put(ResponseStatus.FAILURE.name(), e.getMessage());
            return Future.value(new AdminResponse(getMap(), ResponseStatus.FAILURE));
        }
    }


    public Future<AdminResponse> deleteForm(final String formId, final String userDn) {
        try {

            Map<String, Object> map = getMap();

            if (!formId.equals("catchAll")) {
                BasicDBObject document = new BasicDBObject();
                document.put("formId", formId);
                final WriteResult results = collection.remove(document, WriteConcern.ACKNOWLEDGED);
                map.put(ResponseStatus.SUCCESS.name(), "Document deleted=" + results.getN());
            } else {
                collection.drop();
                map.put(ResponseStatus.SUCCESS.name(), "Form Collection dropped");
            }

            printCollection();

            return Future.value(new AdminResponse(map, ResponseStatus.SUCCESS));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            getMap().put(ResponseStatus.FAILURE.name(), e.getMessage());
            return Future.value(new AdminResponse(getMap(), ResponseStatus.FAILURE));
        }
    }

    public Future<AdminResponse> deleteSurveyForm(final String formId, final String userDn) {
        try {

            Map<String, Object> map = getMap();
            final DBCollection collection = db.getDb().getCollection("SurveyForms.survey");
            collection.drop();
            map.put(ResponseStatus.SUCCESS.name(), "Survey Collection dropped");

            printCollection();

            return Future.value(new AdminResponse(map, ResponseStatus.SUCCESS));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            getMap().put(ResponseStatus.FAILURE.name(), e.getMessage());
            return Future.value(new AdminResponse(getMap(), ResponseStatus.FAILURE));
        }
    }


    public Future<AdminResponse> getSurvey(final String surveyId, final String userDn) {
        try {

            // TODO add filter by Id and userDn, amongst others.
            final DBCollection collection = db.getDb().getCollection("SurveyForms.survey");
            final DBCursor cursor = collection.find();
            List<DBObject> list = cursor.toArray();

            // build response map
            Map<String, Object> map = getMap();
            map.put("items", list);

            return Future.value(new AdminResponse(map, ResponseStatus.SUCCESS));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            getMap().put(ResponseStatus.FAILURE.name(), e.getMessage());
            return Future.value(new AdminResponse(getMap(), ResponseStatus.FAILURE));
        }
    }


}
