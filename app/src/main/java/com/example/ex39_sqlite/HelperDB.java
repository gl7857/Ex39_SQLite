package com.example.ex39_sqlite;

import static com.example.ex39_sqlite.Employees.TABLE_EMPLOYEES;
import static com.example.ex39_sqlite.FoodProviders.TABLE_FOOD_PROVIDERS;
import static com.example.ex39_sqlite.Meals.TABLE_MEALS;
import static com.example.ex39_sqlite.Orders.TABLE_ORDERS;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author      Gali Lavi <gl7857@bs.amalnet.k12.il>
 * @version     1.0
 * @since       15/04/2025
 *
 * short description:
 *      Manages database creation and version management for the app.
 *      Creates and upgrades tables for employees, meals, food providers, and orders.
 */

/**
 * The type HelperDB
 */
public class HelperDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbexam.db";
    private static final int DATABASE_VERSION = 1;
    private String strCreate, strDelete;

    /**
     * Instantiates a new HelperDB
     *
     * @param context the context
     */
    public HelperDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * onCreate
     * <p>
     * This method create the tables in database
     * @param db the SQLite database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        strCreate="CREATE TABLE "+Employees.TABLE_EMPLOYEES;
        strCreate+=" ("+Employees.CARD_ID+" TEXT,";
        strCreate+=" "+Employees.FIRST_NAME+" TEXT,";
        strCreate+=" "+Employees.LAST_NAME+" TEXT,";
        strCreate+=" "+Employees.COMPANY+" TEXT,";
        strCreate+=" "+Employees.ID_NUMBER+" TEXT,";
        strCreate+=" "+Employees.PHONE+" TEXT";
        strCreate+=");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+Meals.TABLE_MEALS;
        strCreate+=" ("+Meals.MEAL_ID+" TEXT,";
        strCreate+=" "+Meals.STARTER+" TEXT,";
        strCreate+=" "+Meals.MAIN_COURSE+" TEXT,";
        strCreate+=" "+Meals.SIDE_DISH+" TEXT,";
        strCreate+=" "+Meals.DESSERT+" TEXT,";
        strCreate+=" "+Meals.DRINK+" TEXT";
        strCreate+=");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+FoodProviders.TABLE_FOOD_PROVIDERS;
        strCreate+=" ("+FoodProviders.PROVIDER_ID+" TEXT,";
        strCreate+=" "+FoodProviders.COMPANY_NAME+" TEXT,";
        strCreate+=" "+FoodProviders.MAIN_PHONE+" TEXT,";
        strCreate+=" "+FoodProviders.SECONDARY_PHONE+" TEXT";
        strCreate+=");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+Orders.TABLE_ORDERS;
        strCreate+=" ("+Orders.ORDER_ID+" TEXT,";
        strCreate+=" "+Orders.DATE+" TEXT,";
        strCreate+=" "+Orders.TIME+" TEXT,";
        strCreate+=" "+Orders.EMPLOYEE_ID+" TEXT,";
        strCreate+=" "+Orders.MEAL_ID+" TEXT,";
        strCreate+=" "+Orders.PROVIDER_ID+" TEXT";
        strCreate+=");";
        db.execSQL(strCreate);


    }

    /**
     * Upgrades the database by dropping all existing tables and recreating them.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {

        strDelete="DROP TABLE IF EXISTS "+TABLE_EMPLOYEES;
        db.execSQL(strDelete);
        strDelete="DROP TABLE IF EXISTS "+TABLE_MEALS;
        db.execSQL(strDelete);
        strDelete="DROP TABLE IF EXISTS "+TABLE_FOOD_PROVIDERS;
        db.execSQL(strDelete);
        strDelete="DROP TABLE IF EXISTS "+TABLE_ORDERS;
        db.execSQL(strDelete);
        onCreate(db);
    }
}