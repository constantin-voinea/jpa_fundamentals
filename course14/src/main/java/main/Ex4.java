package main;

import entities.Product;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author cvoinea
 */
public class Ex4 {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit-c14");
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Product product = entityManager.find(Product.class, 2); // goes to level 2 cache if the entity is marked as cacheable
            // CACHE
            Cache cache = factory.getCache();
            System.out.println(cache.contains(Product.class, 2)); // false if L2 cache was not configured

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
