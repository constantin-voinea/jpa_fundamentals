package main;

import entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

/**
 * @author cvoinea
 */
public class Main {

    public static void main(String[] args) {
        /*
            - EntityManager - the contract which allows the work with entities
            - it manages the context of entities (managed objects in JPA)
         */
        // create an EntityManagerFactory, then an EntityManager
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit-c1");
        EntityManager entityManager = factory.createEntityManager();

        Product p1 = new Product();
        p1.setId(13);
        p1.setName("apple");
        p1.setPrice(1);
        p1.setExpirationDate(LocalDate.now());

        try {
            entityManager.getTransaction().begin();
            // this is not an insert, it only adds the instance p1 in the context
            entityManager.persist(p1);
            // when the transaction ends, the context managed by EntityManager is mirrored to the data in the database
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

        // each EntityManager has its own context
        EntityManager em2 = factory.createEntityManager();
        Product p2 = new Product();
        p2.setId(23);
        // this field is marked as mandatory, so if missing,
        // the transaction will be rolled-back (an exception would be thrown if not using try-catch)
//        p2.setName("pie");
        p2.setPrice(3);
        p2.setExpirationDate(LocalDate.now());

        try {
            em2.getTransaction().begin();
            em2.persist(p2);
            em2.getTransaction().commit();
        } catch (Exception e) {
            em2.getTransaction().rollback();
        } finally {
            em2.close();
        }
    }
}
