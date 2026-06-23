package com.markethub.app.service;

import com.markethub.app.DTO.SignUp;
import com.markethub.app.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);

    List<User> getAllUsers();

    User addUser(User user);

    User updateUser(User user, Long id);

    void deleteUserById(Long userId);

    List<User> getSellers();

    User approveSeller(long sellerId);

    User registerUser(SignUp signUp);
}
