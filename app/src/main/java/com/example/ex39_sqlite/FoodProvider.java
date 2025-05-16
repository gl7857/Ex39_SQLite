package com.example.ex39_sqlite;

/**
 * @author      Gali Lavi <gl7857@bs.amalnet.k12.il>
 * @version     1.0
 * @since       15/04/2025
 *
 * short description:
 *      Represents a food provider entity with ID, company name, and contact phones.
 *      Encapsulates provider details for database and app usage.
 */
public class FoodProvider {
    private int provider_id;
    private String company_name;
    private String main_phone;
    private String secondary_phone;

    /**
     * Constructs a FoodProvider with all details.
     * @param provider_id Unique ID of the provider.
     * @param company_name Name of the provider's company.
     * @param main_phone Primary contact phone number.
     * @param secondary_phone Secondary contact phone number.
     */
    public FoodProvider(int provider_id, String company_name, String main_phone, String secondary_phone) {
        this.provider_id = provider_id;
        this.company_name = company_name;
        this.main_phone = main_phone;
        this.secondary_phone = secondary_phone;
    }

    /**
     * Returns the provider's unique ID.
     * @return provider_id as int.
     */
    public int getProvider_id() {
        return provider_id;
    }

    /**
     * Sets the provider's unique ID.
     * @param provider_id the new ID to set.
     */
    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    /**
     * Returns the provider's company name.
     * @return company_name as String.
     */
    public String getCompany_name() {
        return company_name;
    }

    /**
     * Sets the provider's company name.
     * @param company_name the new company name to set.
     */
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    /**
     * Returns the main phone number of the provider.
     * @return main_phone as String.
     */
    public String getMain_phone() {
        return main_phone;
    }

    /**
     * Sets the main phone number of the provider.
     * @param main_phone the new main phone number to set.
     */
    public void setMain_phone(String main_phone) {
        this.main_phone = main_phone;
    }

    /**
     * Returns the secondary phone number of the provider.
     * @return secondary_phone as String.
     */
    public String getSecondary_phone() {
        return secondary_phone;
    }

    /**
     * Sets the secondary phone number of the provider.
     * @param secondary_phone the new secondary phone number to set.
     */
    public void setSecondary_phone(String secondary_phone) {
        this.secondary_phone = secondary_phone;
    }
}
