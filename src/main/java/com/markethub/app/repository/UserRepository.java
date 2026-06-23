package com.markethub.app.repository;

import com.markethub.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUserName(String userName);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    @Query(value = "select u from User u inner join u.roles roles where roles.roleType = 'ROLE_SELLER'")
    List<User> findUsersBySellerRole();
}
