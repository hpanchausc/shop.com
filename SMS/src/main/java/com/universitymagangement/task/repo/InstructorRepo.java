package com.universitymagangement.task.repo;

import com.universitymagangement.task.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepo extends JpaRepository<Instructor, Integer> {
}
