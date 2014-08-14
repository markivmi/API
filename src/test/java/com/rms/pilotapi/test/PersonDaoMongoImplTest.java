package com.rms.pilotapi.test;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.rms.pilotapi.core.Person;
import com.rms.pilotapi.dao.PersonDaoMongoImpl;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import org.junit.*;

import java.io.IOException;

public class PersonDaoMongoImplTest {

    private static final String MONGO_HOST = "localhost";
    private static final int MONGO_PORT = 27777;
    private static final String DB_NAME = "local";

    private static MongodExecutable mongodExecutable;
    private static MongodProcess mongodProcess;
    private static DB mongoDb;

    public PersonDaoMongoImpl personDao;

    @BeforeClass
    public static void setupDB() throws IOException {
        MongodStarter runtime = MongodStarter.getDefaultInstance();
        IMongodConfig config = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(MONGO_PORT, true))
                .build();
        mongodExecutable = runtime.prepare(config);
        mongodProcess = mongodExecutable.start();
        MongoClient mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);
        mongoDb = mongoClient.getDB(DB_NAME);
    }

    @AfterClass
    public static void closeDB() {
        mongodProcess.stop();
        mongodExecutable.stop();
    }

    @Before
    public void SetupDao() {
        personDao = new PersonDaoMongoImpl(mongoDb);
    }

    @After
    public void AfterTestRun() {
        personDao = null;
    }

    @Test
    public void CreatePersonTest() {
        Person person = TestUtils.getDummyPerson(0);
        Person p = personDao.createPerson(person);
        assert (p.getId() > 0);
    }

    @Test
    public void UpdatePersonTest() {
        Person person = personDao.createPerson(TestUtils.getDummyPerson(0));
        long id = person.getId();
        person.setName("John Doe updated");
        Person p = personDao.updatePerson(id, person);
        assert (p.equals(person));
    }

    @Test
    public void GetPersonTest() {
        Person person = personDao.createPerson(TestUtils.getDummyPerson(0));
        long id = person.getId();
        Person p = personDao.getPerson(id);
        assert (p != null);
    }

    @Test
    public void DeletePersonTest() {
        Person person = personDao.createPerson(TestUtils.getDummyPerson(0));
        long id = person.getId();
        assert (personDao.deletePerson(id));
    }

}
