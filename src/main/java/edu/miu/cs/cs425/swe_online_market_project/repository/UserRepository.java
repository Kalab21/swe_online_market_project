package edu.miu.cs.cs425.swe_online_market_project.repository;

import edu.miu.cs.cs425.swe_online_market_project.model.User;
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
