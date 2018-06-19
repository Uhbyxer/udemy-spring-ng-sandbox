package com.therealdanvega.tasks.repository;

import com.therealdanvega.tasks.domain.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository <Task, Long> {


}
