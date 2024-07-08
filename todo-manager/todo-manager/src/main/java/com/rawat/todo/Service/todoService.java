package com.rawat.todo.Service;

import com.rawat.todo.exceptions.resourceNotFoundException;
import com.rawat.todo.todoMain.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Component
@Service
public class todoService {

    static Logger logger = LoggerFactory.getLogger(todoService.class);

   static List<Todo> todos = new ArrayList<>();


    // Create Todo Method
    public Todo createTodo(Todo todo){
        todos.add(todo);
        logger.info("Todos {}", todos);
        return todo;
    }



    public static List<Todo> getAllTodos() {
        return todos;
    }
    public static Todo getTodo(int todoId){
//        Todo todo = todos.stream().filter(t-> todoId == t.getId()).findAny().get();
        Todo todo = todos.stream().filter(t-> todoId == t.getId()).findAny().stream().findAny().orElseThrow(()->new resourceNotFoundException("Todo Not Found with given ID ", HttpStatus.NOT_FOUND));
        logger.info("Todo {}",todo);
        return todo;
    }

    public static Todo UpdateTodo(int todoId, Todo todo){
            List<Todo> newUpdateList = todos.stream().map(t->{
                if(t.getId() == todoId){
                //perform update
                    t.setTitle(todo.getTitle());
                    t.setContent(todo.getContent());
                    t.setStatus(todo.getStatus());
                return t;
            }else{
                // do nothing
                return t;
            }
            }).collect(Collectors.toList());
                todos = newUpdateList;
                todo.setId(todoId);
                return todo;
    }

    public static void deleteTodo(int todoId) {
        logger.info("Deleting Todo");
        List<Todo> newList = todos.stream().filter(t->t.getId() != todoId).collect(Collectors.toList());
        todos = newList;

    }

}
