package app;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    public String login;
    public byte [] password;
    public double rating;
    public  int count;     //количество игр
    public Users() {

    }
    public Users(String login, byte [] password,double rating,int count) {

        this.login=login;
        this.password=password;
        this.rating=rating;
        this.count=count;

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte [] getPassword() {
        return password;
    }

    public void setPassword(byte [] password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
