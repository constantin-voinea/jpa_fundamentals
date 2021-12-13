package main;

import entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author cvoinea
 */
public class Ex3 {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit-c14");
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Product product = entityManager.find(Product.class, 1); // post load
            entityManager.remove(product); // pre-remove and post remove

            entityManager.getTransaction().commit(); // delete happens heere
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
