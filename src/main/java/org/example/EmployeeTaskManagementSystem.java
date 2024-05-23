package org.example;
import javax.xml.transform.Source;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeTaskManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskHolder holder = new TaskHolder();
        EmployeeHolder employeeHolder = new EmployeeHolder();
        Employee manager = new Manager("1", "Manager", "manager@example.com","123", "Manager");
        Employee employee1 = new Worker ("2", "John Doe", "john@example.com", "456", "Software Developer", (Manager) manager);
        Employee employee2 = new Employee("3", "Jane Smith", "jane@example.com","789", "Software Developer");

        Task task1 = new Task("1", "Complete project report", 5);
        Task task2 = new Task("2", "Prepare presentation slides", 10);
        Task task3 = new Task("3", "Review code", 15);
        Task task4 = new Task("4", "Review code", 15);
        Task task5 = new Task("5", "Review code", 15);



        holder.AddToArraylist(task1);
        holder.AddToArraylist(task2);
        holder.AddToArraylist(task3);
        holder.AddToArraylist(task4);
        holder.AddToArraylist(task5);

        EmployeeHolder.AddToArraylist(manager);
        EmployeeHolder.AddToArraylist(employee1);
        EmployeeHolder.AddToArraylist(employee2);



        Command newAssign = new AssignTaskCommand();

        task1.assignTask(employee1);
        task2.assignTask(employee2);
        System.out.println(task1.isAssigned());




        Task subtask1 = new Task("1.1", "Gather project data", 20);
        Task subtask2 = new Task("1.2", "Analyze project requirements", 25);
        task1.addSubTask(subtask1);
        task2.addSubTask(subtask2);

        System.out.println("Welcome to the Employee Task Management System!");
        System.out.println("Are you a manager or an employee? Enter 'manager' or 'worker':");
        String userType = scanner.nextLine().toLowerCase();
        
        System.out.println("Please enter your password:");
        String password =scanner.nextLine();

            if(userType.equals("manager")) {
                if (manager.getPassword().equals(password)) {
                    System.out.println("Welcome, Manager!");
                    boolean managerRunning = true;
                    while (managerRunning) {
                        System.out.println("Manager Options:");
                        System.out.println("1. Create Task");
                        System.out.println("2. Show Assigned Task");
                        System.out.println("3. Show Unassigned Task");
                        System.out.println("4. Create Employee");
                        System.out.println("5. Update Employee");
                        System.out.println("6. Delete Employee");
                        System.out.println("7. Assign Task");
                        System.out.println("8. Update Task");
                        System.out.println("9. Separate Task to Subtasks");
                        System.out.println("10. Complete Task");
                        System.out.println("11. Exit");
                        System.out.print("Enter your choice: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                            case 1:
                                //Create Task
                                System.out.println("Please enter new Task ID:");
                                String id = scanner.nextLine();
                                System.out.println("Please enter a description for new Task:");
                                String description = scanner.nextLine();
                                System.out.println("Please enter a point for new Task:");
                                int point = scanner.nextInt();
                                scanner.nextLine();
                                holder.AddToArraylist(((Manager) manager).CreateTask(id, description, point));
                                //Bu kısımda kullanıcı kısmı belli olduğunda yapan kişi tarafı değişecek
                                new Log("A new task created", employeeHolder.getEmployee(0).getName());
                                break;
                            case 2:
                                System.out.println("Assigned Tasks:");
                                holder.showAssigned();
                                break;
                            case 3:
                                System.out.println("Unassigned Tasks:");
                                holder.showUnAssigned();
                                break;
                            case 4:
                                //Create Employee
                                System.out.println("Please enter new Employee ID:");
                                String ID = scanner.nextLine();
                                System.out.println("Please enter name of new Employee:");
                                String name = scanner.nextLine();
                                System.out.println("Please enter contact details of new Employee:");
                                String contactDetails = scanner.nextLine();
                                System.out.println("Please enter job role of new Employee:");
                                String jobRole = scanner.nextLine();
                                EmployeeHolder.AddToArraylist(((Manager) manager).CreateEmployee(ID, name, password, contactDetails, jobRole));
                                break;
                            case 5:
                                //Update Employee
                                EmployeeHolder.showEmployees();
                                System.out.println("Select an Employee by index number:");
                                int index = scanner.nextInt();
                                scanner.nextLine();
                                ((Manager) manager).updateEmployee(EmployeeHolder.getEmployee(index));
                                System.out.println("New information for updated Employee:");
                                EmployeeHolder.getEmployee(index).toString();
                                break;
                            case 6:
                                //Delete Employee
                                EmployeeHolder.showEmployees();
                                System.out.println("Select an Employee by index number:");
                                index = scanner.nextInt();
                                scanner.nextLine();
                                Employee deletedEmployee = ((Manager) manager).FireEmployee(index);
                                System.out.println("Information of deleted Employee:");
                                deletedEmployee.toString();
                                break;
                            case 7:
                                //Assign Task
                                EmployeeHolder.showEmployees();
                                System.out.println("Please select which employee take the task(Please enter employee index!)");
                                index = scanner.nextInt();
                                scanner.nextLine();
                                Employee selectedEmployee = EmployeeHolder.getEmployee(index);
                                holder.showUnAssigned();
                                System.out.println("Please select which task do you want to assign.(Please enter task index!)");
                                index = scanner.nextInt();
                                scanner.nextLine();
                                Task selectedTask = holder.getUnAssignedList().get(index);
                                newAssign.execute(selectedEmployee, selectedTask);

                                break;
                            case 8:
                                //Update Task
                                ((Manager) manager).updateTask(holder);
                                break;
                            case 9:
                                //Separate Task to Subtasks
                                boolean flag = true;
                                holder.showUnAssigned();
                                System.out.println("Please select which task do you want to separate.(Please enter task index)");
                                index = scanner.nextInt();
                                scanner.nextLine();
                                while (flag) {
                                    System.out.println("Please enter new Task ID:");
                                    id = scanner.nextLine();
                                    System.out.println("Please enter a description for new Task:");
                                    description = scanner.nextLine();
                                    System.out.println("Please enter a point for new Task:");
                                    point = scanner.nextInt();
                                    scanner.nextLine();
                                    Task newSubTask = ((Manager) manager).CreateTask(id, description, point);
                                    holder.getUnAssignedList().get(index).addSubTask(newSubTask);

                                    System.out.println("Want to create another subtask? Press 0 to stop creating subtasks!");
                                    int subTaskContinue = scanner.nextInt();
                                    scanner.nextLine();
                                    if (subTaskContinue == 0) {
                                        flag = false;
                                    }
                                }

                                break;
                            case 10:
                                //Complete Task
                                ((Manager) manager).RemoveFromWaitingForApproveArraylist();
                                break;
                            case 11:
                                System.out.println("Exiting...");
                                managerRunning = false;
                                break;
                            default:
                                System.out.println("Invalid choice. Please enter a number between 1 and 10.");
                                break;
                        }
                    }

                }
                else{
                    System.out.println("Wrong password!!!");
                }
            }
            else if(userType.equals("worker")) {
                if (employee1.getPassword().equals(password) || employee2.getPassword().equals(password)) {
                    System.out.println("Welcome, Worker!");
                    boolean employeeRunning = true;
                    while (employeeRunning) {
                        System.out.println("Worker Options:");
                        System.out.println("1. Show Unassigned Tasks");
                        System.out.println("2. Show Assigned Tasks");
                        System.out.println("3. Write task comment"); // Bu yapılıcak. Şuan yok.
                        System.out.println("4. Complete Task");
                        System.out.println("5. Exit");
                        System.out.print("Enter your choice: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                            case 1:
                                System.out.println("Unassigned Tasks:");
                                holder.showUnAssigned();
                                break;
                            case 2:
                                System.out.println("Assigned Tasks:");
                                holder.showAssigned();
                                break;
                            case 3:
                                //Write task comment
                                EmployeeHolder.showEmployees();
                                System.out.println("Which employee want to write comment.");
                                int emp = scanner.nextInt();
                                scanner.nextLine();
                                Employee selectedEmp = EmployeeHolder.getEmployee(emp);
                                selectedEmp.ViewAssignedTasks();
                                System.out.println("Please select which task do you want to add comment:");
                                int tsk = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Write your comment to the task:");
                                String selectedComment = scanner.nextLine();
                                try {
                                    selectedEmp.getTaskArray().get(tsk).setWorkerComment(selectedComment);
                                } catch (Exception e) {
                                    System.out.println("Something went wrong!");
                                }
                                break;
                            case 4:

                                break;
                            case 5:
                                System.out.println("Exiting...");
                                employeeRunning = false;
                                break;
                            default:
                                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                                break;
                        }
                    }
                }
                else{
                    System.out.println("Wrong password!!!");
                }
            }
            else {
                System.out.println("Invalid user type. Please enter 'manager' or 'worker'.");

            }


        scanner.close();
}
    public boolean isPasswordValid(String password) {
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialCharacter = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }
            else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            }
            else if (Character.isDigit(c)) {
                hasDigit = true;
            }
            else {
                // Check for special characters using ASCII values
                if ((c >= 33 && c <= 47) || (c >= 58 && c <= 64) || (c >= 91 && c <= 96) || (c >= 123 && c <= 126)) {
                    hasSpecialCharacter = true;
                }
            }
        }

        // hepsi okeyse true doner
        return hasUppercase && hasLowercase && hasDigit && hasSpecialCharacter;
    }

}
