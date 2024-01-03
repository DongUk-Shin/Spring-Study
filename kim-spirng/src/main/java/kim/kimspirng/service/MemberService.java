package kim.kimspirng.service;

import kim.kimspirng.domain.Member;
import kim.kimspirng.repository.MemberRepository;
import kim.kimspirng.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
//@Service //컨테이너에 멤버서비스 등록이 된다
public class MemberService {
    private final MemberRepository memberRepository;

    //@Autowired //객체가 생성될 때 컨테이너에 있는 객체를 가져옴
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    /**
     * 회원가입
     */

    public Long join(Member member) {
        //이름이 중복 되면 안됨
        validateDuplicateMember(member); // 메서드로 뺌

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).
                ifPresent(m -> { //널이 아니라 어떤 값이 "존재" 한다면, Optional의 메서드
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
