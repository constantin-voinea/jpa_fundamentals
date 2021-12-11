package entities;

import javax.persistence.Entity;

/**
 * @author cvoinea
 */
@Entity
public class Bicycle extends Vehicle{

    private String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
