package me.clearsoul2.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ClearSoul2Controller {

    @GetMapping("/clearsoul2/test")
    public Map<String, Object> test(){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("결과값", "안녕 장수인");
        return returnMap;
    }

    @GetMapping("/clearsoul2/test2/{param}")
    public Map<String, Object> test2(@PathVariable("param") String param){
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("결과값", "안녕 " + param);
        return returnMap;
    }

}
