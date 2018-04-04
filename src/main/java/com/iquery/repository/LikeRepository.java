package com.iquery.repository;

import com.iquery.model.Like;
import com.iquery.model.Question;
import com.iquery.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("likeRepository")
public interface LikeRepository extends CrudRepository<Like, Long>  {
    Like findLikeByUserAndQuestion(User user, Question question);
}
