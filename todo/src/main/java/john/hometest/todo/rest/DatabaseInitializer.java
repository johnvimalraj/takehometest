package john.hometest.todo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    private TodoListRepository todoListRepository;

    @Autowired
    public DatabaseInitializer(TodoListRepository todoListRepository){
        this.todoListRepository = todoListRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        List<Todo> todos = new ArrayList<>();

        todos.add(new Todo("Note", true));
        todos.add(new Todo("Note 1", false));
        todos.add(new Todo("Note 2", false));
        todos.add(new Todo("Note 3", false));
        todos.add(new Todo("Note 4", false));
        todos.add(new Todo("Note 5", false));


        todos.add(new Todo("Note 2", true));

        this.todoListRepository.saveAll(todos);
    }
}
