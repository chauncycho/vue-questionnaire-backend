package my.tset.javaweb3.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//id

    @ManyToOne
    @JoinColumn(name = "user_id", columnDefinition = "int")
    @JsonIgnoreProperties({"answers","questionLists","answeredQuestionLists"})
    private User user;//答题用户

    @ManyToOne
    @JoinColumn(name = "question_list_id",columnDefinition = "int")
    @JsonIgnoreProperties({"questions","users","createdByUser"})
    private QuestionList questionList;//所属问卷

    @ManyToOne
    @JoinColumn(name = "question_id",columnDefinition = "int")
    @JsonIgnoreProperties({"answers","questionList"})
    private Question question;//问题

    @Column(columnDefinition = "text")
    private String answer;//用户答案

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "answer_time")
    private Date answerTime;//答题时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public QuestionList getQuestionList() {
        return questionList;
    }

    public void setQuestionList(QuestionList questionList) {
        this.questionList = questionList;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }
}
