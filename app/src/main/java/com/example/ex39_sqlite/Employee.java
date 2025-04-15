package com.example.ex39_sqlite;

public class Employee {
    private int card_id;
    private String first_name;
    private String last_name;
    private String company;
    private String id_number;
    private String phone;

    public Employee(int card_id, String first_name, String last_name, String company, String id_number, String phone) {
        this.card_id = card_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.company = company;
        this.id_number = id_number;
        this.phone = phone;
    }

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
