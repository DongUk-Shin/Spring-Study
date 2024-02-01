package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {


    @GetMapping//회원 목록 조회
    public String user() {
        return "get users";
    }

    @PostMapping //회원 등록
    public String addUsers() {
        return "post user";
    }

    @GetMapping("/{userId}") //회원 조회
    public String findUser(@PathVariable String userId) {
        return "get userId= " + userId;
    }

    @PatchMapping("/{userId}") //회원 수정
    public String updateUser(@PathVariable String userId) {
        return "update userId= " + userId;
    }

    @DeleteMapping("/{userId}") //회원 삭제
    public String deleteUser(@PathVariable String userId) {
        return "delete userId= " + userId;
    }

}
