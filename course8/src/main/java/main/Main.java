package main;


import entities.Department;
import entities.DepartmentDetails;
import entities.Document;
import entities.Employee;
import entities.Person;
import entities.Student;
import entities.Teacher;
import entities.TeacherDetails;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cvoinea
 *
 * @AssociationOverride when having relationships inside the embedded object
 * @ElementCollection, @CollectionTable when using linked collection of objects which are not entities
 */
public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit-c8");
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // example of @AssociationOverride with an embedded unidirectional ManyToOne relationship
            Department d1 = new Department();
            d1.setName("dep 1");

            Employee e1 = new Employee();
            e1.setName("emp 1");

            DepartmentDetails depDet1 = new DepartmentDetails();
            depDet1.setContractNo("emp contract no " + e1.getName());
            depDet1.setDepartment(d1);

            e1.setDepartmentDetails(depDet1);

            entityManager.persist(d1);
            entityManager.persist(e1);

            // example of @AssociationOverride with an embedded unidirectional ManyToMany relationship
            Student s1 = new Student();
            s1.setName("stud 1");

            Teacher t1 = new Teacher();
            t1.setName("teacher 1");

            TeacherDetails td1 = new TeacherDetails();
            td1.setTeachers(new ArrayList<>());
            td1.getTeachers().add(t1);
            s1.setTeacherDetails(td1);

            entityManager.persist(s1);
            entityManager.persist(t1);

            // example of @ElementCollection
            Person per1 = new Person();
            per1.setName("person 1");
            per1.setPhoneNumbers(List.of("123", "321", "221"));

            Document doc1 = new Document();
            doc1.setNumber("no 1");
            doc1.setReference("ref 1");
            per1.setDocuments(List.of(doc1));

            entityManager.persist(per1);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

    }
}
