package main;

import entities.Event;
import entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author cvoinea
 */
public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit-c2");
        EntityManager entityManager = factory.createEntityManager();

        Product p1 = new Product();
        p1.setName("orange");

        Event e1 = new Event();
        e1.setDescription("using uuid as id");


        try {
            entityManager.getTransaction().begin();
            entityManager.persist(p1);
            entityManager.persist(e1);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }


    }
}
