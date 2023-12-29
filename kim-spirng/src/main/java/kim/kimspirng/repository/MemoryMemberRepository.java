package kim.kimspirng.repository;

import kim.kimspirng.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    public static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //NULL 값 반환 방지
    }

    @Override
    public Optional<Member> findByName(String name) { //이름과 일치하는 회원 반환
        return store.values().stream() //store 객체를 스트림으로 변환 후 람다식
                .filter(member -> member.getName().equals(name)) //이름 일치하는거 필터링
                .findAny(); //필터링 된 정보중에서 암꺼나 하나
        //회원이 포함된 optional 반환
        //없으면 빈 optional 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore() {
        store.clear();
    }
}
