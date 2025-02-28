package ch.bfh.bti7081.s2019.black.spitexorganizer.task.view.assembler;

import ch.bfh.bti7081.s2019.black.spitexorganizer.task.model.Task;
import ch.bfh.bti7081.s2019.black.spitexorganizer.task.view.dtos.TaskDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskAssembler {
    public List<TaskDto> toDtos(List<Task> tasks) {
        List<TaskDto> taskDtos = new ArrayList<>();
        for (Task task : tasks) {
            taskDtos.add(toDto(task));
        }
        return taskDtos;
    }

    public TaskDto toDto(Task task) {
        TaskDto taskDtos = new TaskDto();
        taskDtos.setId(task.getId());
        taskDtos.setDescription(task.getDescription());
        taskDtos.setDone(task.getDone());

        return taskDtos;
    }
}
