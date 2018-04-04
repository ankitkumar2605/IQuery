package com.iquery.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "value", nullable = false)
    @NotEmpty(message = "Please provide question")
    private String value;

    @Column(name = "noOfLikes", nullable = true)
    private int noOfLikes;

    @Column(name = "noOfAnswers", nullable = true)
    private int noOfAnswers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private  User user;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private Set<Answer> answers = new HashSet<>();

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private Set<Like> likePost = new HashSet<>();

    @Column(name = "is_like")
    @org.springframework.data.annotation.Transient
    private boolean isLike;

    @Column(name = "is_ans")
    @org.springframework.data.annotation.Transient
    private boolean isAns;

    @Column(name = "lastUpdated")
    @org.springframework.data.annotation.Transient
    private String lastUpdated;

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean isAns() {
        return isAns;
    }

    @CreatedDate
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAns(boolean ans) {
        isAns = ans;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public int getNoOfAnswers() {
        return noOfAnswers;
    }

    public void setNoOfAnswers(int noOfAnswers) {
        this.noOfAnswers = noOfAnswers;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getNoOfLikes() {
        return noOfLikes;
    }

    public void setNoOfLikes(int noOfLikes) {
        this.noOfLikes = noOfLikes;
    }

    public Set<Like> getLikePost() {
        return likePost;
    }

    public void setLikePost(Set<Like> likePost) {
        this.likePost = likePost;
    }
}
