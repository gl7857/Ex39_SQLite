package com.example.ex39_sqlite;

public class Meal {
    private int meal_id;
    private String starter;
    private String main_course;
    private String side_dish;
    private String dessert;
    private String drink;

    public Meal(int meal_id, String starter, String main_course, String side_dish, String dessert, String drink) {
        this.meal_id = meal_id;
        this.starter = starter;
        this.main_course = main_course;
        this.side_dish = side_dish;
        this.dessert = dessert;
        this.drink = drink;
    }

    public int getMeal_id() {
        return meal_id;
    }

    public void setMeal_id(int meal_id) {
        this.meal_id = meal_id;
    }

    public String getStarter() {
        return starter;
    }

    public void setStarter(String starter) {
        this.starter = starter;
    }

    public String getMain_course() {
        return main_course;
    }

    public void setMain_course(String main_course) {
        this.main_course = main_course;
    }

    public String getSide_dish() {
        return side_dish;
    }

    public void setSide_dish(String side_dish) {
        this.side_dish = side_dish;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }
}
