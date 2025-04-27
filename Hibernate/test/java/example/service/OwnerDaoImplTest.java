package example.service;

import example.Model.Owner;
import example.Model.Pet;
import example.dao.OwnerDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;

@Testcontainers
public class OwnerDaoImplTest {

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("postgres")
            .withUsername("postgres")
            .withPassword("8542")
            .withExposedPorts(5432)
            .waitingFor(Wait.forListeningPort());

    private static SessionFactory sessionFactory;
    private OwnerDaoImpl ownerDao;

    @BeforeAll
    public static void setupAll() {
        System.out.println("PostgreSQL JDBC URL: " + postgres.getJdbcUrl());

        Configuration configuration = new Configuration();

        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
        configuration.setProperty("hibernate.connection.username", postgres.getUsername());
        configuration.setProperty("hibernate.connection.password", postgres.getPassword());
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");

        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.generate_statistics", "true");

        configuration.addAnnotatedClass(Owner.class);
        configuration.addAnnotatedClass(Pet.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    @BeforeEach
    public void setup() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.createNativeQuery("TRUNCATE TABLE cat_friends, pet, owner RESTART IDENTITY CASCADE").executeUpdate();
            tx.commit();
            System.out.println("Database truncated successfully.");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            session.close();
        }

        ownerDao = new OwnerDaoImpl(sessionFactory);
    }

    @AfterAll
    public static void tearDownAll() {
        sessionFactory.close();
    }

    @Test
    public void testSaveAndGetById() {
        Owner owner = new Owner();
        owner.setName("Test Owner");
        owner.setBday(LocalDate.of(2000, 1, 1));

        Owner savedOwner = (Owner) ownerDao.save(owner);
        Assertions.assertNotNull(savedOwner.getId());

        Owner foundOwner = (Owner) ownerDao.getById(savedOwner.getId());
        Assertions.assertEquals("Test Owner", foundOwner.getName());
    }

    @Test
    public void testGetAll() {
        Owner owner1 = new Owner();
        owner1.setName("Alice");
        owner1.setBday(LocalDate.of(1990, 5, 5));

        Owner owner2 = new Owner();
        owner2.setName("Bob");
        owner2.setBday(LocalDate.of(1985, 3, 3));

        Owner owner3 = new Owner();
        owner3.setName("Charlie");
        owner3.setBday(LocalDate.of(1980, 1, 1));

        ownerDao.save(owner1);
        ownerDao.save(owner2);
        ownerDao.save(owner3);

        List<Owner> owners = ownerDao.getAll();

        Assertions.assertEquals(3, owners.size());
    }
}
