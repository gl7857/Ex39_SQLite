package com.example.ex39_sqlite;

public class Order {
    private int order_id;
    private String date;
    private String time;
    private int employee_id;
    private int meal_id;
    private int provider_id;

    public Order(int order_id, String date, String time, int employee_id, int meal_id, int provider_id) {
        this.order_id = order_id;
        this.date = date;
        this.time = time;
        this.employee_id = employee_id;
        this.meal_id = meal_id;
        this.provider_id = provider_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getMeal_id() {
        return meal_id;
    }

    public void setMeal_id(int meal_id) {
        this.meal_id = meal_id;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }
}