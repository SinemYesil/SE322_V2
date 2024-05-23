package org.example;

import java.util.List;

interface TaskComponent {
    void addSubTask(TaskComponent task);
    void removeSubTask(TaskComponent task);
    List<TaskComponent> getSubTasks();
    void assignTask(Employee employee);

}

