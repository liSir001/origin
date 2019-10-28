package com.lisir.cn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
public class RestApiController {

    @RequestMapping(value = "/demo", method = RequestMethod.GET, produces = APPLICATION_JSON_UTF8_VALUE)
    public String query() {
        return "RestTemplate!";
    }
}
