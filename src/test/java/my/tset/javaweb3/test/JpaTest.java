package my.tset.javaweb3.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import my.tset.javaweb3.entity.User;
import my.tset.javaweb3.repository.AnswerRepository;
import my.tset.javaweb3.repository.QuestionListRepository;
import my.tset.javaweb3.repository.QuestionRepository;
import my.tset.javaweb3.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class JpaTest {
    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    QuestionListRepository questionListRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    public void userDemo(){
//        User user = new User();
//        user.setUsername("chauncy1");
//        user.setPassword("332501080");
//        System.out.println(JSONObject.toJSONString(userRepository.save(user)));
        System.out.println(JSONObject.toJSONString(userRepository.findById(1).get()));
    }

//    @Test
//    public void questionListDemo(){
//        System.out.println(questionListRepository.findAll(PageRequest.of(0,10, Sort.Direction.DESC,"id")));
//    }
}
