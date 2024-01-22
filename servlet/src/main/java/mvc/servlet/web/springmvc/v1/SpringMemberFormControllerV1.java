package mvc.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
스프링이 자동으로 스프링 빈으로 등록한다 내부에 @Component가 있음
스프링 mvc에서 컨트롤러로 인식

@Component
@RequestMapping 이거 두개랑 똑같음
 */

@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
