package auditor.todo_list.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "todoList", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        task.setTodoList(this);
        this.tasks.add(task);
    }


}
