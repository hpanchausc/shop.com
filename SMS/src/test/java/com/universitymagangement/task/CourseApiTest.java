package com.universitymagangement.task;

import com.universitymagangement.task.model.Course;
import com.universitymagangement.task.repo.CourseRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class CourseApiTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CourseRepo courseRepo;

    @Test
    public void whenFindAll_returnsNonEmptyList(){
        Course course=new Course();
        course.setTitle("PF");
        course.setCreditHours(3);
        entityManager.persist(course);
        entityManager.flush();
//        when
        var found= courseRepo.findAll();
//        then
        assert !found.isEmpty();
    }

    @Test
    public void whenFindById_returnsSpecificElement(){
        Course course=new Course();
        course.setTitle("PF");
        course.setCreditHours(3);
        entityManager.persist(course);
        entityManager.flush();
//        when
        var found=courseRepo.findById(1);
//        then
        assert found.get().getTitle().equals(course.getTitle());
    }

    @Test
    public void whenRemove_deletesTheElement(){
        Course course=new Course();
        course.setTitle("PF");
        course.setCreditHours(3);
        entityManager.persist(course);
        entityManager.flush();
        var size=courseRepo.findAll().size();
//        when
        courseRepo.delete(course);
        courseRepo.flush();
        var secondSize=courseRepo.findAll().size();
//        then
        assert secondSize<size;
    }
}
