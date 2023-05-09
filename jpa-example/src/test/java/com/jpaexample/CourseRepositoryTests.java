package com.jpaexample;

import com.jpaexample.entity.Course;
import com.jpaexample.repo.CourseRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = JpaExampleApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CourseRepositoryTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository repository;

	@Test
	@Transactional
	public void findById_basic() {
		Course course = repository.findById(10001L);
		System.out.println(course.getStudents());
		assertEquals("Math", course.getName());
	}

	@Test
	public void deleteById_basic() {
		repository.deleteById(10002L);
		assertNull(repository.findById(10002L));
	}

	@Test
	public void save_basic() {

		// get a course
		Course course = repository.findById(10001L);
		assertEquals("Math", course.getName());

		// update details
		course.setName("Math - Updated");
		repository.save(course);

		// check the value
		Course course1 = repository.findById(10001L);
		assertEquals("Math - Updated", course1.getName());
	}

	@Test
	public void playWithEntityManager() {
		repository.playWithEntityManager();
	}

}