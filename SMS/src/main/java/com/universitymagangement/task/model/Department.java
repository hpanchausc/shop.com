package com.universitymagangement.task.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Long budget;
    @ManyToOne(optional = true)
    private Instructor admin;
    @OneToMany(mappedBy = "department")
    private List<Course> courses;

    public Department() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getBudget() {
        return budget;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBudget(Long budget) {
        this.budget = budget;
    }

    public Instructor getAdmin() {
        return admin;
    }

    public void setAdmin(Instructor admin) {
        this.admin = admin;
    }

    @JsonIgnore
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public boolean doesDepartmentHasCourse(Integer courseId){
        for (Course course: courses){
            if(course.getId().equals(courseId))
                return true;
        }
        return false;
    }

    public void replaceCourse(Course course) {
        for (Course course1: courses){
            if(course1.getId().equals(course.getId())){
                course1= course;
            }
        }
    }
}
