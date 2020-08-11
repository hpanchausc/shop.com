package com.universitymagangement.task.restapi;

import com.universitymagangement.task.model.Course;
import com.universitymagangement.task.model.Enrollment;
import com.universitymagangement.task.model.Student;
import com.universitymagangement.task.repo.StudentRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/student")
public class StudentController {
    final StudentRepo repository;

    @Autowired
    public StudentController(StudentRepo repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Student> getStudents() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable Integer id) {
        return repository.findById(id).get();
    }

    @GetMapping("{id}/enrollments")
    public List<Enrollment> getEnrollments(@PathVariable Integer id) {
        Optional<Student> byId = repository.findById(id);
        return byId.map(Student::getEnrollments).orElse(null);
    }

    @PostMapping
    public void insertStudent(@RequestBody String body) {
        JSONObject json = new JSONObject(body);
        Student student=new Student();
        if (json.has("firstName")) {
            student.setFirstName(json.getString("firstName"));
        }else throw new IllegalArgumentException("first name is required");
        if (json.has("lastName")) {
            student.setLastName(json.getString("lastName"));
        }else throw new IllegalArgumentException("last name is required");
        repository.saveAndFlush(student);
    }

    @PutMapping("{id}")
    public void updateStudent(@PathVariable Integer id, @RequestBody String body) {
        JSONObject json = new JSONObject(body);
        if(repository.findById(id).isEmpty()){
            throw new IllegalArgumentException("can't find student by this id");
        }
        Student student= repository.findById(id).get();
        if (json.has("firstName")) {
            student.setFirstName(json.getString("firstName"));
        }
        if (json.has("lastName")) {
            student.setLastName(json.getString("lastName"));
        }
        repository.saveAndFlush(student);
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable Integer id) {
        repository.delete(repository.findById(id).get());
    }
}
