package com.rms.pilotapi;

import com.google.inject.AbstractModule;
import com.rms.pilotapi.dao.PersonDao;
import com.rms.pilotapi.dao.PersonDaoMongoImpl;

public class PilotAPIModule extends AbstractModule {
    @Override
    protected void configure() {
     /*
      * This tells Guice that whenever it sees a dependency on a PersonDao,
      * it should satisfy the dependency using a PersonDaoMongoImpl.
      */
        bind(PersonDao.class).to(PersonDaoMongoImpl.class);
    }
}
