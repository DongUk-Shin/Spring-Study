package mvc.servlet.web.frontcontroller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.servlet.web.frontcontroller.MyView;
import mvc.servlet.web.frontcontroller.v1.ControllerV1;
import mvc.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import mvc.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import mvc.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerV2Map = new HashMap<>();

    public FrontControllerServletV2() {
        controllerV2Map.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerV2Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerV2Map.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("FrontControllerServletV2.service");
        String requestURI = request.getRequestURI();
        
        //다형성에 의해 인터페이스로 받을 수 있다
        ControllerV2 controller = controllerV2Map.get(requestURI);
        
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyView myView = controller.process(request, response);
        myView.render(request, response);


    }
}
