package com.jpaexample;

import com.jpaexample.entity.Course;
import com.jpaexample.repo.CourseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = JpaExampleApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class JPQLTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PersistenceContext
	EntityManager em;

	@Test
	public void jpql_basic() {
		Query query = em.createQuery("Select  c  From Course c");
		List resultList = query.getResultList();
		logger.info("Select  c  From Course c -> {}",resultList);
	}


	@Test
	public void jpql_typed() {
		TypedQuery<Course> query =
				em.createQuery("Select  c  From Course c", Course.class);

		List<Course> resultList = query.getResultList();

		logger.info("Select  c  From Course c -> {}",resultList);
	}

	@Test
	public void jpql_where() {
		TypedQuery<Course> query =
				em.createQuery("Select  c  From Course c where name like '%Math'", Course.class);

		List<Course> resultList = query.getResultList();

		logger.info("Select  c  From Course c where name like '%Math'-> {}",resultList);
	}

}