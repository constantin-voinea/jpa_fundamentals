package main.embeddables;

import javax.persistence.Embeddable;

/**
 * @author cvoinea
 */
@Embeddable
public class Address {

    private String no;
    private String street;
    private String city;

    public String getNumber() {
        return no;
    }

    public void setNumber(String number) {
        this.no = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
