package guild.api.security.config;

import guild.api.security.service.CourseService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;


@Aspect
@Configuration
public class AspectConfig {
    private Logger logger = LoggerFactory.getLogger(CourseService.class);

    //JoinPoint dùng để khai báo rằng Aspect đó sẽ được gọi khi nào.
    @Before("execution(* guild.api.security.service.*.*(..))")
    public void before(JoinPoint joinPoint){

        //Advice là logic chúng ta muốn thực hiện
        logger.info("before called " + joinPoint.toString());
    }

    /*
        - Method khi được gọi nó sẽ gọi vào around này trước
        - sau đó gọi đến joinPoint.proceed() Để thực hiện hàm;
        - rồi tiếp tục gọi đến đoạn code tiếp theo.
     */
//    @Around("execution(* guild.api.security.service.*.*(..))")
//    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
//        Long startTime = System.currentTimeMillis();
//        logger.info("Start Time Taken by {} is {}", joinPoint, startTime);
//
//        joinPoint.proceed();
//
//        Long timeTaken = System.currentTimeMillis() - startTime;
//        logger.info("Time Taken by {} is {}", joinPoint, timeTaken);
//    }
}
