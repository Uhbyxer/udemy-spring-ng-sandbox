import { Component, OnInit } from '@angular/core';
import {Task} from "../task.model";
import {TaskService} from "../task.service";

@Component({
  selector: 'app-tasks-list',
  templateUrl: './tasks-list.component.html',
  styleUrls: ['./tasks-list.component.css']
})
export class TasksListComponent implements OnInit {

  tasks: Task[] = [];

  constructor(private taskService: TaskService) { }

  ngOnInit() {
    this.taskService.getTasks()
      .subscribe(
        (tasks: any[]) => {
          this.tasks = tasks
        },
        (error) => console.log(error)
      );

    this.taskService.onTaskAdded.subscribe(
      (newTask: Task) => this.tasks.push(newTask)
    );
  }

  onTaskChange(event, task: Task) {
    this.taskService.saveTask(task, event.target.checked).subscribe();
  }

  getDueDateLabel(task: Task) {
    return task.completed ? "badge-success" : "badge-primary";
  }
}
