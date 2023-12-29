package kim.kimspirng.service;

import kim.kimspirng.domain.Member;
import kim.kimspirng.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach //메서드 실행 이전에 작동, Dependency Injection
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach //메서드 하나가 끝날때마다 실행, 메서드별로 의존성을 제거해야함
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

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
        
        /* 이하의 try catch 문으로 해도 되지만 위의 방식으로 간단하게 할 수 있다
        try {
            memberService.join(member2);
            fail(); //예외가 발생해야 하는데 발생 안해서 fail
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }
        */


        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}