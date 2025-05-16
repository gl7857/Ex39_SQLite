package com.example.ex39_sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Activity for removing workers, meals, providers, and orders to the database.
 *
 * @author      Gali Lavi <gl7857@bs.amalnet.k12.il>
 * @version     1.0
 * @since       15/04/2025
 *
 * short description:
 *      Activity for displaying and sorting employee data using a Spinner and ListView.
 */

public class sortActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner filterSortSpin;
    private ListView lvrecords;

    private SQLiteDatabase db;
    private HelperDB hlp;
    private Cursor crsr;

    private ArrayList<String> tbl = new ArrayList<>();
    private ArrayAdapter<String> adp;
    private int tablechoice;

    private String[] sortFilterArr;


    /**
     * Initializes the activity when it is first created.
     * Sets the layout, initializes database helper, Spinner, ListView,
     * and sets default values and listeners.
     *
     * @param savedInstanceState A Bundle containing the activity's previously saved state,
     *                           or null if it's a fresh start.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort);

        filterSortSpin = findViewById(R.id.filterSortSpin);
        lvrecords = findViewById(R.id.lvRecords);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

        filterSortSpin.setOnItemSelectedListener(this);
        lvrecords.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        tablechoice = 0;
        sortFilterArr = new String[]{
                "אופן תצוגת המידע",
                "להציג שם משפחה",
                "למיין לפי שם פרטי",
                "למיין לפי תעודת זהות"
        };

        adp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sortFilterArr);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSortSpin.setAdapter(adp);
    }

    /**
     * Handles item selection in the Spinner.
     * Based on the selected option, it either:
     * - Retrieves last names only,
     * - Sorts employees by first name,
     * - Sorts employees by ID number.
     * The retrieved data is then displayed in a ListView.
     *
     * @param parent    The AdapterView where the selection happened
     * @param view      The view within the AdapterView that was clicked
     * @param pos       The position of the selected item in the Spinner
     * @param id        The row ID of the selected item
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        tbl.clear();
        db = hlp.getReadableDatabase();

        if (pos == 0) {
            return;
        }

        if (pos == 1) {
            getLastNamesOnly();
        } else if (pos == 2) {
            getAllEmployeesSortedByFirstName();
        } else if (pos == 3) {
            getAllEmployeesSortedByIdNumber();
        }

        db.close();

        adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
        lvrecords.setAdapter(adp);
    }

    /**
     * Called when no item is selected in the spinner.
     * This method is required by the AdapterView.OnItemSelectedListener interface,
     * but no action is needed when nothing is selected.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    /**
     * Retrieves only the last names of all employees from the database.
     * Each last name is added to the display list (tbl).
     */
    // פונקציה להוציא שמות משפחה בלבד
    private void getLastNamesOnly() {
        Cursor crsr = db.query(Employees.TABLE_EMPLOYEES,
                new String[]{Employees.LAST_NAME},
                null, null, null, null, null);

        int colLast = crsr.getColumnIndex(Employees.LAST_NAME);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            tbl.add(crsr.getString(colLast));
            crsr.moveToNext();
        }
        crsr.close();
    }

    // פונקציה למיין לפי שם פרטי
    /**
     * Retrieves all employee records from the database sorted by first name in ascending order.
     * The results are passed to the extractEmployeeData method for formatting and display.
     */

    private void getAllEmployeesSortedByFirstName() {
        Cursor crsr = db.query(Employees.TABLE_EMPLOYEES,
                null, null, null, null, null,
                Employees.FIRST_NAME + " ASC");

        extractEmployeeData(crsr);
    }

    /**
     * Retrieves all employee records from the database sorted by ID number in ascending order.
     * The results are passed to the extractEmployeeData method for formatting and display.
     */

    private void getAllEmployeesSortedByIdNumber() {
        Cursor crsr = db.query(Employees.TABLE_EMPLOYEES,
                null, null, null, null, null,
                Employees.ID_NUMBER + " ASC");

        extractEmployeeData(crsr);
    }

    /**
     * Extracts employee data from the given Cursor and formats each record into a string.
     * The formatted strings are added to the 'tbl' list for display.
     *
     * @param crsr The Cursor containing employee records from the database.
     */

    private void extractEmployeeData(Cursor crsr) {
        int colId = crsr.getColumnIndex(Employees.CARD_ID);
        int colFirst = crsr.getColumnIndex(Employees.FIRST_NAME);
        int colLast = crsr.getColumnIndex(Employees.LAST_NAME);
        int colCompany = crsr.getColumnIndex(Employees.COMPANY);
        int colIdNumber = crsr.getColumnIndex(Employees.ID_NUMBER);
        int colPhone = crsr.getColumnIndex(Employees.PHONE);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            String line = "Card ID: " + crsr.getInt(colId) + "\n"
                    + "Name: " + crsr.getString(colFirst) + " " + crsr.getString(colLast) + "\n"
                    + "Company: " + crsr.getString(colCompany) + "\n"
                    + "ID Number: " + crsr.getString(colIdNumber) + "\n"
                    + "Phone: " + crsr.getString(colPhone);
            tbl.add(line);
            crsr.moveToNext();
        }
        crsr.close();
    }

    /**
     * Creates the options menu.
     * @param menu The options menu in which items are placed.
     * @return True for the menu to be displayed.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Handles item selections in the options menu.
     * Navigates to different activities based on the selected menu item.
     *
     * @param item The menu item that was selected.
     * @return Return false to allow normal menu processing to
     * proceed, true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(@Nullable MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menuCredits) {
            Intent si = new Intent(this, Credits.class);
            startActivity(si);
        }
        else if (id == R.id.menuSortAndFilter) {
            Intent si = new Intent(this, sortActivity.class);
            startActivity(si);
        }
        else if (id == R.id.menuDisplayData) {
            Intent si = new Intent(this, DisplayActivity.class);
            startActivity(si);
        }
        else if (id == R.id.menuAddData) {
            Intent si = new Intent(this, AddActivity.class);
            startActivity(si);
        }
        else if (id == R.id.menuRemoveData) {
            Intent si = new Intent(this, removeDataActivity.class);
            startActivity(si);
        }
        return true;
    }
}
