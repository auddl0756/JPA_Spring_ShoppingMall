package jpabook.jpashop.web;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Item;
import jpabook.jpashop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(){
        //System.out.println("hey");
        return "jspDir/items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(Book book){
        itemService.saveItem(book);
        return "redirect:/items";
    }

    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items",items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId,Model model){
        Item item = itemService.findOne(itemId);
        model.addAttribute("item",item);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@ModelAttribute("item") Book item){
        itemService.saveItem(item);
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
