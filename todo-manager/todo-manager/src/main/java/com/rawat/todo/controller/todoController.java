package com.rawat.todo.controller;

import com.rawat.todo.Service.todoService;
import com.rawat.todo.todoMain.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
public class todoController {



    Logger logger = LoggerFactory.getLogger(todoController.class);
    static Random random = new Random();

    @Autowired
    public todoService TodoService;

    // Create
    @PostMapping
    public ResponseEntity<Todo> TodoController(@RequestBody Todo todo){

//        String str = null;
//        logger.info("{}",str.length());

        int id = random.nextInt(9999999);
        todo.setId(id);

       // Date
        Date currentDate  =new Date();
        logger.info("Current Date: {}",currentDate);
        todo.setCurrentDate(currentDate);

        // complete Date
        logger.info("Todo Complete Data {}", todo.getCompleteDate());

        // creating todos

        logger.info("Create Todo");

        // class Service to create todo.

        Todo todo1 = TodoService.createTodo(todo);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);

    }

    // get all todo methods()
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoHandler(){
        List<Todo> allTodos = todoService.getAllTodos();
        return new ResponseEntity<>(allTodos, HttpStatus.OK);
    }

    // If We want Specific Todo.
    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodo(@PathVariable int todoId){
        Todo todo = todoService.getTodo(todoId);
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo todoWithNewDetail, @PathVariable int todoId){
        Todo todo = todoService.UpdateTodo(todoId,todoWithNewDetail);
        return ResponseEntity.ok(todo);
    }

    // Delete Todo
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable int todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo Deleted");
    }

    //exception handlers
    // This is Local Controller Exception Handler
    // Priority will be of this rather than GlobalExceptionHandler.

    // NullPointerException Handler
//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<String> nullPointerExceptionHandler(NullPointerException ex){
//        System.out.println(ex.getMessage());
//        return new ResponseEntity<>("Null Pointer Exception Generated.."+ex.getMessage()+" Due to (Cause)"+ex.getCause()+" ",HttpStatus.SERVICE_UNAVAILABLE);
//    }
//
//    // NumberFormatException
//    @ExceptionHandler(NumberFormatException.class)
//    public ResponseEntity<String> numberFormatExceptionHandler(NumberFormatException numex){
//        System.out.println(numex.getMessage());
//        return new ResponseEntity<>("Number Format Exception Generated.."+numex.getMessage()+" Due to (Cause)"+numex.getCause()+" ",HttpStatus.SERVICE_UNAVAILABLE);
//    }
}
