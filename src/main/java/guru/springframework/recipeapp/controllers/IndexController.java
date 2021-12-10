package guru.springframework.recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"","/","/index"})
    public String getIndex(){
        System.out.println("Some message to say... 999");
        return "index";
    }
}
