package my.tset.javaweb3.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import my.tset.javaweb3.entity.Question;
import my.tset.javaweb3.entity.QuestionList;
import my.tset.javaweb3.entity.User;
import my.tset.javaweb3.repository.QuestionListRepository;
import my.tset.javaweb3.repository.QuestionRepository;
import my.tset.javaweb3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class QuestionController {
    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionListRepository questionListRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/question/submit")
    public void addQuestionList(@RequestBody Map map){
        JSONObject jsonObject = JSON.parseObject(JSONObject.toJSONString(map));
        JSONArray jsonQuestionList = jsonObject.getJSONArray("questionList");
        JSONObject jsonUser = jsonObject.getJSONObject("user");
        String questionTitle = jsonObject.getString("questionTitle");
        String profile = jsonObject.getString("profile");

        logger.info(jsonQuestionList.toJSONString());
        logger.info(jsonUser.toJSONString());
        logger.info(questionTitle);

        //获取User
        User user = userRepository.findById(jsonUser.getInteger("id")).get();

        //构造QuestionList
        QuestionList questionList = new QuestionList();
        questionList.setTitle(questionTitle);
        questionList.setCreatedByUser(user);
        questionList.setCreateTime(new Date());
        questionList.setProfile(profile);

        //构造Question
        List<Question> list = new ArrayList<>();
        for (int i = 0 ; i < jsonQuestionList.size() ; i ++){
            JSONObject jsonQuestion = jsonQuestionList.getJSONObject(i);
            Question question = new Question();
            question.setQuestionIndex(i);
            question.setContent(JSONObject.toJSONString(jsonQuestion));
            question.setQuestionList(questionList);
            question.setScore(jsonQuestion.getDouble("score"));
            question.setTrueAnswer(JSONObject.toJSONString(jsonQuestion.get("trueAnswer")));
            list.add(question);
        }

        //保存
        saveQuestionListAndQuestions(questionList,list);

        System.out.println("OK");
    }
    @Transactional(rollbackOn = Exception.class)
    public void saveQuestionListAndQuestions(QuestionList questionList, List<Question> questions){
        //保存QuestionList
        questionListRepository.save(questionList);

        //保存Question
        for(Question question : questions){
            questionRepository.save(question);
        }
    }

    @GetMapping("/question/questionList")
    public Page<QuestionList> getAllQuestionByPage(Integer page, Integer size){
        if (page==null){
            page = 0;
        }
        if (size==null){
            size = 10;
        }
        Pageable pageable = PageRequest.of(page,size,Sort.by(Sort.Direction.DESC, "createTime"));
        return questionListRepository.findAll(pageable);
    }

    @GetMapping("/question/question")
    public QuestionList getQuestionListById(Integer id){
        return questionListRepository.findById(id).get();
    }
}
