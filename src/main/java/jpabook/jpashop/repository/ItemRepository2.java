package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

//spring data jpa 이용하는 방식으로 개선
public interface ItemRepository2 extends JpaRepository<Item,Long> {
}
