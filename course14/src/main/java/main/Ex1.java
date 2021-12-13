package main;

import entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author cvoinea
 *      LOAD - @PostLoad
 *      UPDATE - @PreUpdate, @PostUpdate
 *      REMOVE - @PreRemove, @PostRemove
 *      PERSIST - @PrePersist, @PostPersist
 *
 */
public class Ex1 {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit-c14");
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Product p1 = new Product();
            p1.setName("prod 1");
            entityManager.persist(p1);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

    }
}
