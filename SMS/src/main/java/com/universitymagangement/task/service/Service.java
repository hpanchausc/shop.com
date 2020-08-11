package com.universitymagangement.task.service;

import com.universitymagangement.task.repo.*;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {
    final StudentRepo studentRepo;
    final CourseRepo courseRepo;
    final InstructorRepo instructorRepo;
    final DepartmentRepo departmentRepo;
    final OfficeAssignmentRepo officeAssignmentRepo;
    final CourseAssignmentRepo courseAssignmentRepo;
    final EnrollmentRepo enrollmentRepo;

    @Autowired
    public Service(CourseAssignmentRepo courseAssignmentRepo, EnrollmentRepo enrollmentRepo, StudentRepo studentRepo, CourseRepo courseRepo, InstructorRepo instructorRepo, DepartmentRepo departmentRepo, OfficeAssignmentRepo officeAssignmentRepo) {
        this.courseAssignmentRepo = courseAssignmentRepo;
        this.instructorRepo = instructorRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.departmentRepo = departmentRepo;
        this.officeAssignmentRepo = officeAssignmentRepo;
        this.enrollmentRepo = enrollmentRepo;
    }

}
