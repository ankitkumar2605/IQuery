package com.iquery.service;

import com.iquery.model.Answer;
import com.iquery.model.Like;
import com.iquery.model.Question;
import com.iquery.model.User;
import com.iquery.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    public void saveAnswer(String quesId, String ansValue, User user){
        Question question = questionService.getQuestion(quesId);
        Answer answer = answerRepository.findAnswerByQuestionAndUser(question,user);
        if(answer == null){
            answer = new Answer();
            answer.setUser(user);
            question.setNoOfAnswers(question.getNoOfAnswers()+1);
            answer.setUserAnswer(ansValue);
            answer.setQuestion(question);
            question.getAnswers().add(answer);
            question.setDate(new Date());
            questionService.saveQuestion(question);
        }else {
            answer.setUserAnswer(ansValue);
            answerRepository.save(answer);
            question.setDate(new Date());
            questionService.saveQuestion(question);
        }
    }

    public List<Answer> findAnswersByUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        return answerRepository.findAllByUser(user);
    }

    public  boolean isQuestionAnswerByUser(User user, Question question){
        Answer answer = answerRepository.findAnswerByQuestionAndUser(question,user);
        if(answer == null)
            return  false;
        return true;
    }

}
