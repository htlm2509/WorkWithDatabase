package database;

import javax.persistence.*;

@Entity
@Table(name = "scientist")
public class Scientist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scientist_id")
    private int scientistId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    public Scientist() {}

    public Scientist(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getScientistId() {
        return scientistId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Scientist{" +
                "scientistId=" + scientistId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}