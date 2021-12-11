package main;

import entities.Company;
import entities.Detail;
import entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author cvoinea
 */
public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit-c5");
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Company c1 = new Company();
            c1.setName("comp 1");
            c1.setStreet("str 1");
            c1.setNumber("no 1");
            entityManager.persist(c1);

            Product p1 = new Product();
            p1.setName("pear");
            p1.setPrice(4);
            entityManager.persist(p1); // add in context this entity since no cascading type is specified
            Detail d1 = new Detail();
            d1.setKcal(44);
            d1.setProduct(p1);
            p1.setDetail(d1);
            entityManager.persist(d1);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
