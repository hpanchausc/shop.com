package com.universitymagangement.task.restapi;

import com.universitymagangement.task.model.Course;
import com.universitymagangement.task.model.Department;
import com.universitymagangement.task.repo.DepartmentRepo;
import com.universitymagangement.task.repo.InstructorRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/department")
public class DepartmentController {
    final DepartmentRepo repository;
    final InstructorRepo instructorRepo;

    @Autowired
    public DepartmentController(DepartmentRepo repository, DepartmentRepo departmentRepo, InstructorRepo instructorRepo) {
        this.repository = repository;
        this.instructorRepo = instructorRepo;
    }

    @GetMapping
    public List<Department> getDepartments() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Department getDepartment(@PathVariable Integer id) {
        return repository.findById(id).get();
    }
    @GetMapping("{id}/courses")
    public List<Course> getDepartmentCourses(@PathVariable Integer id) {
        return repository.findById(id).get().getCourses();
    }

    @PostMapping()
    public void insertDepartment(@RequestBody String body) {
        JSONObject json = new JSONObject(body);
        Department department=new Department();
        if (json.has("name")) {
            department.setName(json.getString("name"));
        }else
            throw new IllegalArgumentException("name is not provided.");
        if (json.has("admin")) {
            Integer instructorId = json.getInt("admin");
            if (instructorRepo.findById(instructorId).isPresent())
                department.setAdmin(instructorRepo.findById(instructorId).get());
            else
                throw new IllegalArgumentException("can't find instructor with this id.");
        }
        if (json.has("budget")) {
            department.setBudget(json.getLong("budget"));
        }else
            throw new IllegalArgumentException("budget is not provided.");

        repository.saveAndFlush(department);
    }

    @PutMapping("/{id}")
    public void updateDepartment(@PathVariable Integer id, @RequestBody String body) {
        JSONObject json = new JSONObject(body);
        Optional<Department> optionalDepartment = repository.findById(id);
        if (optionalDepartment.isEmpty())
            throw new IllegalArgumentException("can't find department with this id.");
        Department department = optionalDepartment.get();
        if (json.has("name")) {
            department.setName(json.getString("name"));
        }
        if (json.has("admin")) {
            Integer instructorId = json.getInt("admin");
            if (instructorRepo.findById(instructorId).isPresent())
                department.setAdmin(instructorRepo.findById(instructorId).get());
            else
                throw new IllegalArgumentException("can't find instructor with this id.");
        }
        if (json.has("budget")) {
            department.setBudget(json.getLong("budget"));
        }
        repository.saveAndFlush(department);
    }

    @DeleteMapping("{id}")
    public void deleteDepartment(@PathVariable Integer id) {
        repository.delete(repository.findById(id).get());
    }
}
