package guru.springframework.sfgpetclinicconor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    public static final String INDEX = "index";

    @RequestMapping({"", "/", INDEX, INDEX+".html"})
    public String index() {
        return "index";
    }

    @RequestMapping("/oups")
    public String oupsHandler() {
        return "notImplementedYet";
    }
}
