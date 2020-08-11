package com.universitymagangement.task;

import com.universitymagangement.task.model.Enrollment;
import com.universitymagangement.task.repo.CourseRepo;
import com.universitymagangement.task.repo.StudentRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class StudentApiTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Test
    public void ifStudentRegisteredBefore_returnsTrue() {
        var studentId = 1;
        var courseId = 1;
        var student = studentRepo.findById(studentId);
        var course = courseRepo.findById(courseId);
        assert student.isPresent() && course.isPresent();
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student.get());
        enrollment.setCourse(course.get());
        entityManager.persist(enrollment);
        entityManager.flush();

        assert student.get().alreadyRegisteredInCourse(courseId);
    }
}
