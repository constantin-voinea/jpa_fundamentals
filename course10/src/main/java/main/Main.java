package main;

import entities.Animal;
import entities.Bicycle;
import entities.Car;
import entities.Cat;
import entities.Chocolate;
import entities.Employee;
import entities.Manager;
import entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author cvoinea
 *
 * @MappedSuperclass and @Inheritance strategies
 */
public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit-c10");
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // example for InheritanceType.SINGLE_TABLE
            Animal a1 = new Animal();
            a1.setName("animal 1");
            Cat c1 = new Cat();
            c1.setName("cat 1");
            c1.setColor("color 1");
            entityManager.persist(a1);
            entityManager.persist(c1);

            // example for InheritanceType.JOINED
            Product prod1 = new Product();
            prod1.setName("product 1");
            Chocolate choco1 = new Chocolate();
            choco1.setName("choco 1");
            choco1.setKcal(333);
            entityManager.persist(prod1);
            entityManager.persist(choco1);

            // example for InheritanceType.TABLE_PER_CLASS --> strongly not recommended in practice
            Employee e1 = new Employee();
            e1.setId(1);
            e1.setName("empl 1");
            Manager m1 = new Manager();
            m1.setId(2);
            m1.setName("manager 1");
            m1.setResponsibility("resp 1");
            entityManager.persist(e1);
            entityManager.persist(m1);

            // example for @MappedSuperclass
            Car car = new Car();
            car.setColor("black");
            car.setGas("diesel");
            Bicycle bicycle = new Bicycle();
            bicycle.setColor("white");
            bicycle.setModel("m 1");
            entityManager.persist(car);
            entityManager.persist(bicycle);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

    }
}
