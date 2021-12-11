package entities;

import javax.persistence.Entity;

/**
 * @author cvoinea
 */
@Entity
public class Manager extends Employee {

    private String responsibility;

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }
}
