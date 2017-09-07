package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/8/31.
 */
@RestController
public class IndexController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello,this is a springboot demo";
    }
}
