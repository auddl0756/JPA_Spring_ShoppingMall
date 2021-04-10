package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    @PersistenceContext
    EntityManager manager;

    public void save(Order order){
        manager.persist(order);
    }

    public Order findOne(Long id){
        return manager.find(Order.class,id);
    }


    //이 부분은 나중에 다시 봐야함. Criteria~,Predicate,Root 등 모름
    // 일단은 전체적인 맥락부터 잡고
    public List<Order> findAll(OrderSearch orderSearch){
        List<Order> ret = new ArrayList<>();

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);

        List<Predicate> criteria = new ArrayList<>();
        //주문 상태 검색
        if(orderSearch.getOrderStatus()!=null){
            Predicate status = criteriaBuilder.equal(root.get("status"),orderSearch.getOrderStatus());
            criteria.add(status);
        }

        //회원 이름 검색
        if(StringUtils.hasText(orderSearch.getMemberName())){
            Join<Order, Member> memberJoin = root.join("member",JoinType.INNER);
            Predicate name
                    = criteriaBuilder.like(memberJoin.<String>get("name"),"%"+orderSearch.getMemberName()+"%");
            criteria.add(name);
        }

        criteriaQuery.where(criteriaBuilder.and(criteria.toArray(new Predicate[criteria.size()])));

        TypedQuery<Order> query = manager.createQuery(criteriaQuery).setMaxResults(1000);

        return query.getResultList();
    }
}
