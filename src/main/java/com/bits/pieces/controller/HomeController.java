package com.bits.pieces.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home Controller
 *
 * @author Nate Vardell
 * @since 4/22/2019
 */
@Controller
public class HomeController {
    @GetMapping(value="/")
    public String routeToHome(){
        return "forward:index.html";
    }

    @GetMapping("/health")
    public String health() {
        return "HC Health Mapping";
    }
}
