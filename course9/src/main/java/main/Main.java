package main;

import entities.Employee;
import entities.Owner;
import entities.Person;
import entities.Pet;
import entities.Student;
import entities.Teacher;
import entities.enums.PhoneType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cvoinea
 *
 * Use maps for relationships
 */
public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit-c9");
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Person p1 = new Person();
            p1.setName("person 1");

            Map<String, String> phones = new HashMap<>();
            phones.put("mobile", "1234");
            phones.put("home", "3344");
            phones.put("office", "1221");
            p1.setPhoneNumbers(phones);

            entityManager.persist(p1);

            // example for a map having an enum as key
            Employee e1 = new Employee();
            e1.setName("employee 1");
            Map<PhoneType, String> phoneNumbers = new HashMap<>();
            phoneNumbers.put(PhoneType.MOBILE, "1234");
            phoneNumbers.put(PhoneType.HOME, "3344");
            phoneNumbers.put(PhoneType.OFFICE, "1221");
            e1.setPhoneNumbers(phoneNumbers);

            entityManager.persist(e1);

            // example for a map having an entity as value
            Student s1 = new Student();
            s1.setName("student 1");
            Teacher t1 = new Teacher();
            t1.setName("teacher 1");
            t1.setStudents(new HashMap<>());
            t1.getStudents().put("algebra", s1);
            entityManager.persist(s1);
            entityManager.persist(t1);

            // example for a map having an entity as a value and its pk as key
            Owner o1 = new Owner();
            o1.setName("owner 1");

            Pet pet1 = new Pet();
            pet1.setName("pet 1");
            Pet pet2 = new Pet();
            pet2.setName("pet 2");

            o1.setPets(new HashMap<>());
            o1.getPets().put(0, pet1);
            o1.getPets().put(1, pet2);
            pet1.setOwner(o1);
            pet2.setOwner(o1);

            entityManager.persist(o1);
            entityManager.persist(pet1);
            entityManager.persist(pet2);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
