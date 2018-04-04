package com.iquery.controller;

import com.iquery.model.Question;
import com.iquery.model.User;
import com.iquery.service.QuestionService;
import com.iquery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AnswerController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/showAnswers", method = RequestMethod.GET)
    public ModelAndView showProfilePage(ModelAndView modelAndView,String id) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        Question question = questionService.getQuestion(id);
        modelAndView.addObject("question", question);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("answers");
        return modelAndView;

    }

}
