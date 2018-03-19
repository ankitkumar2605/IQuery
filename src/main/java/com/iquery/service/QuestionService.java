package com.iquery.service;

import com.iquery.model.Question;
import com.iquery.model.User;
import com.iquery.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }



}
