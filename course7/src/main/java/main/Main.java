package main;

import entities.Student;
import entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cvoinea
 *
 * @OneToMany
 * targetEntity() - attribute to specify the exact type of an object when using a supertype reference
 * cascade() - instruct hibernate to persist related entities on specific operations
 *      CascadeType.ALL - persist related entities for all operations
 * fetch() - default is LAZY on collections and EAGER on single object
 * mappedBy() - value of the attribute used by the other side of a bidirectional relation
 * orphanRemoval() -
 *
 * @ManyToOne
 * targetEntity()
 * cascade()
 * fetch()
 * optional() - default true, specify if the related object is mandatory when persisting
 */
public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit-c7");
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Student s1 = new Student();
            s1.setName("student 1");

            Teacher t1 = new Teacher();
            t1.setName("teacher 1");

            List<Student> students = new ArrayList<>();
            List<Teacher> teachers = new ArrayList<>();
            students.add(s1);
            t1.setStudents(students);
            teachers.add(t1);
            s1.setTeachers(teachers);

            entityManager.persist(s1);
            entityManager.persist(t1);
            // by default, a join table teacher_student is required, having the 2 fk columns: teacher_id and students_id (if not using @JoinTable)


            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }

    }
}
