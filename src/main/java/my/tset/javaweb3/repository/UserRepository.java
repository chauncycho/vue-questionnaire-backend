package my.tset.javaweb3.repository;

import my.tset.javaweb3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUsernameAndPassword(String username, String password);

    User findByToken(String token);
}
