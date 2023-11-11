package kim.kimspirng.repository;

import kim.kimspirng.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


class MemoryMemberRepositoryTest { //테스트를 클래스 단위로 돌리면 메서드들은 순서가 랜덤
    MemoryMemberRepository r = new MemoryMemberRepository();
    @AfterEach //메서드 하나가 끝날때마다 실행, 메서드별로 의존성을 제거해야함
    public void afterEach() {
        r.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("DongUk");

        r.save(member);
        Member result = r.findById(member.getId()).get();
        //System.out.println("result = " + (result == member));
        //assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Spring1");
        r.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        r.save(member2);

        Member result = r.findByName("Spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("Spring1");
        r.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        r.save(member2);

        List<Member> result = r.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
