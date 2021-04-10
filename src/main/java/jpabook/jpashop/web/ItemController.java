package jpabook.jpashop.web;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(){
        //System.out.println("hey");
        return "jsp/items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(Book book){
        itemService.saveItem(book);
        return "redirect:/items";
    }

    @GetMapping("/test")
    public String test(){
        return "views/home";
    }

    @GetMapping("/test2")
    public String test2(){
        return "views/signup-form";
    }
}
