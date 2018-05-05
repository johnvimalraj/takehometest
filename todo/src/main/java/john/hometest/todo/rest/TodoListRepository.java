package john.hometest.todo.rest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoListRepository extends JpaRepository<Todo, Long>{
    List<Todo> findByComplete(boolean complete);
}
