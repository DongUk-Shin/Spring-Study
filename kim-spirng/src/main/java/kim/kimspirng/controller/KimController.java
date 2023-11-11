package kim.kimspirng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KimController {

    @GetMapping("hello")//URL 매칭
    public String hello(Model model) {
        //키 값
        model.addAttribute("data", "hello!!dd!!!!!!!!");
        return "hello"; //템플릿 이름
    }
}
