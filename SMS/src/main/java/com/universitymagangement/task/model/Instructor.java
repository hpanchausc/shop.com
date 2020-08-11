package com.universitymagangement.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String lastName;
    private String firstName;
    private Date hireDate;
    @OneToMany(mappedBy = "instructor")
    private List<CourseAssignment> courseAssignments;
    @OneToMany(mappedBy = "instructor")
    private List<OfficeAssignment> officeAssignments;
    @OneToMany(mappedBy = "admin")
    private List<Department> departments;

    public Instructor() {
        hireDate=new Date();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    @JsonIgnore
    public List<CourseAssignment> getCourseAssignments() {
        return courseAssignments;
    }

    public void setCourseAssignments(List<CourseAssignment> courseAssignments) {
        this.courseAssignments = courseAssignments;
    }
    @JsonIgnore
    public List<OfficeAssignment> getOfficeAssignments() {
        return officeAssignments;
    }

    public void setOfficeAssignments(List<OfficeAssignment> officeAssignments) {
        this.officeAssignments = officeAssignments;
    }

    @JsonIgnore
    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
