package com.iquery.controller;

import com.iquery.model.Question;
import com.iquery.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;


    @RequestMapping(value = "/saveQuestion", method = RequestMethod.POST)
    public String processQuestion(@RequestParam("value") String value){
        Question question = new Question();
        question.setValue(value);
        questionService.saveQuestion(question);
        return "redirect:/home";
    }
}
