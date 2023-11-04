package guild.api.security.service.interfaces;

import guild.api.security.entity.Course;
import guild.api.security.response.ResponseObject;

public interface ICourseService {
    public ResponseObject getAllCourse();
    public ResponseObject getCourseById(int courseId);

    public ResponseObject insertCourse(Course course);
    public boolean checkCourseExist(String name);
    public ResponseObject removeCourse(int id);
    public ResponseObject updateCourse(int id, Course course);
}
