package my.tset.javaweb3.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import my.tset.javaweb3.entity.User;
import my.tset.javaweb3.repository.UserRepository;
import my.tset.javaweb3.utils.FileUtil;
import my.tset.javaweb3.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Value("${web.upload-path}")
    private String serverFileDictPath;

    @Value("${server.port}")
    private String port;

    @Value("${web.doman}")
    private String doman;

    @PostMapping("/register/profileUpload")
    public String profileUpload(@RequestBody MultipartFile file, HttpServletRequest request){
        String fileName = file.getOriginalFilename();
        String randomFileName = UUIDUtil.getRandomUUID()+"_"+fileName;

        String pathForReturn = doman+":"+port+"/"+randomFileName;
        try {
            FileUtil.upload(file.getInputStream(),serverFileDictPath,randomFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return pathForReturn;
    }

    @PostMapping("/register/register")
    @Transactional(rollbackOn = Exception.class)
    public String register(@RequestBody Map<String,String> map){
        JSONObject jsonObject = JSON.parseObject(JSONObject.toJSONString(map));
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String profile = jsonObject.getString("profile");
        Boolean isEditor = jsonObject.getBoolean("isEditor");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setProfile(profile);
        if (isEditor) {
            user.setAuthority("editor");
        }else{
            user.setAuthority("normal");
        }

        //保存
        User user1 = userRepository.save(user);

        return "success";
    }
}