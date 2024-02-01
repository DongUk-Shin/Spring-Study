package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController { 

    @RequestMapping("/headers") //스프링 헤더에는 많은 정보를 받을 수 있다
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale, //나라
                          @RequestHeader MultiValueMap<String, String> headerMap, //하나의 키에 여러 값 받을 수 있는 맵, 헤더 싹다 가져옴
                          @RequestHeader("host") String host, //헤더에서 하나만 key-value
                          @CookieValue(value = "myCookie", required = false) String cookie) { //false는 없어도 되는거

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);
        return "ok";
        /*
        2024-01-23T15:42:24.191+09:00  INFO 4616 --- [nio-8080-exec-2] h.s.b.request.RequestHeaderController    : request=org.apache.catalina.connector.RequestFacade@a495a7f
        2024-01-23T15:42:24.192+09:00  INFO 4616 --- [nio-8080-exec-2] h.s.b.request.RequestHeaderController    : response=org.apache.catalina.connector.ResponseFacade@5052d702
        2024-01-23T15:42:24.192+09:00  INFO 4616 --- [nio-8080-exec-2] h.s.b.request.RequestHeaderController    : httpMethod=GET
        2024-01-23T15:42:24.192+09:00  INFO 4616 --- [nio-8080-exec-2] h.s.b.request.RequestHeaderController    : locale=ko_KR
        2024-01-23T15:42:24.192+09:00  INFO 4616 --- [nio-8080-exec-2] h.s.b.request.RequestHeaderController    : headerMap={host=[localhost:8080], connection=[keep-alive], ... 생략
        2024-01-23T15:42:24.192+09:00  INFO 4616 --- [nio-8080-exec-2] h.s.b.request.RequestHeaderController    : header host=localhost:8080
        2024-01-23T15:42:24.192+09:00  INFO 4616 --- [nio-8080-exec-2] h.s.b.request.RequestHeaderController    : myCookie=null

        MultiValueMap<String, String> map = new LinkedMultiValueMap();
        map.add("keyA", "value1");
        map.add("keyA", "value2");
        //[value1,value2]
        List<String> values = map.get("keyA");
        */
    }
}
