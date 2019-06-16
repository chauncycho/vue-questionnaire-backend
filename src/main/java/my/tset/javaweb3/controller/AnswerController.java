package my.tset.javaweb3.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import my.tset.javaweb3.entity.Answer;
import my.tset.javaweb3.entity.Question;
import my.tset.javaweb3.entity.QuestionList;
import my.tset.javaweb3.entity.User;
import my.tset.javaweb3.repository.AnswerRepository;
import my.tset.javaweb3.repository.QuestionListRepository;
import my.tset.javaweb3.repository.QuestionRepository;
import my.tset.javaweb3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Map;

@RestController
public class AnswerController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionListRepository questionListRepository;

    @Autowired
    AnswerRepository answerRepository;

    @PostMapping("/answer/submit")
    @Transactional(rollbackOn = Exception.class)
    public void submitAnswer(@RequestBody Map map){
        JSONObject questionList = JSON.parseObject(JSONObject.toJSONString(map));

        Integer userId = questionList.getJSONObject("createdByUser").getInteger("id");
        User user = userRepository.findById(userId).get();

        Integer questionListId = questionList.getInteger("id");
        QuestionList questionListFromDatabase = questionListRepository.findById(questionListId).get();

        Date answerTime = new Date();
        JSONArray questions = questionList.getJSONArray("questions");

        for (int i = 0 ; i < questions.size() ; i ++){
            Answer answer = new Answer();
            answer.setAnswerTime(answerTime);
            answer.setUser(user);
            answer.setQuestionList(questionListFromDatabase);

            Integer questionId = questions.getJSONObject(i).getInteger("id");
            Question question = questionRepository.findById(questionId).get();
            answer.setQuestion(question);

            String myAnswer = questions.getJSONObject(i).getString("myAnswer");
            answer.setAnswer(myAnswer);

            answerRepository.save(answer);
        }
    }
}
