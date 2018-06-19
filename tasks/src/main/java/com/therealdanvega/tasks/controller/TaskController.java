package com.therealdanvega.tasks.controller;

import com.therealdanvega.tasks.domain.Task;
import com.therealdanvega.tasks.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(value = {"", "/"})
    public Iterable<Task> listTasks() {
        return taskService.list();
    }

    @PostMapping("/save")
    public Task saveTask(Task task) {
        return taskService.save(task);
    }


}

