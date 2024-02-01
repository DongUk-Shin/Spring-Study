package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //롬복이 제공, 로그 객체 만들어줌
@RestController //ResponseBody와 유사, 그냥 문자열 반환 함
public class LogTestController {

    //private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @RequestMapping("/log-test")
    public String loTest() {
        String name = "Spring";
        //log.trace("trace log={}" + name); 더하기 연산 하면 안됨 성능 손해 
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        return "ok";
        /*
        2024-01-22T21:18:53.833+09:00  INFO 12768 --- [nio-8080-exec-7]
        h.springmvc.basic.LogTestController      :  info log=Spring
         */
    }
}
