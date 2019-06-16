package my.tset.javaweb3.controller;

import my.tset.javaweb3.entity.User;
import my.tset.javaweb3.repository.UserRepository;
import my.tset.javaweb3.utils.Md5Encrypt;
import my.tset.javaweb3.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public User login(String username, String password){
        password = Md5Encrypt.encrypt(password);
        User user = userRepository.findByUsernameAndPassword(username,password);
        if (user!=null){
            user.setToken(UUIDUtil.getRandomUUID());
            return userRepository.save(user);
        }
        return null;
    }
}
