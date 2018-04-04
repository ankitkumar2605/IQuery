package com.iquery.controller;

import com.iquery.model.Question;
import com.iquery.model.User;
import com.iquery.repository.StorageService;
import com.iquery.service.QuestionService;
import com.iquery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView showProfilePage(ModelAndView modelAndView) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        List<Question> questions = questionService.getUserQuestions(user);
        modelAndView.addObject("questions", questions);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("profile");
        return modelAndView;

    }

    @RequestMapping(value = "/yourAnswer", method = RequestMethod.GET)
    public ModelAndView showYourAnswerPage(ModelAndView modelAndView) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        List<Question> questions = questionService.getYourAnswers(user);
        modelAndView.addObject("questions", questions);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("yourAnswer");
        return modelAndView;

    }


    @PostMapping(value = "/saveProfileImage")
    public String handleImageUpload(@RequestParam("profileFile") MultipartFile profileFile,
                                   RedirectAttributes redirectAttributes) {
        storageService.store(profileFile);
        return "redirect:/profile";
    }

}
