package hello.jdbc.domain;

import lombok.Data;

@Data
public class Member {
    private String memberId; //PK
    private int money;

    public Member() {
    }

    public Member(String memberId, int money) {
        this.memberId = memberId;
        this.money = money;
    }
}
