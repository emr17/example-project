package org.example;

import org.example.entities.Address;
import org.example.entities.Course;
import org.example.entities.Student;
import org.example.entities.Teacher;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

    public static void main(String[] args) {
        Student student = new Student("John Doe", 25);

        Address address = new Address("123 Main St", "New York");
        student.setAddress(address);

        Teacher teacher = new Teacher("Mrs. Smith");
        student.setTeacher(teacher);

        Course course1 = new Course("Mathematics");
        Course course2 = new Course("Physics");

        student.getCourses().add(course1);
        student.getCourses().add(course2);

        // Save the student to the database
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(student);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }

        // Read the student from the database
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Address retrievedAddress = session.get(Address.class, address.getId());
            System.out.println("Student name: " + retrievedAddress.getStudent().getName());
        }

        // Update the student information
        student.setName("Jane Doe");
        student.getAddress().setCity("Florida");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.update(student);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }

        // Delete the student from the database
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(student);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }
    }
}

