package com.example.ex39_sqlite;

/**
 * @author Gali Lavi
 * @version 1.0
 * @since 15/04/2025
 *
 * short Description:
 *      Represents a meal with various courses and a drink.
 *      Contains information about starter, main course, side dish, dessert, and drink.
 *      Used to store and retrieve meal details.
 */

public class Meal {
    private int meal_id;
    private String starter;
    private String main_course;
    private String side_dish;
    private String dessert;
    private String drink;

    /**
     * Constructs a new Meal object with all meal components.
     *
     * @param meal_id    Unique identifier for the meal
     * @param starter    The starter dish
     * @param main_course The main course dish
     * @param side_dish  The side dish
     * @param dessert   The dessert dish
     * @param drink     The drink
     */
    public Meal(int meal_id, String starter, String main_course, String side_dish, String dessert, String drink) {
        this.meal_id = meal_id;
        this.starter = starter;
        this.main_course = main_course;
        this.side_dish = side_dish;
        this.dessert = dessert;
        this.drink = drink;
    }

    /**
     * Gets the meal's unique identifier.
     *
     * @return the meal_id
     */
    public int getMeal_id() {
        return meal_id;
    }

    /**
     * Sets the meal's unique identifier.
     *
     * @param meal_id the meal_id to set
     */
    public void setMeal_id(int meal_id) {
        this.meal_id = meal_id;
    }

    /**
     * Gets the starter dish.
     *
     * @return the starter
     */
    public String getStarter() {
        return starter;
    }

    /**
     * Sets the starter dish.
     *
     * @param starter the starter to set
     */
    public void setStarter(String starter) {
        this.starter = starter;
    }

    /**
     * Gets the main course dish.
     *
     * @return the main_course
     */
    public String getMain_course() {
        return main_course;
    }

    /**
     * Sets the main course dish.
     *
     * @param main_course the main_course to set
     */
    public void setMain_course(String main_course) {
        this.main_course = main_course;
    }

    /**
     * Gets the side dish.
     *
     * @return the side_dish
     */
    public String getSide_dish() {
        return side_dish;
    }

    /**
     * Sets the side dish.
     *
     * @param side_dish the side_dish to set
     */
    public void setSide_dish(String side_dish) {
        this.side_dish = side_dish;
    }

    /**
     * Gets the dessert dish.
     *
     * @return the dessert
     */
    public String getDessert() {
        return dessert;
    }

    /**
     * Sets the dessert dish.
     *
     * @param dessert the dessert to set
     */
    public void setDessert(String dessert) {
        this.dessert = dessert;
    }

    /**
     * Gets the drink.
     *
     * @return the drink
     */
    public String getDrink() {
        return drink;
    }

    /**
     * Sets the drink.
     *
     * @param drink the drink to set
     */
    public void setDrink(String drink) {
        this.drink = drink;
    }
}
