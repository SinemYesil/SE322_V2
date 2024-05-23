package org.example;

import java.util.ArrayList;
import java.util.List;
//Receiver (sinem)
class Employee { // ViewUnAssigned yapılmadı + separateTask diye bi method var onu konuşmamız lazım
    private String id;
    private String name;
    private String contactDetails;
    private String jobRole;
    private ArrayList<Task> taskArray;
    private ArrayList<Task> completedTask;
    private String password;

    public Employee(String id, String name, String contactDetails, String password, String jobRole) {
        this.id = id;
        this.name = name;
        this.contactDetails = contactDetails;
        this.password= password;
        this.jobRole = jobRole;
        this.completedTask = new ArrayList<>();
        this.taskArray = new ArrayList<>();
    }

    public ArrayList<Task> getCompletedTask() {
        return completedTask;
    }

    public void setCompletedTask(ArrayList<Task> completedTask) {
        this.completedTask = completedTask;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getters and setters for employee attributes
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public List<Task> getTaskArray() {
        return taskArray;
    }

    public void setTaskArray(ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
    }

    public void addTaskArray(Task goal) { //also calls assignTask script
        this.taskArray.add(goal);
        goal.setStatus(Task.TaskStatus.INPROGRESS);
        goal.AddToEmpList(this);
    }

    public List<Task> getCompletedTasks() {
        return completedTask;
    }

    public void addCompletedTasks(Task task) {
        if (task != null) {
            this.completedTask.add(task);
        } else {
            System.out.println("Cannot add a null task to completed tasks.");
        }
    }

    // Method to assign a task to this employee
    private void assignTask(Task task) {
        task.setAssigned(true);
    }

    public void ViewAssignedTasks(){
        for(int i=0;i < taskArray.size();i++){
            System.out.println(i + "-Task ID is " + getTaskArray().get(i).getTaskId()+ " Task Description is " + getTaskArray().get(i).getDescription());
        }
    }

    //bu method şuan boş. Genel task arrayinden assigned olmayanlar check edilip burada yazdırılabilir
    public void ViewUnAssignedTasks(){

    }

    //Bu methodu Workera mı yoksa Managera mı koyacağımıza karar verememiştik ben Employee koydum direkt yeri değişebilir ileride
    public void CompleteTask(Task task){
        task.setStatus(Task.TaskStatus.DONE);
        completedTask.add(task);
        taskArray.remove(task);
    }




    // toString method to represent employee information as a string
    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contactDetails='" + contactDetails + '\'' +
                ", jobRole='" + jobRole + '\'' +
                ", allTasks=" + taskArray +
                ", completedTasks =" + completedTask +
                '}';
    }
}
