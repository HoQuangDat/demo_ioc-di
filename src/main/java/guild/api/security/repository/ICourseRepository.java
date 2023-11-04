package guild.api.security.repository;

import guild.api.security.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICourseRepository extends JpaRepository<Course, Integer> {
    public Course findCourseByName(String name);

}
