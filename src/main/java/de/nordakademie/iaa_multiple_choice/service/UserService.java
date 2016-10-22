package de.nordakademie.iaa_multiple_choice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import de.nordakademie.iaa_multiple_choice.domain.User;
import de.nordakademie.iaa_multiple_choice.domain.UserRepository;

public class UserService {

    private final UserRepository UserRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.UserRepository = userRepository;
    }

    @Transactional
    public void createUser(final User user) {
        UserRepository.createUser(user);
    }

    @Transactional(readOnly = true)
    public List<User> listAll() {
        return UserRepository.findAll();
    }
}
