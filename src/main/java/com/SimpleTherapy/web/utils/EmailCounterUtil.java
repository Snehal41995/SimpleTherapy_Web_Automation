package com.simpleTherapy.web.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class EmailCounterUtil {

    public static int getNextCounter() {

        Properties prop = new Properties();
        int counter = 1;

        try {
            String projectPath = System.getProperty("user.dir");

            String filePath =
                    projectPath
                            + "\\src\\main\\java\\com\\simpleTherapy\\web\\config\\config.properties";

            FileInputStream in = new FileInputStream(filePath);
            prop.load(in);
            in.close();

            String today =
                    LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

            String savedDate = prop.getProperty("email.date", "");
            String savedCounter = prop.getProperty("email.counter", "0");

            if (today.equals(savedDate)) {
                counter = Integer.parseInt(savedCounter) + 1;
            }

            prop.setProperty("email.date", today);
            prop.setProperty("email.counter", String.valueOf(counter));

            FileOutputStream out = new FileOutputStream(filePath);
            prop.store(out, null);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return counter;
    }
}
