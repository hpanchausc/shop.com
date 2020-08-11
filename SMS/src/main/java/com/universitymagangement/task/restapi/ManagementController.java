package com.universitymagangement.task.restapi;

import com.universitymagangement.task.model.*;
import com.universitymagangement.task.repo.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("api/management")
@RestController
public class ManagementController {
    final StudentRepo studentRepo;
    final CourseRepo courseRepo;
    final InstructorRepo instructorRepo;
    final DepartmentRepo departmentRepo;
    final OfficeAssignmentRepo officeAssignmentRepo;
    final CourseAssignmentRepo courseAssignmentRepo;
    final EnrollmentRepo enrollmentRepo;

    @Autowired
    public ManagementController(CourseAssignmentRepo courseAssignmentRepo, EnrollmentRepo enrollmentRepo, StudentRepo studentRepo, CourseRepo courseRepo, InstructorRepo instructorRepo, DepartmentRepo departmentRepo, OfficeAssignmentRepo officeAssignmentRepo) {
        this.courseAssignmentRepo = courseAssignmentRepo;
        this.instructorRepo = instructorRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.departmentRepo = departmentRepo;
        this.officeAssignmentRepo = officeAssignmentRepo;
        this.enrollmentRepo = enrollmentRepo;
    }

    @PostMapping("assign/{id}/{location}")
    public void assignOffice(@PathVariable Integer id, @PathVariable String location) {
        OfficeAssignment officeAssignment = new OfficeAssignment();
        if (instructorRepo.findById(id).isPresent()) {
            officeAssignment.setInstructor(instructorRepo.findById(id).get());
            officeAssignment.setLocation(location);
        } else {
            throw new IllegalArgumentException("can't find instructor by this id");
        }
        officeAssignmentRepo.saveAndFlush(officeAssignment);
    }

    @PutMapping("update/office-assignment/{id}")
    public void updateAssignedOffice(@PathVariable Integer id, @RequestBody String body) {
        Optional<OfficeAssignment> officeAssignmentOptional = officeAssignmentRepo.findById(id);
        if (officeAssignmentOptional.isEmpty())
            throw new IllegalArgumentException("Office assigment for this id not found");
        JSONObject object = new JSONObject(body);
        OfficeAssignment officeAssignment = officeAssignmentOptional.get();
        if (object.has("instructorId"))
            if (instructorRepo.findById(object.getInt("instructorId")).isPresent()) {
                officeAssignment.setInstructor(instructorRepo.findById(id).get());
            } else {
                throw new IllegalArgumentException("can't find instructor by this id");
            }
        if(object.has("location")){
            officeAssignment.setLocation(object.getString("location"));
        }
        officeAssignmentRepo.saveAndFlush(officeAssignment);
    }

    @PostMapping("/enroll")
    public void enrollStudent(@RequestBody String body) {
        JSONObject json = new JSONObject(body);
        Enrollment enrollment = new Enrollment();

        Student student = null;
        Course course = null;
        if (json.has("student_id")) {
            if (studentRepo.findById(json.getInt("student_id")).isPresent())
                student = studentRepo.findById(json.getInt("student_id")).get();
            else
                throw new IllegalArgumentException("can't find student by this id");
        }
        if (json.has("course_id")) {
            if (courseRepo.findById(json.getInt("course_id")).isPresent())
                course = courseRepo.findById(json.getInt("course_id")).get();
            else throw new IllegalArgumentException("can't find course by this id");
        }
        if (json.has("grade")) {
            enrollment.setGrade(json.getInt("grade"));
        }
        assert student != null;
        if (!student.alreadyRegisteredInCourse(course.getId())) {
            enrollment.setCourse(course);
            enrollment.setStudent(student);
            enrollmentRepo.saveAndFlush(enrollment);
        } else
            throw new IllegalArgumentException("student is already registered in this course.");
    }

    @PutMapping("/update-enroll/{id}")
    public void updateEnrollment(@PathVariable Integer id, @RequestBody String body) {
        JSONObject json = new JSONObject(body);
        Optional<Enrollment> enrollmentOptional = enrollmentRepo.findById(id);

        if(enrollmentOptional.isEmpty())
            throw new IllegalArgumentException("can't find enrollment by this id");
        Enrollment enrollment = enrollmentOptional.get();;
        Student student = null;
        Course course = null;
        if (json.has("student_id")) {
            if (studentRepo.findById(json.getInt("student_id")).isPresent())
                student = studentRepo.findById(json.getInt("student_id")).get();
            else
                throw new IllegalArgumentException("can't find student by this id");
        }
        if (json.has("course_id")) {
            if (courseRepo.findById(json.getInt("course_id")).isPresent())
                course = courseRepo.findById(json.getInt("course_id")).get();
            else throw new IllegalArgumentException("can't find course by this id");
        }
        if (json.has("grade")) {
            enrollment.setGrade(json.getInt("grade"));
        }
        enrollmentRepo.saveAndFlush(enrollment);
    }

    @PostMapping("assign-course/{instructorId}/{courseId}")
    public void assignCourse(@PathVariable Integer instructorId, @PathVariable Integer courseId) {
        CourseAssignment courseAssignment = new CourseAssignment();
        if (instructorRepo.findById(instructorId).isEmpty()) {
            throw new IllegalArgumentException("can't find instructor by this id");
        }
        if (courseRepo.findById(courseId).isEmpty()) {
            throw new IllegalArgumentException("can't find course by this id");
        }
        courseAssignment.setCourse(courseRepo.findById(courseId).get());
        courseAssignment.setInstructor(instructorRepo.findById(instructorId).get());

        //checking whether the instructor is head of department in some department
        Optional<Department> optionalDepartment = departmentRepo.findByAdmin_Id(instructorId);
        if (optionalDepartment.isEmpty()) {
            courseAssignmentRepo.saveAndFlush(courseAssignment);
        } else {
            if (optionalDepartment.get().doesDepartmentHasCourse(courseId)) {
                courseAssignmentRepo.saveAndFlush(courseAssignment);
            } else {
                throw new IllegalArgumentException("Head of department can't teach course in another department");
            }
        }
    }

        @PutMapping("updated-assigned-course/{id}/{instructorId}/{courseId}")
        public void updatedAssignedCourse(@PathVariable Integer id, @PathVariable Integer instructorId, @PathVariable Integer courseId) {
            Optional<CourseAssignment> courseAssignmentOptional = courseAssignmentRepo.findById(id);
            if(courseAssignmentOptional.isEmpty())
                throw new IllegalArgumentException("can't find course assignment by this id");

            if (instructorRepo.findById(instructorId).isEmpty()) {
                throw new IllegalArgumentException("can't find instructor by this id");
            }
            if (courseRepo.findById(courseId).isEmpty()) {
                throw new IllegalArgumentException("can't find course by this id");
            }
            CourseAssignment courseAssignment = courseAssignmentOptional.get();

            courseAssignment.setCourse(courseRepo.findById(courseId).get());
            courseAssignment.setInstructor(instructorRepo.findById(instructorId).get());

            //checking whether the instructor is head of department in some department
            Optional<Department> optionalDepartment = departmentRepo.findByAdmin_Id(instructorId);
            if (optionalDepartment.isEmpty()) {
                courseAssignmentRepo.saveAndFlush(courseAssignment);
            } else {
                if (optionalDepartment.get().doesDepartmentHasCourse(courseId)) {
                    courseAssignmentRepo.saveAndFlush(courseAssignment);
                } else {
                    throw new IllegalArgumentException("Head of department can't teach course in another department");
                }
            }

    }
}
