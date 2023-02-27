package database;

import javax.persistence.*;

@Entity
@Table(name = "account_data")
public class AccountData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scientist_id", insertable = false, updatable = false)
    private Scientist scientist;

    public AccountData() {}

    public AccountData(String username, String email, String password, Scientist scientist) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.scientist = scientist;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Scientist getScientist() {
        return scientist;
    }

    public void setScientist(Scientist scientist) {
        this.scientist = scientist;
    }

    @Override
    public String toString() {
        return "AccountData{" +
                "accountId=" + accountId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", scientist=" + scientist +
                '}';
    }
}