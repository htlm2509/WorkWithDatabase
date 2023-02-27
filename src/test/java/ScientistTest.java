import database.*;
import org.assertj.core.api.*;
import org.junit.jupiter.api.*;

import javax.persistence.*;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class ScientistTest extends DatabaseBaseTest {

    @Test
    void verifyScientistRecordAvailability() {
        String lastName = "newton";

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Scientist> query = em
                .createQuery(
                        "SELECT s " +
                        "FROM Scientist s " +
                        "WHERE s.lastName = :p", Scientist.class)
                .setParameter("p", lastName);
        Scientist scientist = query.getSingleResult();

        assertThat(scientist).as("This scientist was not found")
                .isNotNull();
    }

    @Test
    void verifyScientistAccountData() {
        String firstName = "marie";
        String lastName = "curie";
        String username = "mcurie";
        String email = "mcurie@test.com";
        String password = "Qwerty1234!";

        SoftAssertions softly = new SoftAssertions();

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Object[]> query = em
                .createQuery(
                        "SELECT s.firstName, s.lastName, ad.username, ad.email, ad.password " +
                        "FROM AccountData ad " +
                        "JOIN ad.scientist s " +
                        "ON s.id = ad.id " +
                        "WHERE s.lastName = :p", Object[].class)
                .setParameter("p", lastName);
        Object[] results = query.getSingleResult();

        softly.assertThat((String) results[0])
                .as("Incorrect first name")
                .isEqualTo(firstName);
        softly.assertThat((String) results[2])
                .as("Incorrect username")
                .isEqualTo(username);
        softly.assertThat((String) results[3])
                .as("Incorrect email")
                .isEqualTo(email);
        softly.assertThat((String) results[4])
                .as("Incorrect password")
                .isEqualTo(password);
        softly.assertAll();
    }

    @Test
    void verifyScientistRecordDeletion() {
        String lastName = "darwin";

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Query deletion = em
                .createQuery(
                "DELETE " +
                "FROM Scientist s " +
                "WHERE s.lastName = :p")
                .setParameter("p", lastName);
        int removedRecords = deletion.executeUpdate();

        assertThat(removedRecords).as("Record was not removed")
                .isEqualTo(1);
    }
}
