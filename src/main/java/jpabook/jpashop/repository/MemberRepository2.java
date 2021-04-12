package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

//spring data jpa 이용하는 방식으로 개선
public interface MemberRepository2 extends JpaRepository<Member,Long> {
    Member findByUsername(String username);
}
