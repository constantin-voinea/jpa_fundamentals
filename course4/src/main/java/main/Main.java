package main;

import main.embeddables.Address;
import main.embeddables.BuildingPK;
import main.entities.Building;
import main.entities.Company;
import main.entities.Department;
import main.entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author cvoinea
 */
public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit-c4");
            EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // embedded example
            Company c1 = new Company();
            c1.setName("c1");
            Address a1 = new Address();
            a1.setCity("buc");
            a1.setNumber("1");
            a1.setStreet("mohor");
            c1.setAddress(a1);
            entityManager.persist(c1);

            // composed primary key using @IdClass
            Department d1 = new Department();
            d1.setName("dep 11");
            d1.setCode("c11d11");
            d1.setNumber(111);
            entityManager.persist(d1);

            // composed primary key using @EmbeddedId
            Building b1 = new Building();
            b1.setName("b1");
            b1.setId(new BuildingPK());
            b1.getId().setCode("bbb");
            b1.getId().setNumber(11);
            entityManager.persist(b1);

            // AccessType example
            Person person1 = new Person();
            person1.setName("person one");
            entityManager.persist(person1);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }


    }
}
