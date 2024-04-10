package com.example.TaskDemo.examples.FormReactiveThymeLeaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

@Controller
public class RactiveController {
    @Autowired
    ProductServices productServices;
    @RequestMapping("/list")
    public String listarProdutos(Model model){
        IReactiveDataDriverContextVariable reactiveList = new ReactiveDataDriverContextVariable(productServices.buscarTodo());
        model.addAttribute("productLis", reactiveList);
        return "ready";
    }
}
