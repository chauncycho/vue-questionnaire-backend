package my.tset.javaweb3.repository;

import my.tset.javaweb3.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
}
