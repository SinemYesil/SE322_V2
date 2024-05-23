package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TaskHolderTest {

    @Test
    void showUnAssignedPositive() {  // PASSED
        //Positive test case, viewing existing unassigned tasks are tested
        TaskHolder taskHolder = new TaskHolder();
        Task unassignedTask = new Task("1", "Complete project report", 5);
        unassignedTask.setAssigned(false);
        taskHolder.AddToArraylist(unassignedTask);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        taskHolder.showUnAssigned();
        String expectedOutput = unassignedTask.toString() + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void showUnAssignedNegative() { // NEEDS TO BE FIXED
        //Negative test case, viewing existing assigned tasks are tested
        TaskHolder taskHolder = new TaskHolder();
        Task assignedTask = new Task("1", "Complete project report", 5);
        assignedTask.setAssigned(true);
        taskHolder.AddToArraylist(assignedTask);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        taskHolder.showUnAssigned();
        String expectedOutput = "";
        assertNotEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void addToArraylistPositive() { // PASSED
        //Positive test case, adding existing tasks to list is tested
        TaskHolder taskHolder = new TaskHolder();
        Task task = new Task("1", "Complete project report", 5);

        taskHolder.AddToArraylist(task);
        assertEquals(1, taskHolder.arrayList.size());
        assertEquals(task, taskHolder.arrayList.get(0));
    }

    @Test
    void addToArraylistNegative() {
        // Negative test case, adding null task to the list
        TaskHolder taskHolder = new TaskHolder();
        Task task = null;
        taskHolder.AddToArraylist(task);
        assertNotEquals(1, taskHolder.arrayList.size());
    }
}