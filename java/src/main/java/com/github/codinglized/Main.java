/*
Written by: Kaje Jack
Date: November 16, 2026
*/

package com.github.codinglized;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        
        SessionFactory sFactory;
        Session session;
        Scanner input = new Scanner(System.in);
        try{
            sFactory= new Configuration().addAnnotatedClass(Student.class).buildSessionFactory();
            session = sFactory.openSession();
        }
        catch (Exception e){
            e.printStackTrace();
            System.err.println("COULD NOT OPEN SESSION");
            input.close();
            return;
        }
    
        System.out.println("===============================");
        System.out.println("\tAssignment 3 Q1");
        System.out.println("===============================");
        
        int option;
        do{
            displayMenu();
            while(!input.hasNextInt()){
                System.err.println("Invalid Option");
                input.next();
            }
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    List<Student> students = getAllStudents(session);
                    System.out.println("STUDENTS");
                    System.out.println("========");
                    for(Student s : students){
                        System.out.printf("%d\t%s\t%s\t%s\t%s\n", s.getId(), s.getFName(), s.getLName(), s.getEmail(), s.getEDateString());
                    }
                    break;
                case 2:
                    System.out.print("Enter student info [first_name last_name email enrollment_date(yyyy-mm-dd)]: ");
                    try{
                        Scanner line = new Scanner(input.nextLine());
                        String first_name = line.next();
                        String last_name = line.next();
                        String email = line.next();
                        String enrollment_date_string = line.next();

                        addStudent(session, first_name, last_name, email, enrollment_date_string);
                        line.close();
                    }
                    catch(Exception ex){
                        System.err.println("Student could not be created");
                    }
                    
                    break;
                
                case 3:
                    System.out.print("Enter student ID: ");
                    int s_id;
                    try{
                        s_id = input.nextInt();
                        if(session.get(Student.class, s_id) == null){
                            throw new Exception();
                        }
                    }
                    catch(Exception e){
                        System.out.println("Could not retrieve student");
                        break;
                    }
                    System.out.print("Enter new email: ");
                    String new_email = input.next();
                    try{
                        updateStudentEmail(session, s_id, new_email);
                    }
                    catch (Exception e){
                        System.err.println("Could not update student email");
                    }
                    break;
                
                case 4:
                    System.out.print("Enter student ID: ");
                    try{
                        s_id = input.nextInt();
                        deleteStudent(session, s_id);
                    }
                    catch (Exception e){
                        System.out.println("Could not delete student");
                    }
                    break;
                
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
        while (option != 0);
        input.close();
        session.close();
    }

    public static void displayMenu(){
        System.out.println("MENU");
        System.out.println("=====");
        System.out.println("1. Get All Students");
        System.out.println("2. Add New Student");
        System.out.println("3. Update Student Email");
        System.out.println("4. Delete Student");
        System.out.println("0. QUIT");
    }

    public static List<Student> getAllStudents(Session s){
        List<Student> data = s.createQuery("FROM Student", Student.class).getResultList();
        return data;
    }

    public static void addStudent(Session s, String first_name, String last_name, String email, String enrollment_date){
        Transaction t = s.beginTransaction();
            Student new_student = new Student(first_name, last_name, email, enrollment_date);
            s.persist(new_student);
        t.commit();
    }
    public static void updateStudentEmail(Session s, int s_id, String email){
        Transaction t = s.beginTransaction();
            Student stu = s.get(Student.class, s_id);
            stu.setEmail(email);
            stu = s.merge(stu);
        t.commit();
    }

    public static void deleteStudent(Session s, int s_id){
        Transaction t = s.beginTransaction();
            Student stu = s.get(Student.class, s_id);
            s.remove(stu);
        t.commit();
    }
}