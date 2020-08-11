package com.universitymagangement.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private Date enrollmentDate;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    public Student() {
        enrollmentDate=new Date();
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    @JsonIgnore
    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public boolean alreadyRegisteredInCourse(Integer courseId){
        for (Enrollment enrollment: enrollments){
            if(enrollment.getCourse().getId().equals(courseId))
                return true;
        }
        return false;
    }
}
