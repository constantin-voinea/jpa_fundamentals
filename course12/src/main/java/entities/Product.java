package entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * @author cvoinea
 *  named queries are processed by the JPA implementation when the application starts; if they are invalid, the app won't start
 *  may become difficult to read for longer queries
 */
@Entity
@NamedQuery(name = "Product.all", query = "SELECT p FROM Product p")
@NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id")
public class Product {

    @Id
    private int id;
    @Basic(fetch = FetchType.LAZY) // not retrieved directly before being used
    private String name;
    private double price;

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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
