package com.iquery.service;

import com.iquery.model.Answer;
import com.iquery.model.Question;
import com.iquery.model.User;
import com.iquery.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private AnswerService answerService;

    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    public List<Question> getUserQuestions(User user) {

        List<Question> questions = questionRepository.findAllByUser(user);
        questions.forEach(question -> {
            question.setLike(likeService.isQuestionLikeByUser(user, question));
            question.setAns(answerService.isQuestionAnswerByUser(user,question));
        });
        return questions;
    }

    public List<Question> getTopQuestions(User user) {

        List<Question> questions = questionRepository.findAllByUserNotOrderByNoOfLikesDesc(user);
        questions.forEach(question -> {
            question.setLike(likeService.isQuestionLikeByUser(user, question));
            question.setAns(answerService.isQuestionAnswerByUser(user,question));
        });
        return questions;
    }

    public List<Question> getNewQuestions(User user) {

        List<Question> questions = questionRepository.findAllByUserNotOrderByDateDesc(user);
        questions.forEach(question -> {
            question.setLike(likeService.isQuestionLikeByUser(user, question));
            question.setAns(answerService.isQuestionAnswerByUser(user,question));
            question.setLastUpdated(getLastUpdated(question.getDate()));
        });
        return questions;
    }
    public List<Question> getAllQuestions(User user) {

        List<Question> questions = questionRepository.findAllByUserNot(user);
        questions.forEach(question -> {
            question.setLike(likeService.isQuestionLikeByUser(user, question));
            question.setAns(answerService.isQuestionAnswerByUser(user,question));
        });
        return questions;
    }

    public List<Question> getYourAnswers(User user) {
        List<Answer> answerList = answerService.findAnswersByUser();
        List<Question> questions = questionRepository.findAllByAnswersIn(answerList);
        return questions;
    }

    public Question getQuestion(String quesId) {
        return questionRepository.findById(Integer.parseInt(quesId));
    }

    private String getLastUpdated(Date date){
        long diff = new Date().getTime()-date.getTime();
        System.out.println(diff+" ygyyuyu");
        long diffMinutes = diff / (60 * 1000) % 60;
        return diffMinutes + "min ago";
    }
}
