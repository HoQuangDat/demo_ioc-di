package guild.api.security.service;

import guild.api.security.entity.Course;
import guild.api.security.repository.ICourseRepository;
import guild.api.security.response.ResponseObject;
import guild.api.security.service.interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Override
    public ResponseObject getAllCourse() {
        List<Course> courses = courseRepository.findAll();

        if (courses.isEmpty()) {
            return new ResponseObject(HttpStatus.OK.name(), "List courses is empty", null);
        }

        return new ResponseObject(HttpStatus.OK.name(), "Get all list course successfully", courses);
    }

    @Override
    public ResponseObject getCourseById(int courseId) {
        var course = courseRepository.findById(courseId);

        if (course.isPresent()) {
            return new ResponseObject(HttpStatus.OK.name(), "Found a course in list", course.get());
        }

        return new ResponseObject(HttpStatus.OK.name(), "Not found any course", course);
    }


    @Override
    public ResponseObject insertCourse(Course course) {
        try {
            var checkResponse = checkCourseExist(course.getName());

            if (checkResponse) {
                courseRepository.save(course);

                return new ResponseObject(HttpStatus.OK.name(), "Insert new a course successfully", course);
            }

            return new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Course is existing in list courses", null);
        }catch (HttpClientErrorException e){
            return new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Insert new a cser failed", e.getMessage());
        }
    }

    @Override
    public boolean checkCourseExist(String name) {
        try {
            var response = courseRepository.findCourseByName(name);

            if(response == null)
            {
                return true;
            }

            return  false;
        }catch (HttpClientErrorException e){
            return false;
        }
    }

    @Override
    public ResponseObject removeCourse(int id) {
        try {
            var courseFound = courseRepository.findById(id);

            if (courseFound.isPresent()) {
                courseRepository.delete(courseFound.get());

                return new ResponseObject(HttpStatus.OK.name(), "Delete a course successfully", null);
            }

            return new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Course not found in list courses", null);
        }catch (HttpClientErrorException e){
            return new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Delete a course failed", e.getMessage());
        }
    }

    @Override
    public ResponseObject updateCourse(int id, Course course) {
        try {
            var checkResponse = checkCourseExist(course.getName());

            var courseFound = courseRepository.findById(id);

            if (courseFound.isPresent())
            {
                if (checkResponse) {
                    courseFound.get().setName(course.getName());
                    courseRepository.save(courseFound.get());

                    return new ResponseObject(HttpStatus.OK.name(), "Update a course successfully", courseFound);
                }
                else {
                    return new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(), "course is existing in list courses", null);
                }
            }

            return new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Not found a course to update", null);
        }catch (HttpClientErrorException e){
            return new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Update a course failed", e.getMessage());
        }
    }
}
