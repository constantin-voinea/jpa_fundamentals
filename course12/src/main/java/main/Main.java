package main;

import entities.Employee;
import entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author cvoinea
 * <p>
 * JPQL - Java persistence query language -> similar to sql 92, but querying over objects
 * try to avoid native queries beacause it creates strong coupling to a specific technology
 */
public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit-c12");
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            String jpql = "SELECT p FROM Product p";
            TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
            List<Product> products = query.getResultList();
            System.out.println(products);

            String jpqlWithParam = "SELECT p FROM Product p WHERE p.price > :price";
            TypedQuery<Product> query1 = entityManager.createQuery(jpqlWithParam, Product.class);
            query1.setParameter("price", 2.0);
            List<Product> result1 = query1.getResultList();
            System.out.println(result1);

            String jpqlWithFunctions = "SELECT SUM(p.price) FROM Product p WHERE p.price > :price";
            TypedQuery<Double> query2 = entityManager.createQuery(jpqlWithFunctions, Double.class);
            query2.setParameter("price", 2.0);
            System.out.println(query2.getSingleResult());

            TypedQuery<Product> query3 = entityManager.createNamedQuery("Product.all", Product.class);
            query3.getResultStream().forEach(System.out::println);

            String jpqlWithOrderParam = "SELECT SUM(p.price) FROM Product p WHERE p.price > ?1 AND p.id >= ?2";
            TypedQuery<Double> query4 = entityManager.createQuery(jpqlWithOrderParam, Double.class);
            query4.setParameter(1, 2.0);
            query4.setParameter(2, 3);
            System.out.println(query4.getSingleResult());

            String sql = "SELECT * FROM product p";
            Query nativeQuery = entityManager.createNativeQuery(sql, Product.class);
            System.out.println(nativeQuery.getResultList());


            String jpqlEmployees = "select e from Employee e, Department d where e.department = d and d.id = :id";
            TypedQuery<Employee> employees = entityManager.createQuery(jpqlEmployees, Employee.class);
            employees.setParameter("id", 3);
            System.out.println(employees.getResultList());

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

    }
}
