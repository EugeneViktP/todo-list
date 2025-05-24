package auditor.todo_list.web;

import auditor.todo_list.model.TodoList;
import auditor.todo_list.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String showLists(Model model) {
        model.addAttribute("lists", todoService.getAllLists());
        model.addAttribute("newList", new TodoList());
        return "todo";
    }

    @PostMapping("list")
    public String createList(@ModelAttribute TodoList newList) {
        todoService.createList(newList.getName());
        return "redirect:/todo";
    }

    @PostMapping("/task")
    public String addTask(@RequestParam Long listId,
                          @RequestParam String taskDesc) {
        todoService.addTaskToList(listId, taskDesc);
        return "redirect:/todo";
    }
}
