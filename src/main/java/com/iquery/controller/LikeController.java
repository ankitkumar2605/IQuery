package com.iquery.controller;

import com.iquery.model.Question;
import com.iquery.model.User;
import com.iquery.service.LikeService;
import com.iquery.service.QuestionService;
import com.iquery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LikeController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addLike", method = RequestMethod.GET,produces = {"application/json"})
    public int addLike(@RequestParam("questionId") String questionId) throws IOException {
        int noOfLikes = likeService.saveLikeForQuestion(questionId);
        return noOfLikes;
    }


}
