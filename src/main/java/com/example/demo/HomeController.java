package com.example.demo;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    public static long idCounter=0;
    ArrayList<ToDo> toDoList = new ArrayList<>();

    @GetMapping("/toDoForm")
    public String loadFormPage(Model model){
        ToDo toDo = new ToDo();
        idSetter(toDo);
        model.addAttribute("toDo", toDo);
        return "todoform";
    }

    @PostMapping("/displayEntry")
    public String loadEntryPage(@Valid ToDo toDo, BindingResult result) {
        if (result.hasErrors()) {
            return "todoform";
        } else {
            toDoList.add(toDo);
            return "displayentry";
        }
    }

    @RequestMapping("/displayList")
    public String loadListPage(Model model){
        model.addAttribute("toDoList", toDoList);
        return "displaylist";
    }

    static void idSetter(ToDo toDo){
        idCounter = idCounter + 1;
        toDo.setId(idCounter);
    }
}
