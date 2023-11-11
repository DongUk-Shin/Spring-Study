package kim.kimspirng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        model.addAttribute("name", name);
        return "hello-template"; //템플릿 이름
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // 뷰 없이 바로 감
    }
    @GetMapping("hello-api")
    @ResponseBody //JSON 으로 반환 하도록 기본 setting
    public Hello helloApi(@RequestParam("name") String name) { //JSON 으로 Return
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}
