package entities;

// note these are Hibernate-specific
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

/**
 * @author cvoinea
 */
@Entity
public class Event {

    /*
        - Hibernate-specific annotation, not a JPA specification
     */
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDHexGenerator",
            parameters = @Parameter(name = "separator", value = "-")) // specify separator for generated UUID
    @GeneratedValue(generator = "uuid")
    @Id
    private String id;

    private String description;

    // must be commented-out so that Hibernate uses default/no-param constructor
//    public Event() {
//        this.id = UUID.randomUUID().toString();
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
