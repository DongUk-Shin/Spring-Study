package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamController {

    /**
     * 쿼리 파라미터 조회 GET 쿼리 파라미터, HTML form
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username= {}, age= {}", username, age);
        response.getWriter().write("ok");
    }

    /**
     * 간단한 버전
     */
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {

        log.info("username= {}, age= {}", memberName, memberAge);
        return "ok";
    }

    /**
     * 요청 파라미터의 이름과 변수가 @RequestParam의 key 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {

        log.info("username= {}, age= {}", username, age);
        return "ok";
    }

    /*
     * String, int, Integer 등의 단순 타입이면
     * @RequestParam 조차 생략 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {

        log.info("username= {}, age= {}", username, age);
        return "ok";
    }

    /**
     * required = false 면 없어도 됨
     * 기본값은 true
     * 만약 false 인데 안 넣으면 null이 들어감 -> 기본 타입은 500 에러
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                 @RequestParam(required = false) Integer age) {

        log.info("username= {}, age= {}", username, age);
        return "ok";
    }

    /**
     * 기본값 설정 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username,
                                       @RequestParam(required = false, defaultValue = "-1")  int age) {

        log.info("username= {}, age= {}", username, age);
        return "ok";
    }

    /**
     * 다 가져옴, MultiValueMap 도 가능
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Objects> paramMap) {

        log.info("username= {}, age= {}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }
}
