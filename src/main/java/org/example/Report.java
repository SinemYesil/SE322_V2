package org.example;

import java.time.LocalDate;
import java.util.*;

public class Report {
    Scanner reportScanner = new Scanner(System.in);
    Employee selectedEmployee;
    Calendar calendar = Calendar.getInstance();
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            //this will be filled but we need to
        }
    };

    public Report(){
        selectedEmployee = selectEmployee();
        setReportTimer();
        addToCalender();
    }

    public void setReportTimer(){
        boolean bool = false;
        String tempString="";

        while (!bool){
            System.out.println("Please enter time period for reports. Write \"Day\" for daily and \"Week\" for weekly reports"); // burayi duzelt daha guzel bir string yaz
            tempString = reportScanner.nextLine();
            if(tempString.toLowerCase().replace(" ","").equals("day")||tempString.toLowerCase().replace(" ","").equals("week")){
                bool=true;
            }
        }

        if(tempString.toLowerCase().replace(" ","").equals("day")){
            calendar.add(Calendar.DATE,1);
        }
        else if(tempString.toLowerCase().replace(" ","").equals("week")){
            calendar.add(Calendar.DATE,7);
        }
    }

    public void addToCalender(){
        timer.schedule(task,calendar.getTime());
    }

    public Employee selectEmployee(){
        EmployeeHolder.showEmployees();
        System.out.println("Enter the index of the employee");
        int tempInt = reportScanner.nextInt();
        selectedEmployee=EmployeeHolder.getEmployee(tempInt);
        return selectedEmployee;
    }
}
