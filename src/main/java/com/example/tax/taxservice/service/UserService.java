package com.example.tax.taxservice.service;

import com.example.tax.taxservice.model.User;
import com.example.tax.taxservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll(orderByFirstLastNameAsc());
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    private Sort orderByFirstLastNameAsc() {
        return Sort.by(Sort.Direction.ASC, "firstName")
                .and(Sort.by(Sort.Direction.ASC, "lastName"));
    }

}
