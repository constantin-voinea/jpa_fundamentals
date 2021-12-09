package main.pk;

import java.io.Serializable;

/**
 * @author cvoinea
 */
public class DepartmentPK implements Serializable {

    private String name;
    private int number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
