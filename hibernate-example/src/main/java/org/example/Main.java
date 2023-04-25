package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        // Create a new student object
        Student student = new Student("John Doe", 25);

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
            Student retrievedStudent = session.get(Student.class, student.getId());
            System.out.println("Student name: " + retrievedStudent.getName());
        }

        // Update the student information
        student.setName("Jane Doe");
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

