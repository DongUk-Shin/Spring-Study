package mvc.servlet.web.springmvc.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


@Component("/springmvc/old-controller") //스프링 빈의 이름 지정, 스프링 빈 이름으로 핸들러를 찾음 / 1. 핸들러 매핑
public class OldController implements Controller {

    /*
    2. Controller 인터페이스를 실행 할 수 있는 핸들러 어댑터를 찾아야 한다
    스프링은 이미 필요한 핸들러 매핑과 핸들러 어댑터를 거의 다 만들어 놓음 그래서 그냥 실행된다

    핸들러 매핑 우선순위
    0 = RequestMappingHandlerMapping : 애노테이션 기반의 컨트롤러인 @RequestMapping에서 사용
    1 = BeanNameUrlHandlerMapping : 스프링 빈의 이름으로 핸들러를 찾는다. (현재 코드에서는 이거)

    핸들러 어댑터 우선순위
    0 = RequestMappingHandlerAdapter : 애노테이션 기반의 컨트롤러인 @RequestMapping에서 사용
    1 = HttpRequestHandlerAdapter : HttpRequestHandler 처리
    2 = SimpleControllerHandlerAdapter : Controller 인터페이스(애노테이션X, 과거에 사용) 처리 (현재 코드에서는 이거)
     */
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form"); //논리적인 이름이기에 뷰 리졸버가 있어야 함
    }

    /*
    스프링부트에서 제공한다 application.properties 에서 설정 할 수 있음
    spring.mvc.view.prefix=/WEB-INF/views/
    spring.mvc.view.suffix=.jsp
     */
}
