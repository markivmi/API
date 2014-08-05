package com.rms.pilotapi.health;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.DB;

public class MongoHealthCheck extends HealthCheck {
    private DB db;

    public MongoHealthCheck(DB db) {
        this.db = db;
    }

    @Override
    protected Result check() throws Exception {
        db.getCollectionNames();
        return Result.healthy("MongoDB is running !!");
    }
}
