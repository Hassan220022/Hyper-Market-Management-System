package Order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(String date) {
        String[] dateArray = date.split("/");
        this.day = Integer.parseInt(dateArray[0]);
        this.month = Integer.parseInt(dateArray[1]);
        this.year = Integer.parseInt(dateArray[2]);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString() {
        return getDay() + "/" + getMonth() + "/" + getYear();
    }

    public static Date getDate(String date) {
        return new Date(date);
    }

    public static boolean isBefore(Date date) {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter current = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String currentDate = myDateObj.format(current);
        Date CurrentDate = new Date(currentDate);
        if (date.getYear() >= CurrentDate.getYear()) {
            return true;
        } else if (date.getMonth() >= CurrentDate.getMonth()) {
            return true;
        } else if (date.getDay() >= CurrentDate.getDay()) {///// this would be the problem
            return true;
        }
        return false;
    }
}
