package com.iquery.repository;

import com.iquery.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository("questionRepository")
public interface QuestionRepository extends CrudRepository<Question, Long>{

}
