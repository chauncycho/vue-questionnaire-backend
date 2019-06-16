package my.tset.javaweb3.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import my.tset.javaweb3.utils.Md5Encrypt;
import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//id

    @Column(unique = true, nullable = false, length = 100)
    private String username;//用户名

    @Column(nullable = false)
    private String password;//密码

    //manager/editor/normal
    @Column(nullable = false)
    private String authority = "normal";//权限

    private String profile;

    private String token;//token

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user","questionList","question"})
    private List<Answer> answers;//已答的题

    @OneToMany(mappedBy = "createdByUser")
    @JsonIgnoreProperties({"createdByUser","users","questions"})
    private List<QuestionList> questionLists;//创建的问卷

    @ManyToMany
    @JoinTable(name = "user_question_list", joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "question_list_id",referencedColumnName = "id")})
    @JsonIgnoreProperties({"users","createdByUser","questions"})
    private List<QuestionList> answeredQuestionLists;//已答问卷


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        password = Md5Encrypt.encrypt(password);
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPassword() {
        return password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<QuestionList> getQuestionLists() {
        return questionLists;
    }

    public void setQuestionLists(List<QuestionList> questionLists) {
        this.questionLists = questionLists;
    }

    public List<QuestionList> getAnsweredQuestionLists() {
        return answeredQuestionLists;
    }

    public void setAnsweredQuestionLists(List<QuestionList> answeredQuestionLists) {
        this.answeredQuestionLists = answeredQuestionLists;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
