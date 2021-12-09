package main.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author cvoinea
 */
@Entity
@Access(AccessType.FIELD) // this overrides the access type, however not recommended to have both access types declared
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // field access

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;  // property access -> the JPA implementation will access the value of a field through getter/setter
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
}
