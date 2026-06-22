package edu.miu.cs.cs425.swe_online_market_project.service.imp;

import edu.miu.cs.cs425.swe_online_market_project.model.User;
import edu.miu.cs.cs425.swe_online_market_project.repository.UserRepository;
import edu.miu.cs.cs425.swe_online_market_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);}

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getSellers() {
        return userRepository.findUsersBySellerRole();
    }

    @Override
    public User approveSeller(long sellerId) {
        User seller = userRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Seller not found: " + sellerId));
        seller.setApprovedSeller(true);
        return userRepository.save(seller);
    }

    @Override
    public User updateUser(User user, Long id) {
        User user1 = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        user1.setUserId(user.getUserId());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setUserName(user.getUserName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setRoles(user.getRoles());
        user1.setApprovedSeller(user.isApprovedSeller());
        user1.setAddress(user.getAddress());
        return userRepository.save(user1);
    }
}
