package com.example.ex39_sqlite;

/**
 * Represents an employee with identifying and contact information.
 * @author      Gali Lavi <gl7857@bs.amalnet.k12.il>
 * @version     1.0
 * @since       15/04/2025
 * short description:
 *      This activity handles the removal of employees from the database.
 *      Shows a confirmation dialog, then deletes the selected employee
 *      along with their related orders and meals.
 */

public class Employee {
    private int card_id;
    private String first_name;
    private String last_name;
    private String company;
    private String id_number;
    private String phone;

    /**
     * Constructs an Employee with all relevant fields.
     *
     * @param card_id    the employee's card ID
     * @param first_name the employee's first name
     * @param last_name  the employee's last name
     * @param company    the company the employee works for
     * @param id_number  the employee's ID number
     * @param phone      the employee's phone number
     */
    public Employee(int card_id, String first_name, String last_name, String company, String id_number, String phone) {
        this.card_id = card_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.company = company;
        this.id_number = id_number;
        this.phone = phone;
    }

    /** @return the employee's card ID */
    public int getCard_id() {
        return card_id;
    }

    /** @param card_id sets the employee's card ID */
    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    /** @return the employee's first name */
    public String getFirst_name() {
        return first_name;
    }

    /** @param first_name sets the employee's first name */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /** @return the employee's last name */
    public String getLast_name() {
        return last_name;
    }

    /** @param last_name sets the employee's last name */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /** @return the employee's company name */
    public String getCompany() {
        return company;
    }

    /** @param company sets the company the employee works for */
    public void setCompany(String company) {
        this.company = company;
    }

    /** @return the employee's ID number */
    public String getId_number() {
        return id_number;
    }

    /** @param id_number sets the employee's ID number */
    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    /** @return the employee's phone number */
    public String getPhone() {
        return phone;
    }

    /** @param phone sets the employee's phone number */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
