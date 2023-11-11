package kim.kimspirng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KimController {

    @GetMapping("hello")//URL 매칭
    public String hello(Model model) {
        //키 값
        model.addAttribute("data", "hello!");
        return "hello"; //템플릿 이름
    }
    @GetMapping("hello-mvc")//URL 매칭
    public String helloMvc(@RequestParam("name") String name, Model model) {
        //키 값
        model.addAttribute("name", name);
        return "hello-template"; //템플릿 이름
    }
}
