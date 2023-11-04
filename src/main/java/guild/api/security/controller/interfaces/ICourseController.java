package guild.api.security.controller.interfaces;

import guild.api.security.entity.Course;
import guild.api.security.response.ResponseObject;

public interface ICourseController {
    public ResponseObject getAllCourse();
    public ResponseObject getCourseById(int courseId);
    public ResponseObject insertCourse(Course course);
    public ResponseObject removeCourse(int id);
    public ResponseObject updateCourse(int id, Course course);
}
