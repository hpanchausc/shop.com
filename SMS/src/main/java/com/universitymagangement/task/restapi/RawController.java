package com.universitymagangement.task.restapi;

import com.universitymagangement.task.model.CourseAssignment;
import com.universitymagangement.task.model.Enrollment;
import com.universitymagangement.task.model.OfficeAssignment;
import com.universitymagangement.task.model.Student;
import com.universitymagangement.task.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("api/")
public class RawController {
    final StudentRepo studentRepo;
    final CourseRepo courseRepo;
    final InstructorRepo instructorRepo;
    final OfficeAssignmentRepo officeAssignmentRepo;
    final CourseAssignmentRepo courseAssignmentRepo;
    final EnrollmentRepo enrollmentRepo;

    @Autowired
    public RawController(CourseAssignmentRepo courseAssignmentRepo, EnrollmentRepo enrollmentRepo, StudentRepo studentRepo, CourseRepo courseRepo, InstructorRepo instructorRepo, OfficeAssignmentRepo officeAssignmentRepo) {
        this.courseAssignmentRepo=courseAssignmentRepo;
        this.instructorRepo = instructorRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.officeAssignmentRepo = officeAssignmentRepo;
        this.enrollmentRepo = enrollmentRepo;
    }

    @GetMapping("/office-assignments")
    public List<OfficeAssignment> getAssignments(){
        return officeAssignmentRepo.findAll();
    }
    
    @GetMapping("/office-assignments/{id}")
    public OfficeAssignment getOfficeAssignment(@PathVariable Integer id) {
        return officeAssignmentRepo.findById(id).get();
    }
    
    @DeleteMapping("/office-assignments/{id}")
    public void deleteOfficeAssignment(@PathVariable Integer id) {
    	officeAssignmentRepo.delete(officeAssignmentRepo.findById(id).get());
    }

    @GetMapping("/course-assignments")
    public List<CourseAssignment> getCourseAssignments(){
        return courseAssignmentRepo.findAll();
    }
    
    @GetMapping("/course-assignments/{id}")
    public CourseAssignment getCourseAssignment(@PathVariable Integer id) {
        return courseAssignmentRepo.findById(id).get();
    }
    
    @DeleteMapping("/course-assignments/{id}")
    public void deleteCourseAssignment(@PathVariable Integer id) {
    	courseAssignmentRepo.delete(courseAssignmentRepo.findById(id).get());
    }
    
    @GetMapping("/enrollments")
    public List<Enrollment> getEnrollments(){
        return enrollmentRepo.findAll();
    }
    
    @GetMapping("/enrollments/{id}")
    public Enrollment getEnrollment(@PathVariable Integer id) {
        return enrollmentRepo.findById(id).get();
    }
    
    @DeleteMapping("/enrollments/{id}")
    public void deleteEnrollment(@PathVariable Integer id) {
    	enrollmentRepo.delete(enrollmentRepo.findById(id).get());
    }
}
