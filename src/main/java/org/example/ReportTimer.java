package org.example;

public class ReportTimer {
    private static ReportTimer timer = new ReportTimer();

    private ReportTimer(){};

    public static ReportTimer getTimer(){
        return timer;
    }


}
