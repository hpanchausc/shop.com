package com.universitymagangement.task.repo;

import com.universitymagangement.task.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Integer> {
}
