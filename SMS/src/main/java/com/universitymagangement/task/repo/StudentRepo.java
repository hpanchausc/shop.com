package com.universitymagangement.task.repo;

import com.universitymagangement.task.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {
}
