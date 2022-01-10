package web.model;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotEmpty(message = "The field must not be empty.")
    @Size(min = 2, max = 30, message = "The allowed number of characters is from 2 to 30.")
    private String name;

    @Column
    @NotEmpty(message = "The field must not be empty.")
    @Size(min = 2, max = 30, message = "The allowed number of characters is from 2 to 30.")
    private String lastName;

    @Column
    @Min(value = 0, message = "Age cannot be less than zero.")
    private int age;

    //добавляю поле password для security-приложения
    @Column
    @NotEmpty(message = "The field must not be empty.")
    @Size(min = 4, max = 30, message = "The allowed number of characters is from 4 to 30.")
    private String password;

    public User() {
    }

    public User(String name, String lastName, int age, String password) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
