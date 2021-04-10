package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {
    @PersistenceContext
    EntityManager manager;

    public void save(Member member){
        manager.persist(member);
    }

    public Member findOne(Long id){
        return manager.find(Member.class,id);
    }

    public List<Member> findAll(){
        return manager.createQuery("select m from Member m",Member.class).getResultList();
    }

    public List<Member> findByName(String name){
        return manager.createQuery("select m from Member m where m.name=:name",Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
