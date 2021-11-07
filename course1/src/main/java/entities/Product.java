package entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * @author cvoinea
 */
@Entity
public class Product {

    @Id
    private int id;

    // added on simple object fields, not collections
    @Basic(optional = false) // specify if the field is optional, default is true; should correspond to nullable/not null in database
    private String name;
    private double price;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
