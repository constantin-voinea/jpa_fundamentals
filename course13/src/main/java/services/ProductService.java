package services;

import entities.Product;
import repositories.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * @author cvoinea
 */
public class ProductService {

    private final EntityManagerFactory factory;

    public ProductService() {
        factory = Persistence.createEntityManagerFactory("persistence-unit-c13");
    }

    public void addProduct(String name) {
        EntityManager entityManager = factory.createEntityManager();
        var productRepository = new ProductRepository(entityManager);
        Product product = new Product();
        product.setName(name);

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(product);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    public List<Product> findProducts() {
        EntityManager entityManager = factory.createEntityManager();
        var productRepository = new ProductRepository(entityManager);
        return productRepository.findAllProducts();
    }
}
