package mvc.servlet.web.frontcontroller.v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc.servlet.web.frontcontroller.MyView;

import java.io.IOException;

public interface ControllerV2 {
    //view를 반환하는것에 차이
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
