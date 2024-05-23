package org.example;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Task implements TaskComponent {
    private String taskId;
    private String description;
    private String workerComment;

    private List<TaskComponent> subTasks;  //Bunu Composite Interface liğinden dolayı TaskComponenttan alır.(sinem)    private boolean assigned; // Sinem'in kodundan geldi bunu ve alakalı şeyleri silmedim
    public enum TaskStatus{
        TODO,INPROGRESS,DONE
    }
    private TaskStatus status;
    private int taskPoint;
    private ArrayList<Employee> empList;
    private Scanner scanner;
    private boolean assigned = false;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    LocalTime startTime = LocalTime.parse("08:00", formatter);
    LocalTime endTime = LocalTime.parse("17:00", formatter);

    public Task(String taskId, String description,int point) {
        this.taskId = taskId;
        this.description = description;
        this.subTasks = new ArrayList<TaskComponent>();
        this.assigned = false;
        this.empList = new ArrayList<>();
        status = TaskStatus.TODO;
        taskPoint = point;
        scanner = new Scanner(System.in);
    }


    // Getters and setters for task attributes
    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<TaskComponent> getSubTasks() {
        return subTasks;
    }
    @Override
    public void addSubTask(TaskComponent task) {
        subTasks.add(task);
    }
    @Override
    public void removeSubTask(TaskComponent task) {
        subTasks.remove(task);
    }
    public boolean isCurrentTimeValid(){
        LocalTime currentTime = LocalTime.now();

        if (startTime.isBefore(endTime)) {
            return !currentTime.isBefore(startTime) && !currentTime.isAfter(endTime);
        } else { // Handles the case where the time range crosses midnight
            return !currentTime.isBefore(startTime) || !currentTime.isAfter(endTime);
        }
    }
    @Override
    public void assignTask(Employee employee) { //Görev atayacak (sinem) ve bunu output verir
        if(isCurrentTimeValid()){
            this.setAssigned(true);
            employee.addTaskArray(this);
            System.out.println("Task "+ taskId+" assigned to employee:\n"+employee.getName());
        } else {
            System.out.println("Current time is not in work hours! (08:00-17:00)");
        }
    }
    public boolean isAssigned() {
        return assigned;
    }
    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    public void setStatus(TaskStatus taskStatus){
        status=taskStatus;
    }
    public TaskStatus getStatus(){
        return status;
    }

    public int getTaskPoint() {
        return taskPoint;
    }
    public void setTaskPoint(int taskPoint) {
        this.taskPoint = taskPoint;
    }

    public ArrayList<Employee> getEmpList() {
        return empList;
    }
    public void setEmpList(ArrayList<Employee> empList) {
        this.empList = empList;

        if (this.empList.size()>0){
            assigned = true;
        }
        else {
            assigned = false;
        }
    }

    public void AddToEmpList(Employee employee){
        empList.add(employee);

        if (this.empList.size()>0){
            assigned = true;
        }
        else {
            assigned = false;
        }
    }
    public void removeFromEmpList(Employee employee){
        for(Employee employee_ID : empList){
            if(employee.getId().equals(employee_ID)){
                empList.remove(employee_ID);
            }
        }
    }

    public String getWorkerComment() {
        return workerComment;
    }
    public void setWorkerComment(String workerComment) {
        this.workerComment = workerComment;
    }

    public void showAssignedPeople(){
        for (int i = 0; i< empList.size();i++){
            Employee emp = empList.get(i);
            System.out.println(i + "-" + emp.getId() + " : " + emp.getName());
        }
    }

    // toString method to represent task information as a string
    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", description='" + description + '\'' +
                ", subTasks=" + subTasks +
                ", assigned=" + assigned +
                ", comment= " + getWorkerComment() +
                '}';
    }

    public void addEmp(Employee emp){
        empList.add(emp);
    }

    //When manager want to update description it should use this
    public void setDescription(){
        System.out.println("Please enter new description");
        String newDescription = scanner.nextLine();
        description = newDescription;
    }

    //when manager wanted to update  empList it should
    public void UpdateWorker(){
        System.out.println("What do you want yo do?\n1-Add\n2-Delete");
        int choice = getIndex();
        if (choice == 1){
            EmployeeHolder.showEmployees();
            System.out.println("Please enter the number of employee");
            choice = scanner.nextInt();
            while (choice>EmployeeHolder.getSize() || choice<0){
                System.out.println("You have enter wrong number please try again:");
                choice = scanner.nextInt();
            }
            addEmp(EmployeeHolder.getEmployee(choice));
        }
        else if (choice == 2){
            showAssignedPeople();
            int index = scanner.nextInt();
            while (index>empList.size() || index<0){
                System.out.println("You have enter wrong number please try again:");
                index = scanner.nextInt();
            }
            empList.remove(index);
        }

    }

    //This is a helper method. Just write for simplicity
    private int getIndex(){
        int choice = scanner.nextInt();
        while (choice != 1 || choice != 2){
            System.out.println("You have entered wrong number please try again");
            choice = scanner.nextInt();

            if (choice == 1 || choice == 2){
                break;
            }
        }
        return choice;
    }
}


