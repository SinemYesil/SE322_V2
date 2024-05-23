package org.example;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Manager extends Employee{
    HashMap<Worker,Task> WaitingForApprove = new HashMap<>();
    Scanner scanner = new Scanner(System.in);

    public Manager(String id, String name, String contactDetails, String password, String jobRole){
        super(id, name, contactDetails, password, jobRole);
    }

    // bunun çalışması için bizim employeeleri tuttuğumuz bir genel arrayden, input olarak verilen employee objesini silmemiz lazım
    public Employee FireEmployee(int index){
        Employee deletedEmployee = EmployeeHolder.getEmployee(index);
        EmployeeHolder.RemoveFromArrayList(EmployeeHolder.getEmployee(index));
        return deletedEmployee;
    }

    //bunun icinde yaratınca genel bir employee arrayi varsa içine atabilir ya da return ettigi yerde eklenebilir
    public Employee CreateEmployee(String EmployeeID, String name,String password ,String ContactDetail, String jobRole){
        return new Employee(EmployeeID, name, password,ContactDetail, jobRole);
    }
    public Task CreateTask(String ID, String desc, int point){
        return new Task(ID, desc, point);
    }

    //just for the change the employee attributes
    public  void updateEmployee(Employee employee){
        int temp_input =0;
        System.out.println();
        System.out.println("To change employee ID press 1 \nTo change employee name press 2 \nTo change employee contact detail press 3 \nTo change employee job role press 4 \n");
        temp_input = scanner.nextInt();
        if (temp_input==1){
            int temp_input_2=0;
            System.out.println("Enter new ID");
            temp_input_2=scanner.nextInt();
            employee.setId(Integer.toString(temp_input_2));
        }
        else if(temp_input==2){
            String temp_input_string_2="";
            System.out.println("Enter new name");
            temp_input_string_2= scanner.nextLine();
            employee.setName(temp_input_string_2);
        }
        else if(temp_input==3){
            String temp_input_string_3="";
            System.out.println("Enter new contact detail");
            temp_input_string_3= scanner.nextLine();
            employee.setContactDetails(temp_input_string_3);
        }
        else if(temp_input==4){
            String temp_input_string_3="";
            System.out.println("Enter new job role");
            temp_input_string_3= scanner.nextLine();
            employee.setJobRole(temp_input_string_3);
        }
    }

    public void AddToWaitingForApprove(Worker worker,Task task){
        WaitingForApprove.put(worker, task);
    }

    public void RemoveFromWaitingForApproveArraylist(){
        //For each task we should show description of task and comment of worker and manager should decide if task is finished or not.
        for(Map.Entry<Worker,Task> set: WaitingForApprove.entrySet()){
            System.out.print("Task Description: ");
            System.out.println(set.getValue().getDescription());
            System.out.print("Worker Comment: ");
            System.out.println(set.getValue().getWorkerComment());
            System.out.println("Is Finished (Write \"Yes\" or \"No\")");
            String string = scanner.nextLine();


            if (string.toLowerCase().replace(" ","").equals("yes")){
                set.getValue().setStatus(Task.TaskStatus.DONE);
                WaitingForApprove.remove(set.getKey(),set.getValue());// Burada task'ın done olduğunu aşağıda belirtmemiz lazım. Sonradan bitmiş işleri görmek istersek orada kullanılır.

            }
            else if (string.toLowerCase().replace(" ","").equals("no")){
                GiveTask(set.getKey(),set.getValue());
            }
            else{
                System.out.println("Wrong input given and task skipped");
            }
        }
    }

    public void GiveTask(Employee employee, Task task){
        if (task != null) {
            this.addTaskArray(task);
        } else {
            System.out.println("Cannot add a null task to completed tasks.");
        }
    }

    public void updateTask(TaskHolder holder) {
        ArrayList<Task> tempList = holder.getAssignedList();

        int realChoice;
        while (true) {
            TaskHolder.showTasks(tempList);
            System.out.println("Which Task Do You Want to Change? Give Number Please");
            int choice = getIndex(tempList);
            boolean isOk = isOk(choice);
            while (!isOk){
                choice = getIndex(tempList);
                isOk = isOk(choice);
            }

            boolean updateChoice = chooseUpdate();

            if (updateChoice){
                tempList.get(choice).setDescription();
            }
            else{
                tempList.get(choice).UpdateWorker();
            }
        }
    }//endFunction


    public int getIndex(ArrayList<Task> tasks){
        ArrayList<Task> tempList = tasks;
        int choice = scanner.nextInt();
        do {
            System.out.println("Your number is invalid please try again");
            choice = scanner.nextInt();
        }
        while (choice> tempList.size() || choice<0);
        return choice;
    }

    public boolean isOk(int choice){
        System.out.println("You enter "+ choice + "Is this what you want? \"Y\" or \"N\"");
        String choice2 = scanner.nextLine();
        choice2.toUpperCase();
        while (!choice2.equals("N") || !choice2.equals("Y") ){
            System.out.println("Your number is invalid please try again");
            choice = scanner.nextInt();
            if (choice2.equals("N")){
                break;
            }else if(choice2.equals("Y")){
                break;
            }
        }
        return choice2.equals("Y")? true : false;
    }

    // bu method Sinem'in attığı java dosyası ile gelmişti ondan silmedim. Sizle konuşup silmek istedim
    // Method to separate a task to subtasks
    //Employee den buraya taşındı çünkü bunu sadece manager yapabiliyor.
    public void separateTaskToSubtasks(Task task, ArrayList<Task> subTasks) {
        task.getSubTasks().addAll(subTasks);
    }

    public boolean chooseUpdate(){
        System.out.println("What do you want to change? For Description enter \"D\". For Employee please \"E\"");
        String choice2 = scanner.nextLine();

        while (!choice2.equals("D") || !choice2.equals("E")){
            System.out.println("You have entered invalid Input try again");
            choice2 = scanner.nextLine();
            if (choice2.equals("D")){
                break;
            }else if(choice2.equals("E")){
                break;
            }
        }

        return choice2.equals("D") ? true : false;
    }
}
