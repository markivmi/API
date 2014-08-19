package com.rms.pilotapi;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.rms.common.Functor;
import com.rms.pilotapi.core.Person;
import com.rms.pilotapi.dao.PersonDao;
import com.rms.pilotapi.dao.PersonDaoMongoImpl;
import org.mongojack.JacksonDBCollection;

import java.net.UnknownHostException;

public class PilotAPIModule extends AbstractModule {

    private final PilotAPIConfiguration configuration;

    public PilotAPIModule(PilotAPIConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void configure() {
     /*
      * This tells Guice that whenever it sees a dependency on a PersonDao,
      * it should satisfy the dependency using a PersonDaoMongoImpl.
      */

        bind(new TypeLiteral<Functor<String, JacksonDBCollection<Person, Long>>>() {}).toInstance(getJacksonDBCollection);
        bind(PersonDao.class).to(PersonDaoMongoImpl.class).asEagerSingleton();
    }

    @Provides
    @Singleton
    public DB getDB() {
        // MongoDB Setup
        final DB db;
        try {
            MongoClient mongoClient = new MongoClient(configuration.getHost(), configuration.getPort());
            db = mongoClient.getDB(configuration.getDatabase());
        } catch (UnknownHostException e) {
            throw new IllegalArgumentException("Cannot connect to MongoDB", e);
        }

        return db;
    }

    public Functor<String, JacksonDBCollection<Person, Long>> getJacksonDBCollection =
            (String collectionName) -> JacksonDBCollection.wrap(getDB().getCollection(collectionName), Person.class, Long.class);
}