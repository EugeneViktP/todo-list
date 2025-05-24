package auditor.todo_list.repository;

import auditor.todo_list.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    Optional<TodoList> findByName(String name);
}
