package main;

import entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

/**
 * @author cvoinea
 */
public class Ex3 {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit-c15");
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Product> query = builder.createQuery(Product.class);

//            String jpql = "select p from Product p where p.price > :min and p.price < :max";
            ParameterExpression<Double> min = builder.parameter(Double.class, "min");
            var max = builder.parameter(Double.class, "max");

            Root<Product> product = query.from(Product.class);
            query.select(product).where(
                    builder.and(
                            builder.greaterThan(product.get("price"), min),
                            builder.lessThan(product.get("price"), max)
                    )
            );

            TypedQuery<Product> productTypedQuery = entityManager.createQuery(query);
            productTypedQuery.setParameter("min", 10.0);
            productTypedQuery.setParameter("max", 30.0);
            System.out.println(productTypedQuery.getResultList());

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

    }
}
