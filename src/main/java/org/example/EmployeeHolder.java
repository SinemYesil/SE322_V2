package org.example;

import java.util.ArrayList;

public class EmployeeHolder {
    private static ArrayList<Employee> arrayList = new ArrayList<>();

    public static void AddToArraylist(Employee emp){
        arrayList.add(emp);
    }

    public static void RemoveFromArrayList(Employee emp){
        arrayList.remove(emp);
    }



    public static void showEmployees(){
        for (int i = 0; i< arrayList.size();i++){
            Employee tempEmp = arrayList.get(i);
            System.out.println(i + " - ID=" + tempEmp.getId() + " : " + tempEmp.getName());
        }
    }

    public static Employee getEmployee(int index){
        if (index >= 0 && index < arrayList.size()) {
            return arrayList.get(index);
        } else {
            return null; // Return null if index is out of bounds
        }
    }

    public static int getSize(){
        return arrayList.size();
    }
}
