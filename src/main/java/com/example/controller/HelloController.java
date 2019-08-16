package com.example.controller;

import com.example.dto.PaginationDTO;
import com.example.service.Questionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @Autowired
    private Questionservice questionservice;

    @GetMapping("/")
    public String index(  Model model,
                        @RequestParam(name = "page" ,defaultValue = "1")   Integer page  ,
                        @RequestParam(name = "size",defaultValue ="5" ) Integer size)  {

        PaginationDTO pagination = questionservice.Allselect(page ,size);

        model.addAttribute("pagination",pagination);
        return "index";
    }
}

