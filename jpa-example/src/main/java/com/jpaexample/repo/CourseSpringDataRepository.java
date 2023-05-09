package com.jpaexample.repo;

import com.jpaexample.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
    List<Course> findByNameAndId(String name, Long id);

    List<Course> findByName(String name);

    List<Course> countByName(String name);

    List<Course> findByNameOrderByIdDesc(String name);

    List<Course> deleteByName(String name);

    @Query("Select  c  From Course c where c.name like '%Math'")
    List<Course> courseWithMathInName();

    @Query(value = "Select  *  From Course c where c.name like '%Math'", nativeQuery = true)
    List<Course> courseWithMathInNameUsingNativeQuery();

    @Query(name = "query_get_all_courses")
    List<Course> getAllCoursesUsingNamedQuery();
}
