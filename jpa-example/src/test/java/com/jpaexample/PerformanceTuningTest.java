package com.jpaexample;

import com.jpaexample.entity.Course;
import jakarta.persistence.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = JpaExampleApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PerformanceTuningTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    public void creatingNPlusOneProblemWithNamedQuery() {
        List<Course> courses = em
                .createNamedQuery("query_get_all_courses", Course.class)
                .getResultList();
        for (Course course : courses) {
            logger.info("Course -> {} Students -> {}", course, course.getStudents());
        }
    }

    @Test
    @Transactional
    public void creatingNPlusOneProblemWithJPQL() {
        TypedQuery<Course> query =
                em.createQuery("Select  c  From Course c", Course.class);

        List<Course> courses = query.getResultList();
        for (Course course : courses) {
            logger.info("Course -> {} Students -> {}", course, course.getStudents());
        }
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_FetchAnnotation() {
        TypedQuery<Course> query =
                em.createQuery("Select  c  From Course c", Course.class);

        List<Course> courses = query.getResultList();
        for (Course course : courses) {
            logger.info("Course -> {} Students -> {}", course, course.getStudents());
        }
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_EntityGraph() {

        EntityGraph<Course> entityGraph = em.createEntityGraph(Course.class);
        Subgraph<Object> subGraph = entityGraph.addSubgraph("students");

        List<Course> courses = em
                .createNamedQuery("query_get_all_courses", Course.class)
                .setHint("jakarta.persistence.loadgraph", entityGraph)
                .getResultList();

        for (Course course : courses) {
            logger.info("Course -> {} Students -> {}", course, course.getStudents());
        }
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_JoinFetch() {
        List<Course> courses = em
                .createNamedQuery("query_get_all_courses_join_fetch", Course.class)
                .getResultList();
        for (Course course : courses) {
            logger.info("Course -> {} Students -> {}", course, course.getStudents());
        }
    }

}
