package com.iquery.repository;

import com.iquery.model.Answer;
import com.iquery.model.Question;
import com.iquery.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("questionRepository")
public interface QuestionRepository extends CrudRepository<Question, Long>{

    List<Question> findAllByUser(User user);
    List<Question> findAllByUserNotOrderByNoOfLikesDesc(User user);
    List<Question> findAllByUserNotOrderByDateDesc(User user);
    List<Question> findAllByUserNot(User user);
    Question findById(int id);
    List<Question> findAllByAnswersIn(List<Answer> answer);

}
