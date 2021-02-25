package com.foo.cte.config;

import com.mongodb.DB;
import com.mongodb.Mongo;

/**
 * Created by tnaw on 2/23/17.
 */
public class MongoDB {

    private Mongo mongo = new Mongo("localhost", 27017);
    private DB db = mongo.getDB("survey");


    public DB getDb() {
        return db;
    }

    public void setDb(DB db) {
        this.db = db;
    }
}
