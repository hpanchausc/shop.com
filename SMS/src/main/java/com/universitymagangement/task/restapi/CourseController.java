package com.universitymagangement.task.restapi;

import com.universitymagangement.task.model.Course;
import com.universitymagangement.task.model.CourseAssignment;
import com.universitymagangement.task.model.Department;
import com.universitymagangement.task.model.Enrollment;
import com.universitymagangement.task.repo.CourseRepo;
import com.universitymagangement.task.repo.DepartmentRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/course")
public class CourseController {
    final CourseRepo repository;
    final DepartmentRepo departmentRepo;

    @Autowired
    public CourseController(CourseRepo repository, DepartmentRepo departmentRepo) {
        this.repository = repository;
        this.departmentRepo = departmentRepo;
    }

    @GetMapping
    public List<Course> getCourses() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Course getCourse(@PathVariable Integer id) {
        return repository.findById(id).get();
    }

    @GetMapping("{id}/enrollments")
    public List<Enrollment> getEnrollments(@PathVariable Integer id) {
        Optional<Course> byId = repository.findById(id);
        return byId.map(Course::getEnrollments).orElse(null);
    }

    @GetMapping("{id}/course-assignments")
    public List<CourseAssignment> getCourseAssignments(@PathVariable Integer id) {
        Optional<Course> byId = repository.findById(id);
        return byId.map(Course::getCourseAssignments).orElse(null);
    }

    @PostMapping
    public void insertCourse(@RequestBody String body) {
        JSONObject json = new JSONObject(body);
        Course course = new Course();
        if (json.has("title")) {
            course.setTitle(json.getString("title"));
        } else
            throw new IllegalArgumentException("Title required");
        if (json.has("creditHours")) {
            course.setCreditHours(json.getInt("creditHours"));
        }
        if (json.has("departmentId")) {
            int dId = json.getInt("departmentId");
            if (departmentRepo.findById(dId).isPresent()) {
                Department dpt=departmentRepo.findById(dId).get();
                course.setDepartment(dpt);
                dpt.getCourses().add(course);
            } else
                throw new IllegalArgumentException("Department not found with this id");
        }
        repository.saveAndFlush(course);
    }

    @PutMapping("{id}")
    public void updateCourse(@PathVariable Integer id, @RequestBody String body) {
        JSONObject json = new JSONObject(body);
        Optional<Course> optionalCourse = repository.findById(id);
        if (optionalCourse.isEmpty()) {
            throw new IllegalArgumentException("Course not found with this id");
        }
        Course course = optionalCourse.get();
        if (json.has("title")) {
            course.setTitle(json.getString("title"));
        }
        if (json.has("creditHours")) {
            course.setCreditHours(json.getInt("creditHours"));
        }
        if (json.has("departmentId")) {
            int dId = json.getInt("departmentId");
            if (departmentRepo.findById(dId).isPresent()) {
                Department dpt=departmentRepo.findById(dId).get();
                course.setDepartment(departmentRepo.findById(dId).get());
                dpt.replaceCourse(course);
            } else
                throw new IllegalArgumentException("Department not found with this id");
        }
        repository.saveAndFlush(course);
    }

    @DeleteMapping("{id}")
    public void deleteCourse(@PathVariable Integer id) {
        repository.delete(repository.findById(id).get());
        repository.flush();
    }
}
