package my.tset.javaweb3.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//问题id

    @ManyToOne
    @JoinColumn(name = "question_list_id", columnDefinition = "int")
    @JsonIgnoreProperties({"questions","users","createdByUser"})
    private QuestionList questionList;//所属问卷

    @Column(nullable = false)
    private Integer questionIndex = 0;

    @Column(nullable = false,columnDefinition = "longtext")
    private String content;//问题内容

    @Column(name = "true_answer",columnDefinition = "text")
    private String trueAnswer;//问题正确答案

    @Column(columnDefinition = "double(10,2)")
    private Double score;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "question")
    @JsonIgnoreProperties({"question","user","questionList"})
    private List<Answer> answers;//该问题的所有解答

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public QuestionList getQuestionList() {
        return questionList;
    }

    public void setQuestionList(QuestionList questionList) {
        this.questionList = questionList;
    }

    public Integer getQuestionIndex() {
        return questionIndex;
    }

    public void setQuestionIndex(Integer questionIndex) {
        this.questionIndex = questionIndex;
    }

    public JSONObject getContent() {
        return JSON.parseObject(content);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(String trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
