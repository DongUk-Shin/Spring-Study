package mvc.servlet.web.frontcontroller.v3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.servlet.web.frontcontroller.ModelView;
import mvc.servlet.web.frontcontroller.MyView;
import mvc.servlet.web.frontcontroller.v2.ControllerV2;
import mvc.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import mvc.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import mvc.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();

    public FrontControllerServletV3() {
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("FrontControllerServletV3.service");
        String requestURI = request.getRequestURI();
        
        //다형성에 의해 인터페이스로 받을 수 있다
        ControllerV3 controller = controllerV3Map.get(requestURI);
        
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }


        Map<String, String> paramMap = createParamMap(request);
        ModelView modelView = controller.process(paramMap);

        String viewName = modelView.getViewName();//논리 이름 ex)new-form
        //이름 변경
        MyView view = viewResolver(viewName);
        view.render(modelView.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames().asIterator().forEachRemaining(paramName ->
                paramMap.put(paramName, request.getParameter(paramName))
        );
        return paramMap;
    }
}
