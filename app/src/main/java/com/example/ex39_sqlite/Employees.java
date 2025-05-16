package com.example.ex39_sqlite;

/**
 * @author      Gali Lavi <gl7857@bs.amalnet.k12.il>
 * @version     1.0
 * @since       15/04/2025
 *
 * short description:
 *      Holds constant field names and table name for the Employees table in the database.
 *      Used for referencing columns consistently in queries and data operations.
 */

public class Employees {
    /** Table name for employees */
    public static final String TABLE_EMPLOYEES = "Employees";

    /** Column name for the employee card ID */
    public static final String CARD_ID = "card_id";

    /** Column name for the employee's first name */
    public static final String FIRST_NAME = "first_name";

    /** Column name for the employee's last name */
    public static final String LAST_NAME = "last_name";

    /** Column name for the company the employee belongs to */
    public static final String COMPANY = "company";

    /** Column name for the employee's ID number */
    public static final String ID_NUMBER = "id_number";

    /** Column name for the employee's phone number */
    public static final String PHONE = "phone";
}
