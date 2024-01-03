package kim.kimspirng.service;

import kim.kimspirng.domain.Member;
import kim.kimspirng.repository.MemberRepository;
import kim.kimspirng.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    //@Commit 이거하면 안사라짐
    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello11111");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void joinException() { //join에서 예외가 발생하는 경우의 테스트 케이스
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);

        //람다식으로 어떤 메서드를 동작 시켰는데 특정 예외가 발생하는지 검증, 오류 메세지를 위해 병수 e로 받는다
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //오류 메세지도 검증
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

    }
}