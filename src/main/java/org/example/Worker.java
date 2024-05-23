package org.example;

class Worker extends Employee{
    Manager manager;

    public Worker(String id, String name, String contactDetails, String password, String jobRole,Manager manager_1){
        super(id, name, contactDetails, password, jobRole);
        manager=manager_1;
    }

    @Override
    public void CompleteTask(Task task) {  //directly related with manager, it is like a broadcast in the end
        super.CompleteTask(task);
        manager.AddToWaitingForApprove(this,task);
    }

    public String toString() {
        return "Worker{" +
                "id='" + super.getId() + '\'' +
                ", name='" + super.getName() + '\'' +
                ", contactDetails='" + getContactDetails() + '\'' +
                ", jobRole='" + getJobRole() + '\'' +
                ", allTasks=" + super.getTaskArray() +
                ", completedTasks =" + super.getCompletedTasks() +
                '}';
    }
}
