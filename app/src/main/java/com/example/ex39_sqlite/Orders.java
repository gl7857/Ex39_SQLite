package com.example.ex39_sqlite;

/**
 * @author Gali Lavi
 * @version 1.0
 * @since 15/04/2025
 *
 * short Description:
 *      Holds constant field and table names for the Orders table in the database.
 *      Used for consistent column references in queries and database operations.
 */

public class Orders {
    /** Table name for orders */
    public static final String TABLE_ORDERS = "Orders";

    /** Column name for order ID */
    public static final String ORDER_ID = "order_id";

    /** Column name for order date */
    public static final String DATE = "date";

    /** Column name for order time */
    public static final String TIME = "time";

    /** Column name for employee ID associated with order */
    public static final String EMPLOYEE_ID = "employee_id";

    /** Column name for meal ID associated with order */
    public static final String MEAL_ID = "meal_id";

    /** Column name for food provider ID associated with order */
    public static final String PROVIDER_ID = "provider_id";
}
