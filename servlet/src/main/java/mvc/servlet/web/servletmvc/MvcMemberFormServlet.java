package mvc.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response); 
        /*
        다른 jsp나 서블릿으로 이동 가능하게 함 -> 컨트롤러의 역할
        서버 내부에서 다시 호출 발생 redirect가 아님!!

        WEB-INF의 파일들은 외부에서 호출해도 안 불러짐
        항상 컨트롤러를 거쳐야 함

        redirect vs forward
        리다이렉트는 실제 클라이언트(웹 브라우저)에 응답이 나갔다가, 클라이언트가 redirect 경로로 다시 요청
        따라서 클라이언트가 인지, URL 경로도 실제로 변경

        포워드는 서버 내부에서 일어나는 호출이기 때문에 클라이언트가 인지 x
         */

    }
}
