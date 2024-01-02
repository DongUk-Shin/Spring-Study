package kim.kimspirng.controller;

import kim.kimspirng.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;

    //컨테이너에 있는 memberService를 연결해준다 멤버 컨트롤러가 생성될 때 빈에 등록되어있는 객체를 가져옴
    //의존성 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
