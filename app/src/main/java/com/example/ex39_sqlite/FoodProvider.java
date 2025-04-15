package com.example.ex39_sqlite;

public class FoodProvider {
    private int provider_id;
    private String company_name;
    private String main_phone;
    private String secondary_phone;

    public FoodProvider(int provider_id, String company_name, String main_phone, String secondary_phone) {
        this.provider_id = provider_id;
        this.company_name = company_name;
        this.main_phone = main_phone;
        this.secondary_phone = secondary_phone;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getMain_phone() {
        return main_phone;
    }

    public void setMain_phone(String main_phone) {
        this.main_phone = main_phone;
    }

    public String getSecondary_phone() {
        return secondary_phone;
    }

    public void setSecondary_phone(String secondary_phone) {
        this.secondary_phone = secondary_phone;
    }
}
