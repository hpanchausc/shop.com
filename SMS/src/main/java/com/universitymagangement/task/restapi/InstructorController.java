package com.universitymagangement.task.restapi;

import com.universitymagangement.task.model.CourseAssignment;
import com.universitymagangement.task.model.Department;
import com.universitymagangement.task.model.Instructor;
import com.universitymagangement.task.model.OfficeAssignment;
import com.universitymagangement.task.repo.InstructorRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/instructor")
public class InstructorController {
    final InstructorRepo repository;

    @Autowired
    public InstructorController(InstructorRepo repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Instructor> getInstructors() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Instructor getInstructor(@PathVariable Integer id) {
        return repository.findById(id).get();
    }

    @GetMapping("{id}/course-assignments")
    public List<CourseAssignment> getInstructorCourseAssignments(@PathVariable Integer id) {
        Optional<Instructor> instructor = repository.findById(id);
        return instructor.map(Instructor::getCourseAssignments).orElse(null);
    }

    @GetMapping("{id}/office-assignments")
    public List<OfficeAssignment> getInstructorOfficeAssignments(@PathVariable Integer id) {
        Optional<Instructor> instructor = repository.findById(id);
        return instructor.map(Instructor::getOfficeAssignments).orElse(null);
    }
    @GetMapping("{id}/departments")
    public List<Department> getInstructorDepartments(@PathVariable Integer id) {
        Optional<Instructor> instructor = repository.findById(id);
        return instructor.map(Instructor::getDepartments).orElse(null);
    }

    @PutMapping("{id}")
    public void updateInstructor(@PathVariable Integer id, @RequestBody String body) {
        JSONObject json = new JSONObject(body);
        if(repository.findById(id).isEmpty()){
            throw new IllegalArgumentException("can't find instructor By this id");
        }
        Instructor instructor=repository.findById(id).get();
        if (json.has("firstName")) {
            instructor.setFirstName(json.getString("firstName"));
        }
        if (json.has("lastName")) {
            instructor.setLastName(json.getString("lastName"));
        }
        repository.saveAndFlush(instructor);
    }

    @PostMapping()
    public void insertInstructor(@RequestBody String body) {
        JSONObject json = new JSONObject(body);
        Instructor instructor=new Instructor();
        if (json.has("firstName")) {
            instructor.setFirstName(json.getString("firstName"));
        }else throw new IllegalArgumentException("first name is required");
        if (json.has("lastName")) {
            instructor.setLastName(json.getString("lastName"));
        }else throw new IllegalArgumentException("last name is required");
        repository.saveAndFlush(instructor);
    }

    @DeleteMapping("{id}")
    public void deleteInstructor(@PathVariable Integer id) {
        repository.delete(repository.findById(id).get());
    }


}
