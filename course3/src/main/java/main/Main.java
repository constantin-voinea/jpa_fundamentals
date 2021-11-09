package main;

import entities.Company;
import entities.Employee;
import entities.Event;
import entities.Price;
import entities.Product;
import entities.embeddables.Address;
import entities.enums.Currency;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author cvoinea
 */
public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit-c3");
            EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Price p1 = new Price();
            p1.setAmount(4);
            p1.setCurrency(Currency.USD);

            entityManager.persist(p1);

            Product product1 = new Product();
            product1.setExpirationDate(LocalDate.now());
            entityManager.persist(product1);

            Event event1 = new Event();
            event1.setEventTime(LocalDateTime.now());
            entityManager.persist(event1);

            Employee e1 = new Employee();
            e1.setEmploymentDate(new Date());
            entityManager.persist(e1);

            Company company1 = new Company();
            company1.setName("some name");
            Address a1c1 = new Address();
            a1c1.setStreet("street 1");
            a1c1.setNumber(1);
            a1c1.setCity("B");
            company1.setAddress(a1c1);
            entityManager.persist(company1);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }


    }
}
