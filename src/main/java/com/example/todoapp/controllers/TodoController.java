package com.example.todoapp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapp.models.Todo;
import com.example.todoapp.repositories.TodoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api("/api/todos")
@CrossOrigin("*")
public class TodoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	TodoRepository todoRepository;

	@GetMapping("/todos")
	@ApiOperation(value = "Return a list of tasks.", notes = "The list is order by creation date.", response = Todo[].class)
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = Todo[].class) })
	public List<Todo> getAllTodos() {
		LOGGER.info("Getting TODOs list");
		Sort sortByCreatedAtDesc = new Sort(Sort.Direction.DESC, "createdAt");
		return todoRepository.findAll(sortByCreatedAtDesc);
	}

	@GetMapping(value = "/todos/{id}")
	@ApiOperation(value = "Return a task.", notes = "The task returned contains the id specified in the path parameter.")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Success", response = Todo.class),
        @ApiResponse(code = 404, message = "Not found")
    })
	public ResponseEntity<Todo> getTodoById(
			@ApiParam(required = true, name = "id", value = "ID of the task you want return") 
			@PathVariable("id") String id) {
		
		LOGGER.info("Getting TODO item with id: " + id);
		return todoRepository.findById(id).map(todo -> ResponseEntity.ok().body(todo))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("/todos")
	@ApiOperation(value = "Create a new task.", response = Todo.class)
	@ApiResponses({
        @ApiResponse(code = 200, message = "Success", response = Todo.class),
        @ApiResponse(code = 400, message = "Bad request")
    })
	public Todo createTodo(
			@ApiParam(required = true, name = "task", value = "New task.") 
			@Valid @RequestBody Todo todo) {
		LOGGER.info("Creating new TODO item");
		todo.setCompleted(false);
		return todoRepository.save(todo);
	}

	@PutMapping(value = "/todos/{id}")
	@ApiOperation(value = "Update an existing task.", response = Todo.class)
	@ApiResponses({
        @ApiResponse(code = 200, message = "Success", response = Todo.class),
        @ApiResponse(code = 400, message = "Bad request"),
        @ApiResponse(code = 404, message = "Not found")
    })
	public ResponseEntity<Todo> updateTodo(
			@ApiParam(required = true, name = "id", value = "ID of the task you want to update.") 
			@PathVariable("id") String id,
			@ApiParam(required = true, name = "task", value = "Updated task.") 
			@Valid @RequestBody Todo todo) {
		LOGGER.info("Updating TODO item with id: " + id);
		return todoRepository.findById(id).map(todoData -> {
			todoData.setTitle(todo.getTitle());
			todoData.setCompleted(todo.getCompleted());
			Todo updatedTodo = todoRepository.save(todoData);
			return ResponseEntity.ok().body(updatedTodo);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(value = "/todos/{id}")
	@ApiOperation(value = "Delete a existing task.")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Success", response = Todo.class),
        @ApiResponse(code = 404, message = "Not found")
    })
	public ResponseEntity<?> deleteTodo(
			@ApiParam(required = true, name = "id", value = "ID of the task you want to delete.") 
			@PathVariable("id") String id) {
		LOGGER.info("Deleting TODO item with id: " + id);
		return todoRepository.findById(id).map(todo -> {
			todoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}