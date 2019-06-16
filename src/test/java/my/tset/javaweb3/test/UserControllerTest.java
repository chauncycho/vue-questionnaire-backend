package my.tset.javaweb3.test;

import my.tset.javaweb3.entity.User;
import my.tset.javaweb3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerTest {
    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/user")
    private User getUserById(@RequestParam Integer id){
        return userRepository.findById(id).get();
    }
}
