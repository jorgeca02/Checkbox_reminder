package com.cdp.checkboxreminder;

public class Task {
    private String name;
    private String description;
    private int day;
    private int month;
    private int year;
    private int id;
    static int maxId;
    private boolean check;

    public Task(String name, String description, int day, int month, int year) {
        this.name = name;
        this.description = description;
        this.day = day;
        this.month = month;
        this.year = year;
        if(Task.maxId!=0&&Task.maxId<1)
            Task.maxId=0;
        id=maxId;
        Task.maxId++;
        //check=false;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public static int getMaxId() {
        return maxId;
    }
    public static void setMaxId(int maxId) {
        Task.maxId = maxId;
    }
    public boolean isCheck() {
        return check;
    }
    public void setCheck(boolean check) {
       this.check = check;
    }

}
