package guild.api.security.controller;


import guild.api.security.controller.interfaces.ICourseController;
import guild.api.security.entity.Course;
import guild.api.security.response.ResponseObject;
import guild.api.security.service.interfaces.ICourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/*  Khi 1 request http gui client -> server, DispatcherServlet sẽ xử lý request và điều hướng đến
    controller cụ thể, dựa trên cấu hình @RequestMapping và những phuong thức của http để xác định
    phuong thức nào sẽ được thực hiện
 */
//Spring Boot sử dụng "Component Scanning" cơ chế quét tự động các package để tìm và quản lý các bean
//@RestController đánh dấu class này là 1 bean và được quản lý bởi Spring IoC Container
//Spring IoC Container giúp tạo và quản lý đối tượng trong ứng dụng. Hoạt động bằng cách loại bỏ sự phụ thuộc giữa các đối tượng
@RestController
@RequestMapping(path = "/api/courses")
@CrossOrigin(origins = "*")
public class CourseController implements ICourseController {

    /*
      Không cần tạo new ICourseService(), thay vào đó yêu cầu IoC Container
      cung cấp một instance ICourseService để tiêm vào courseService
     */
    @Autowired
    private ICourseService courseService;

    //Khi không sử dụng @autowired
//    public CourseController(ICourseService courseService) {
//        this.courseService = courseService;
//    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping(path = "")
    public ResponseObject getAllCourse() {
        return courseService.getAllCourse();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping(path = "/{id}")
    public ResponseObject getCourseById(@PathVariable("id") int id) {
        return courseService.getCourseById(id);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping(path = "")
    public ResponseObject insertCourse(@Valid @RequestBody Course course) {
        return courseService.insertCourse(course);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public ResponseObject removeCourse(@PathVariable("id") int id) {
        return courseService.removeCourse(id);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping(path = "/{id}")
    public ResponseObject updateCourse(@PathVariable("id") int id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }
}
