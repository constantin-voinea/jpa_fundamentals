package main.entities;

import main.pk.DepartmentPK;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * @author cvoinea
 */
@Entity
@IdClass(DepartmentPK.class)
public class Department {

    @Id
    private String code;
    @Id
    private int number;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
