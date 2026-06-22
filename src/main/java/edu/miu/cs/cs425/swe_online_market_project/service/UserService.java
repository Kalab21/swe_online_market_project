package edu.miu.cs.cs425.swe_online_market_project.service;

import edu.miu.cs.cs425.swe_online_market_project.DTO.SignUp;
import edu.miu.cs.cs425.swe_online_market_project.model.User;

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
