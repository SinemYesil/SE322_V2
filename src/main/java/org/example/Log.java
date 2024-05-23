package org.example;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Log {// Log kayıtları için sınıf olmalı bu sınıf action, kimin yaoptığı ve zaman içermeli (sinem)
    private static final String LOG_FILE = "log.txt";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");
    private String action;
    private String performedBy;
    private String timestamp;

    public Log(String action, String performedBy) {
        this.action = action;
        this.performedBy = performedBy;
        setTimestamp();
        enterLog();
    }

    public void enterLog(){
        String timestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        String logEntry = String.format("%s - Action: %s, By: %s", timestamp, action, performedBy);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setTimestamp() {
        DateTimeFormatter myFormat= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime myDateTime= LocalDateTime.now();
        timestamp= myDateTime.format(myFormat);
    }

}