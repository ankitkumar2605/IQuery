package com.iquery.repository;

import com.iquery.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findByConfirmationToken(String confirmationToken);
    User findByRollNo(String rollNo);
    User findByRollNoAndEnabled(String rollNo, boolean isEnabled);
}