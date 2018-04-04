package com.iquery.controller;

import com.iquery.model.Question;
import com.iquery.model.User;
import com.iquery.service.AnswerService;
import com.iquery.service.QuestionService;
import com.iquery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnswerService answerService;


    @RequestMapping(value = "/saveQuestion", method = RequestMethod.POST)
    public String processQuestion(@RequestParam("value") String value) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        Question question = new Question();
        question.setValue(value);
        question.setUser(user);
        question.setDate(new Date());
        questionService.saveQuestion(question);
        return "redirect:/home";
    }

    @RequestMapping(value = "/questions", method = RequestMethod.GET)
    public ModelAndView showQuestionPage(ModelAndView modelAndView) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        List<Question> questions = questionService.getUserQuestions(user);
        modelAndView.addObject("questions", questions);
        modelAndView.setViewName("yourquestion");
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView showTopQuestionsPage(ModelAndView modelAndView) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        List<Question> questions = questionService.getTopQuestions(user);
        modelAndView.addObject("questions", questions);
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/newQuestion", method = RequestMethod.GET)
    public ModelAndView showNewQuestionsPage(ModelAndView modelAndView) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        List<Question> questions = questionService.getNewQuestions(user);
        modelAndView.addObject("questions", questions);
        modelAndView.setViewName("newQuestion");
        return modelAndView;
    }
    @RequestMapping(value = "/answer", method = RequestMethod.GET)
    public ModelAndView showAnswerPage(ModelAndView modelAndView) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        List<Question> questions = questionService.getAllQuestions(user);
        modelAndView.addObject("questions", questions);
        modelAndView.setViewName("answer");
        return modelAndView;
    }

    @RequestMapping(value = "/saveAnswer", method = RequestMethod.POST)
    public String processAnswer(@RequestParam("questionId") String questionId,@RequestParam("answer") String answer,
                                @RequestParam("callingScreen") String callingScreen) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        answerService.saveAnswer(questionId,answer,user);
        return "redirect:/"+callingScreen;
    }

}
