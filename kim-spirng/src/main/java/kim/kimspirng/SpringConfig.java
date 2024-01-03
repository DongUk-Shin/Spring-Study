package kim.kimspirng;

import kim.kimspirng.repository.JpaMemberRepository;
import kim.kimspirng.repository.MemberRepository;
import kim.kimspirng.repository.MemoryMemberRepository;
import kim.kimspirng.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    /*
    EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }
    */

    @Autowired
    private final MemberRepository memberRepository;
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /* 스프링 데이터 jpa를 사용했기에 자동으로 저장소는 컨테이너에 등록 됨
    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        //return new JpaMemberRepository(em);

    }
     */
}
