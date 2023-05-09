package com.jpaexample;

import com.jpaexample.repo.CourseRepository;
import com.jpaexample.repo.CourseSpringDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaExampleApplication implements CommandLineRunner {


	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CourseRepository repository;

	@Autowired
	private CourseSpringDataRepository courseSpringDataRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaExampleApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		//repository.playWithEntityManager();
		//courseSpringDataRepository.findByName("Math");
		//courseSpringDataRepository.courseWithMathInNameUsingNativeQuery();
		//courseSpringDataRepository.deleteByName("Math");



	}

}
