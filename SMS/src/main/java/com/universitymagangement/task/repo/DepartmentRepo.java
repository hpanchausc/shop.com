package com.universitymagangement.task.repo;

import com.universitymagangement.task.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
    Optional<Department> findByAdmin_Id(Integer id);
}
