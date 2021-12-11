package main;

import entities.Department;
import entities.Document;
import entities.Employee;
import entities.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cvoinea
 */
public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit-c6");
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Employee e1 = new Employee();
            e1.setName("emp 1");
            entityManager.persist(e1);

            Department d1 = new Department();
            d1.setName("dep 1");
            List<Employee> employees = new ArrayList<>();
            employees.add(e1);
            d1.setEmployees(employees);
            entityManager.persist(d1);

            // in order for this to work as the default hibernate behaviour, an additional table has to be created:
            // department_employee, having 2 columns: department_id, employees_id (department is the owner of the relationship)


            Person p1 = new Person();
            p1.setName("pers 1");

            Document doc1 = new Document();
            doc1.setName("doc 1");
            doc1.setPerson(p1);
            Document doc2 = new Document();
            doc2.setName("doc 2");
            doc2.setPerson(p1);
            p1.setDocuments(List.of(doc1, doc2));

            entityManager.persist(p1);
            entityManager.persist(doc1);
            entityManager.persist(doc2);
            // by default, hibernate expects and uses person_id as join column, if @JoinColumn is not used

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

    }
}
