package com.iquery.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "value", nullable = false, unique = true)
    @NotEmpty(message = "Please provide question")
    private String value;

    @Column(name = "noOfLikes", nullable = true)
    private int noOfLikes;

    @Column(name = "noOfUnlikes", nullable = true)
    private int noOfUnlikes;

    @ElementCollection
    @CollectionTable(name ="answers")
    private List<String> answers = new ArrayList<String>();


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

    public int getNoOfUnlikes() {
        return noOfUnlikes;
    }

    public void setNoOfUnlikes(int noOfUnlikes) {
        this.noOfUnlikes = noOfUnlikes;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
