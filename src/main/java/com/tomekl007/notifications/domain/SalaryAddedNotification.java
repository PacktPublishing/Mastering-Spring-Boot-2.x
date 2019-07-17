package com.tomekl007.notifications.domain;

public class SalaryAddedNotification {
    private String name;

    public SalaryAddedNotification() {
    }

    public SalaryAddedNotification(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
