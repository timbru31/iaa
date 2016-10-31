package de.nordakademie.iaa_multiple_choice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.nordakademie.iaa_multiple_choice.domain.User;
import de.nordakademie.iaa_multiple_choice.domain.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createUser(final User user) {
        userRepository.createUser(user);
    }

    @Transactional(readOnly = true)
    public User findByMail(final String email) {
        return userRepository.findbyMail(email);
    }

    @Transactional(readOnly = true)
    public User findByToken(final String token) {
        return userRepository.findByToken(token);
    }

    @Transactional(readOnly = true)
    public List<User> listAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User updateUser(final User user) {
        return userRepository.updateUser(user);
    }
}
