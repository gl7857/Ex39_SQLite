package com.example.ex39_sqlite;

/**
 * Holds constant field names and table name for the Meals table in the database.
 * Used to reference column names consistently in queries and database operations.
 *
 * @author Gali Lavi
 * @version 1.0
 * @since 15/04/2025
 *
 * short Description:
 *      Holds constant field names and table name for the Meals table in the database.
 *      Used to reference column names consistently in queries and database operations.
 */

public class Meals {
    /** The name of the Meals table. */
    public static final String TABLE_MEALS = "Meals";

    /** Column name for the meal ID. */
    public static final String MEAL_ID = "meal_id";

    /** Column name for the starter dish. */
    public static final String STARTER = "starter";

    /** Column name for the main course dish. */
    public static final String MAIN_COURSE = "main_course";

    /** Column name for the side dish. */
    public static final String SIDE_DISH = "side_dish";

    /** Column name for the dessert dish. */
    public static final String DESSERT = "dessert";

    /** Column name for the drink. */
    public static final String DRINK = "drink";
}
