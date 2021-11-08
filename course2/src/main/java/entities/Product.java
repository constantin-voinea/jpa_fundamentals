package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 * @author cvoinea
 */
@Entity
public class Product {

    /*
     GenerationType.AUTO     - leaves the implementation (Hibernate, in this case) to choose the generation strategy (based on database used)
                             - not recommendable
     GenerationType.IDENTITY - the persistence provider must assign primary keys for the entity using a database identity column
     GenerationType.TABLE    - the persistence provider must assign primary keys for the entity using an underlying database table
     GenerationType.SEQUENCE - similar to TABLE, the persistence provider must assign primary keys for the entity using a database sequence
                             - not supported by MySQL
                             - recommendable to use
     */


//    @GeneratedValue(strategy = GenerationType.IDENTITY) // check/set auto-increment option when creating the table
    /*
     - by default, the sequence_name from generator table is the name of the class
     - next_val is the default name for sequence value when using MySQL
     */
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "key_generator")
    /*
        - brings more flexibility
        - any name can be provided for the table generator (mandatory), pkColumnValue
     */
    @TableGenerator(name = "key_generator", table = "key_generator",
        pkColumnName = "key_name",
        pkColumnValue = "product_sequence",
        valueColumnName = "key_value",
        allocationSize = 20) // set the amount to increment by when allocating id numbers from the generator; can improve performance
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "key_generator")
    @Id
    private Integer id; // use wrapper classes to make a distinction between 0 or not set/null values
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
