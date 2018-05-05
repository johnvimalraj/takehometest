package john.hometest.todo;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import john.hometest.todo.rest.*;

import java.util.List;

@RestController
@RequestMapping(value = "/todos")
@Api(
        name = "Todo List API",
        description = "Provides a list of todo items",
        stage = ApiStage.RC)
public class TodoListController {

    private TodoListRepository todoListRepository;

    @Autowired
    public TodoListController(
        TodoListRepository todoListRepository){
            this.todoListRepository = todoListRepository;
        }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ApiMethod(description = "Get all the todo items")
    public List<Todo> getAll(){
        return todoListRepository.findAll();
    }

    @RequestMapping(value = "/incomplete", method = RequestMethod.GET)
    @ApiMethod(description = "Get all the incomplete todo items")
    public List<Todo> getIncompleteItems(){
        return todoListRepository.findByComplete(false);
    }

    @RequestMapping(value = "/complete", method = RequestMethod.GET)
    @ApiMethod(description = "Get all complete todo items")
    public List<Todo> getCompleteItems(){
        return todoListRepository.findByComplete(true);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ApiMethod(description = "Create a todo item and save it to the database")
    public List<Todo> create(@RequestBody String note){
        todoListRepository.save(new Todo(note,false));
        return todoListRepository.findAll();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ApiMethod(description = "update todo item and save it to the database")
    public List<Todo> update(@RequestBody Todo Todo){
        todoListRepository.save(Todo);

        return todoListRepository.findAll();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ApiMethod(description = "Remove the todo item with the provided ID from the database")
    public List<Todo> remove(@ApiPathParam(name = "id") @PathVariable long id){
        todoListRepository.deleteById(id);

        return todoListRepository.findAll();
    }
}
