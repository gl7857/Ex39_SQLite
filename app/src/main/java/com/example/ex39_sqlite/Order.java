package com.example.ex39_sqlite;

/**
 * @author Gali Lavi
 * @version 1.0
 * @since 15/04/2025
 *
 * short Description:
 *      Represents an Order entity with its details.
 *      Contains order ID, date, time, and references to employee, meal, and provider.
 */

public class Order {
    private int order_id;
    private String date;
    private String time;
    private int employee_id;
    private int meal_id;
    private int provider_id;

    /**
     * Constructs an Order object with all required fields.
     *
     * @param order_id The unique ID of the order.
     * @param date The date of the order.
     * @param time The time of the order.
     * @param employee_id The ID of the employee who placed the order.
     * @param meal_id The ID of the meal ordered.
     * @param provider_id The ID of the food provider.
     */
    public Order(int order_id, String date, String time, int employee_id, int meal_id, int provider_id) {
        this.order_id = order_id;
        this.date = date;
        this.time = time;
        this.employee_id = employee_id;
        this.meal_id = meal_id;
        this.provider_id = provider_id;
    }

    /**
     * Gets the order ID.
     * @return The order ID.
     */
    public int getOrder_id() {
        return order_id;
    }

    /**
     * Sets the order ID.
     * @param order_id The new order ID.
     */
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    /**
     * Gets the order date.
     * @return The date as a String.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the order date.
     * @param date The new date.
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the order time.
     * @return The time as a String.
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the order time.
     * @param time The new time.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets the employee ID associated with the order.
     * @return The employee ID.
     */
    public int getEmployee_id() {
        return employee_id;
    }

    /**
     * Sets the employee ID for the order.
     * @param employee_id The new employee ID.
     */
    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    /**
     * Gets the meal ID associated with the order.
     * @return The meal ID.
     */
    public int getMeal_id() {
        return meal_id;
    }

    /**
     * Sets the meal ID for the order.
     * @param meal_id The new meal ID.
     */
    public void setMeal_id(int meal_id) {
        this.meal_id = meal_id;
    }

    /**
     * Gets the food provider ID associated with the order.
     * @return The provider ID.
     */
    public int getProvider_id() {
        return provider_id;
    }

    /**
     * Sets the food provider ID for the order.
     * @param provider_id The new provider ID.
     */
    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }
}
