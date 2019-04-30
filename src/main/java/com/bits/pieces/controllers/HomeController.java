package com.bits.pieces.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home Controller
 *
 * @author Nate Vardell
 * @since 4/22/2019
 */
@Controller
public class HomeController {
    @RequestMapping(value="/")
    public String routeToHome(){
        return "forward:index.html";
    }

    @RequestMapping("/health")
    public String health() {
        return "HC Health Mapping";
    }
//    @GetMapping
//    public String routeToHome(){
//        return "forward:index.html";
//    }
//
//    @GetMapping("health")
//    public String health() {
//        return "Testing Health";
//    }
}
