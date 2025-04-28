package com.synex.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.synex.domain.Task;
import com.synex.model.TaskDto;
import com.synex.repository.TaskRepository;

@Controller
public class TaskController {

	private final TaskRepository taskrepository;

	@Autowired
	public TaskController(TaskRepository taskrepository) {
		this.taskrepository = taskrepository;
	}

	@PostMapping("/tasks")
	public ResponseEntity<Long> createTask(@RequestBody TaskDto taskdto) {
		Task task = new Task();
		task.setDescription(taskdto.getDescription());
		task.setTitle(taskdto.getTitle());
		Task savedTask = taskrepository.save(task);
		return ResponseEntity.status(HttpStatus.OK).body(savedTask.getId());
	}

	@GetMapping("/tasks/{id}")
	public ResponseEntity<Task> getTask(@PathVariable Long id) {
		Optional<Task> taskOptional = taskrepository.findById(id);

		if (taskOptional.isPresent()) {
			return ResponseEntity.ok(taskOptional.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@PutMapping("/tasks/{id}")
	public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskDto updatedTask) {
		Optional<Task> task = taskrepository.findById(id);
		if (task.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		Task existingTask = task.get();
		String requestedStatus = updatedTask.getStatus();
		boolean validStatus = Arrays.stream(Task.TaskStatus.values())
				.anyMatch(status -> status.name().equalsIgnoreCase(requestedStatus));
		if (!validStatus) {
			String availableStatuses = "Sorry we dont have that status. Available status are CREATED, APPROVED, REJECTED, BLOCKED, DONE";
			return ResponseEntity.ok(availableStatuses);
		}
		existingTask.setTitle(updatedTask.getTitle());
		existingTask.setDescription(updatedTask.getDescription());
		existingTask.setStatus(Task.TaskStatus.valueOf(requestedStatus.toUpperCase()));

		Task updated = taskrepository.save(existingTask);
		return ResponseEntity.status(HttpStatus.OK).body(updated);
	}

	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
		if (taskrepository.existsById(id)) {
			taskrepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping("/tasks/describe/{id}")
	public ResponseEntity<String> describeTask(@PathVariable Long id) {
		return taskrepository.findById(id)
				.map(task -> ResponseEntity.ok("Description of Task [" + task.getId() + " : " + task.getTitle()
						+ "] is : " + task.getDescription()))
				.orElse(ResponseEntity.ok("Task with id " + id + " does not exists"));
	}

	@GetMapping("/tasks")
	public ResponseEntity<List<Task>> getAllTasks() {
		List<Task> tasks = taskrepository.findAll();
		return ResponseEntity.ok(tasks);
	}

	@GetMapping("/tasks/describe")
	public ResponseEntity<List<String>> describeAllTasks() {
		List<String> descriptions = taskrepository.findAll().stream().map(task -> "Description of Task [" + task.getId()
				+ " : " + task.getTitle() + "] is : " + task.getDescription()).collect(Collectors.toList());
		return ResponseEntity.ok(descriptions);
	}

}
