package com.iquery.repository;

import com.iquery.model.Answer;
import com.iquery.model.Question;
import com.iquery.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("answerRepository")
public interface AnswerRepository extends CrudRepository<Answer, Long> {
    List<Answer> findAllByUser(User user);
    Answer findAnswerByQuestionAndUser(Question question,User user);

}
