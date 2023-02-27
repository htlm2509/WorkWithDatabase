import org.hibernate.boot.*;
import org.hibernate.boot.registry.*;
import org.junit.jupiter.api.*;

import javax.persistence.*;

public class DatabaseBaseTest {

    protected EntityManagerFactory emf;

    @BeforeEach
    void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            emf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @AfterEach
    void tearDown() {
        if (emf != null) {
            emf.close();
        }
    }
}
