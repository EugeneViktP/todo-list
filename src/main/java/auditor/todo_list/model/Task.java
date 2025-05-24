package auditor.todo_list.model;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private boolean completed;

    @ManyToOne
    private TodoList todoList;
}
