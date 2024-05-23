package org.example;
//Concrete Command (sinem)
class AssignTaskCommand implements Command {


    @Override
    public void execute(Employee employee, Task task) {
        task.assignTask(employee);
    }
}
