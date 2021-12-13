package entities;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * @author cvoinea
 */
@Entity
@Cacheable
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    /*
     LOAD - @PostLoad
     UPDATE - @PreUpdate, @PostUpdate
     REMOVE - @PreRemove, @PostRemove
     PERSIST - @PrePersist, @PostPersist
     */

    @PrePersist
    public void prePersist() {
        this.dateCreated = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastModified = LocalDateTime.now();
    }

    @PostUpdate
    public void postUpdate() {
        System.out.println("Entity " + this + " was updated");
    }

    @PostLoad
    public void postLoad() {
        System.out.println("Entity " + this + " was loaded!");
    }

    @PreRemove
    public void preRemove() {
        System.out.println("Entity " + this + " will be removed");
    }

    @PostRemove
    public void postRemove() {
        System.out.println("Entity " + this + " was removed!");
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastModified=" + lastModified +
                '}';
    }
}
