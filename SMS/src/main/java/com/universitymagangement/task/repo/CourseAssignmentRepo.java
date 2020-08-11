package com.universitymagangement.task.repo;

import com.universitymagangement.task.model.CourseAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseAssignmentRepo extends JpaRepository<CourseAssignment, Integer> {
}
