package com.iquery.service;

import com.iquery.model.User;
import com.iquery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByConfirmationToken(String confirmationToken) {
        return userRepository.findByConfirmationToken(confirmationToken);
    }

    public User findByRollNo(String rollNo) {
        return userRepository.findByRollNo(rollNo);
    }

    public User findByRollNoAndEnabled(String rollNo, boolean isEnabled) {
        return userRepository.findByRollNoAndEnabled(rollNo, isEnabled);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

//    @Transactional(readOnly = true)
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsernameAndEnabled(username, true);
//        if (user == null) {
//            throw new BadCredentialsException("Username not found.");
//        } else {
//            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_NO_ROLES");
//            noRoles.add(simpleGrantedAuthority);
//            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), noRoles);
//        }
//    }

}
