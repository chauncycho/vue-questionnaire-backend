package my.tset.javaweb3.controller;

import com.alibaba.fastjson.JSONObject;
import my.tset.javaweb3.entity.User;
import my.tset.javaweb3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorldController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/user")
    private User addUser(User user){
        return userRepository.save(user);
    }

    @RequestMapping("/test")
    private String test(){
        Map<String,String> map = new HashMap<>();
        map.put("msg","success");
        return JSONObject.toJSONString(map);
    }

    @RequestMapping("/api/test")
    private String test1(){
        Map<String,String> map = new HashMap<>();
        map.put("msg","success");
        return JSONObject.toJSONString(map);
    }
}
