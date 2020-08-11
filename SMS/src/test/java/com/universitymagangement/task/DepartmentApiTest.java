package com.universitymagangement.task;

import com.universitymagangement.task.model.Department;
import com.universitymagangement.task.repo.DepartmentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

@DataJpaTest
public class DepartmentApiTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private DepartmentRepo departmentRepo;

    @BeforeEach
    public void loadSomeData() {
        Department department = new Department();
        department.setBudget(1000L);
        department.setName("CS");
        entityManager.persist(department);
        entityManager.flush();
    }

    @Test
    public void whenFindByAdminId_returnsOnlyDepartmentsWithThatAdmin() {
        Integer id = 1;
        var found = departmentRepo.findByAdmin_Id(id);
        assert found.isEmpty() || found.get().getAdmin().getId().equals(id);
    }

    @Test
    public void doesDepartmentHasCourse_returnsTrueIfDepartmentIsAssignedWithIt() {
        var firstDep = departmentRepo.findAll().get(1);
        assert firstDep != null;
        var firstCourse = firstDep.getCourses().get(1);

        assert firstCourse != null && firstDep.doesDepartmentHasCourse(firstCourse.getId());
    }
}
