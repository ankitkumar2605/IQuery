package com.iquery.service;

import com.iquery.model.Like;
import com.iquery.model.Question;
import com.iquery.model.User;
import com.iquery.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class LikeService  {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    public  boolean isQuestionLikeByUser(User user, Question question){
        Like like = likeRepository.findLikeByUserAndQuestion(user,question);
        if(like == null)
            return  false;
        return true;
    }

    public int saveLikeForQuestion(String questionId){
        Question question = questionService.getQuestion(questionId);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        Like like = likeRepository.findLikeByUserAndQuestion(user,question);
        if(like == null){
            Like likeQuestion = new Like();
            likeQuestion.setQuestion(question);
            likeQuestion.setUser(user);
            question.setNoOfLikes(question.getNoOfLikes()+1);
            question.getLikePost().add(likeQuestion);
            question.setDate(new Date());
            questionService.saveQuestion(question);
        }else{
            question.setNoOfLikes(question.getNoOfLikes()-1);
            question.getLikePost().remove(like);
            question.setDate(new Date());
            questionService.saveQuestion(question);
            likeRepository.delete(like);
        }
        return question.getNoOfLikes();
    }
}
