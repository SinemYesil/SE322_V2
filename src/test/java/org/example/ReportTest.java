package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    @Test
    void setReportTimerPositive() {  // NEEDS TO BE FIXED
        Report report = new Report();
        report.setReportTimer();

        Calendar expectedCalendar = Calendar.getInstance();
        expectedCalendar.add(Calendar.DATE, 1);
        assertEquals(expectedCalendar.getTime(), report.calendar.getTime());
    }

    @Test
    void setReportTimerNegative() { // NEEDS TO BE FIXED
        Report report = new Report();
        report.setReportTimer();

        Calendar expectedCalendar = Calendar.getInstance();
        assertNotEquals(expectedCalendar.getTime(), report.calendar.getTime());
    }

}