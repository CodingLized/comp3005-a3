package com.github.codinglized;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table (name = "students")
public class Student {

    public Student(){};

    public Student(String first_name, String last_name, String email, String enrollment_date){
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.enrollment_date = LocalDate.parse(enrollment_date);
    }

    public int getId(){
        return student_id;
    }
    public String getFName(){
        return first_name;
    }
    public String getLName(){
        return last_name;
    }

    public String getEmail(){
        return email;
    }

    public String getEDateString(){
        return enrollment_date.toString();
    }

    public void setEmail(String new_email){
        email = new_email;
    }

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int student_id;

    private String first_name;
    private String last_name;
    private String email;
    private LocalDate enrollment_date;
}
