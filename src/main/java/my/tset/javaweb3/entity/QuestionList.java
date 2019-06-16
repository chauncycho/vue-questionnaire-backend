package my.tset.javaweb3.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class QuestionList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//id

    @Column(nullable = false)
    private String title;//问卷名称

    @OneToMany(mappedBy = "questionList")
    @JsonIgnoreProperties({"questionList","answers"})
    private List<Question> questions;//试卷包含的题

    private String profile;

    @ManyToMany(mappedBy = "answeredQuestionLists")
    @JsonIgnoreProperties({"answeredQuestionLists","questionLists"})
    private List<User> users;//答题人

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;//创建时间

    @ManyToOne
    @JoinColumn(name = "created_user_id",columnDefinition = "int")
    @JsonIgnoreProperties({"questionLists","answeredQuestionLists"})
    private User createdByUser;//建题人

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
