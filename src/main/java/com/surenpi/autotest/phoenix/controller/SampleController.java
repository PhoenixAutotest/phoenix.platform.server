package com.surenpi.autotest.phoenix.controller;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleController {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
}