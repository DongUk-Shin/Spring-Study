package kim.kimspirng.repository;

import kim.kimspirng.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/*
    자동으로 인터페이스 구현체를 만들어주고 인터페이스를 구현해준다
    자동으로 컨테이너에 등록해준다
    -> 인젝션을 받을 수 있다
     */
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> ,MemberRepository {

    /*
    근데? name 혹은 뭐 다른것으로 조회하고자 하는 것은
    findByXXX 면 XXX로 자동으로 JPQL을 작성해준다
    따라서 메서드 이름으로만 만들 수 있다
     */
    @Override
    Optional<Member> findByName(String name);
}
