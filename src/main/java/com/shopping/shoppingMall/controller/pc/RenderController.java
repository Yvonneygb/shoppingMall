package com.shopping.shoppingMall.controller.pc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RenderController {

    @GetMapping("/index")
    public String renderIndex() {
        return "pc/index";
    }


}
