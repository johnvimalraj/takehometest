package john.hometest.todo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import john.hometest.todo.rest.*;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoApplicationTests {

	@Autowired
	public TodoListController ctrl;

	@Test
	public void contextLoads() {

		//after init find all the todo item


	}


	@Test
	public void testTodoUpdate() {
		for (Todo todo : ctrl.getCompleteItems()
				) {
			todo.setComplete(false);
			ctrl.update(todo);
		}
		assert (ctrl.getCompleteItems().size() == 0);
	}


	 @Test
	 public void testTodoDelete() {


	 	 ctrl.remove(1);
		 assert (ctrl.getAll().size() == 6);

    }
   	@Test                                                
   	public void testTodoAll() {
   		// given                                          
   		List allTodo = ctrl.getAll();                     
   		// DB initilizer loaded 7                         
   		Integer size = allTodo.size();                    
                                                            
   		// then                                           
   		assert (size == 7);
   		assert (ctrl.getIncompleteItems().size() == 5);   
   		assert (ctrl.getCompleteItems().size() == 2);     
                                                            
                                                            
   	}                                                     
                                                            
}