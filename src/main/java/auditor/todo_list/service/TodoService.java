package auditor.todo_list.service;

import auditor.todo_list.model.Task;
import auditor.todo_list.model.TodoList;
import auditor.todo_list.repository.TaskRepository;
import auditor.todo_list.repository.TodoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoListRepository todoListRepository;
    private final TaskRepository taskRepository;

    public TodoService(TodoListRepository todoListRepository,
                       TaskRepository taskRepository) {
        this.todoListRepository = todoListRepository;
        this.taskRepository = taskRepository;
    }

    public TodoList createList(String name) {
        TodoList list = new TodoList();
        list.setName(name);
        return todoListRepository.save(list);
    }

    public Task addTaskToList(Long listId, String taskDescription) {
        TodoList list = todoListRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("List not found"));

        Task task = new Task();
        task.setDescription(taskDescription);
        task.setCompleted(false);

        list.addTask(task);
        return taskRepository.save(task);
    }

    public List<TodoList> getAllLists() {
        return todoListRepository.findAll();
    }

    public void toggleTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }

    public void deleteList(Long listId) {
        todoListRepository.deleteById(listId);
    }
}
