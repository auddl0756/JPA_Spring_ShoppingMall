package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository {
    @PersistenceContext
    EntityManager manager;

    public void save(Item item){
        if(item.getId()==null) manager.persist(item);
        else manager.merge(item);
    }

    public Item findOne(Long id){
        return manager.find(Item.class,id);
    }

    public List<Item> findAll(){
        return manager.createQuery("select i from Item i",Item.class).getResultList();
    }
}
