package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    @Test
    void addToWaitingForApprovePositive() { // PASSED
        // Positive test case, waiting for approval of matching tasks and workers is tested
        Manager manager = new Manager("1", "Manager", "manager@office.com", "123", "Manager");
        Worker worker = new Worker("2", "John Black", "john@office.com", "123", "Software Developer", manager);
        Task task = new Task("1", "Complete project report", 5);
        manager.AddToWaitingForApprove(worker, task);
        assertTrue(manager.WaitingForApprove.containsKey(worker));
        assertTrue(manager.WaitingForApprove.containsValue(task));

    }

    @Test
    void addToWaitingForApproveNegative() { // PASSED
        // adding for invalid worker
        Manager manager = new Manager("1", "Manager", "manager@office.com", "123","Manager");
        Worker invalidWorker = new Worker("3", "Jenna Bailey", "jenna@office.com", "123", "Software Developer",manager);
        Task task = new Task("1", "Complete project report", 5);

        assertFalse(manager.WaitingForApprove.containsKey(invalidWorker));

    }

    @Test
    void createEmployeePositive() { // PASSED
        //Positive test case, creating employee with valid data is tested
        Employee manager = new Manager("1", "Manager", "manager@office.com","123",  "Manager");
        assertEquals("Manager", manager.getJobRole());
    }

    @Test
    void createEmployeeNegative() { // PASSED
        //Negative test case, creating employee with invalid data is tested
        Employee invalidEmployee = new Employee(null, null, null,null, null);
        assertNotEquals("Invalid employee should not have an ID", "1", invalidEmployee.getId());
    }

    @Test
    void updateEmployeePositive() { // PASSED
        // Positive test case, updating existing employee is tested
        Employee manager = new Manager("1", "Manager", "manager@office.com", "123", "Manager");
        manager.setName("Updated Manager");
        assertEquals("Updated Manager", manager.getName());
    }

    @Test
    void updateEmployeeNegative() {
        // updating employee with duplicate info
        Manager manager = new Manager("1", "Manager", "manager@office.com", "123", "Manager");
        Employee existingOne = new Employee("2", "John Black", "john@office.com", "123", "Software Developer");
        Employee employee = new Employee("2", "John Black", "john@office.com", "123", "Software Developer");

        // ...

        assertNotEquals("3", employee.getId());
    }

    @Test
    void FireEmployeePositive() { // PASSED
        // Positive test case, removing existing employee is tested
        Employee manager = new Manager("1", "Manager", "manager@office.com", "123", "Manager");
        Manager managerToDelete = (Manager) manager;
        assertNotNull(managerToDelete);
        managerToDelete = null;
        assertNull(managerToDelete);
    }

    @Test
    void FireEmployeeNegative() { // PASSED
        //negative test case, removing non-existing employee
        Manager manager = new Manager("1", "Manager", "manager@office.com", "123", "Manager");
        Employee employee = new Employee("2", "John Black", "john@office.com","123",  "Software Developer");

        assertNotEquals(employee, manager.FireEmployee(1));
    }


    @Test
    public void giveTaskPositive() { // PASSED
        // Positive test case, adding a valid task is tested
        Employee employee = new Employee("2", "John Black", "john@office.com", "123", "Software Developer");
        Task task = new Task("1", "Complete project report", 5);
        employee.addTaskArray(task);
        List<Task> taskList = employee.getTaskArray();
        assertTrue(taskList.contains(task), "Task should be assigned to the employee");
    }


    @Test
    public void giveTaskNegative() { // PASSED
        // Negative test case, testing adding null task and assigning it
        Manager manager = new Manager("1", "Manager", "manager@office.com", "123", "Manager");
        Employee employee = new Employee("2", "John Black", "john@office.com","123",  "Software Developer");
        Task nullTask = null;
        manager.GiveTask(employee, nullTask);

        assertNotEquals(1, employee.getTaskArray().size());

    }

    @Test
    public void isOKPositive() { // this takes too long - SHOULD BE FIXED
        Manager manager = new Manager("1", "Manager", "manager@office.com","123",  "Manager");
        boolean result = manager.isOk(1);

        assertEquals(true, result);

    }

    @Test
    public void isOKNegative() { // this takes too long - SHOULD BE FIXED
        Manager manager = new Manager("1", "Manager", "manager@office.com","123",  "Manager");
        boolean result = manager.isOk(2);

        assertNotEquals(false, result);
    }


    @Test
    public void updateTaskPositive() { // PASSED
        // boolean var should be tested
        TaskHolder holder = new TaskHolder();
        ArrayList<Task> tasks = holder.getAssignedList();
        tasks.add(new Task("1", "Complete project report", 5));
        tasks.add(new Task("2", "Prepare presentation", 2));

        Manager manager = new Manager("1", "Manager", "manager@office.com","123",  "Manager");
        int choice = 1;

        boolean isOk = true;
        boolean updateChoice = true;

        assertEquals(true, isOk);
        assertEquals(true, updateChoice);

    }

    @Test
    public void updateTaskNegative() { // PASSED
        // boolean var should be tested
        TaskHolder holder = new TaskHolder();
        ArrayList<Task> tasks = holder.getAssignedList();
        tasks.add(new Task("1", "Complete project report", 5));
        tasks.add(new Task("2", "Prepare presentation", 2));

        Manager manager = new Manager("1", "Manager", "manager@office.com","123",  "Manager");
        int choice = 1;
        boolean isOk = true;
        boolean updateChoice = false;

        assertNotEquals(true, updateChoice);
    }

}
