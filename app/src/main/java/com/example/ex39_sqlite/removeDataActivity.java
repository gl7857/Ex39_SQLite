package com.example.ex39_sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
 *      This activity allows the user to remove employee entries from the database.
 *      Upon selecting an employee from the list, a confirmation dialog is shown before deletion.
 *      If confirmed, the selected employee is deleted along with all related orders and meals.
 *      The employee list is refreshed after a successful deletion.
 */



public class removeDataActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView employeeListView;
    ArrayAdapter<String> employeeAdapter;
    ArrayList<String> employeeDisplayList, employeeIdList;
    int selectedIndex = -1;

    SQLiteDatabase database;
    HelperDB dbHelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_data);

        employeeListView = findViewById(R.id.lv);
        employeeListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        employeeListView.setOnItemClickListener(this);

        dbHelper = new HelperDB(this);
        employeeDisplayList = new ArrayList<>();
        employeeIdList = new ArrayList<>();

        fetchEmployeeData();
    }

    /**
     * Retrieves all employee records from the database and populates two lists:
     * one for displaying employee information and one for storing employee IDs.
     * Updates the ListView with the formatted employee details.
     */
    private void fetchEmployeeData() {
        database = dbHelper.getReadableDatabase();
        cursor = database.query(Employees.TABLE_EMPLOYEES, null, null, null, null, null, null);

        employeeDisplayList.clear();
        employeeIdList.clear();

        int idCol = cursor.getColumnIndex(Employees.CARD_ID);
        int firstNameCol = cursor.getColumnIndex(Employees.FIRST_NAME);
        int lastNameCol = cursor.getColumnIndex(Employees.LAST_NAME);
        int companyCol = cursor.getColumnIndex(Employees.COMPANY);
        int idNumCol = cursor.getColumnIndex(Employees.ID_NUMBER);
        int phoneCol = cursor.getColumnIndex(Employees.PHONE);

        cursor.moveToFirst();
        employeeDisplayList.add("Select an employee");
        employeeIdList.add("");

        while (!cursor.isAfterLast()) {
            String info = "ID: " + cursor.getString(idCol) + "\n" +
                    "Full Name: " + cursor.getString(firstNameCol) + " " + cursor.getString(lastNameCol) + "\n" +
                    "Phone: " + cursor.getString(phoneCol) + "\n" +
                    "Company: " + cursor.getString(companyCol) + "\n" +
                    "Card Number: " + cursor.getString(idNumCol);

            employeeDisplayList.add(info);
            employeeIdList.add(cursor.getString(idCol));

            cursor.moveToNext();
        }

        cursor.close();
        database.close();

        employeeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employeeDisplayList);
        employeeListView.setAdapter(employeeAdapter);
        employeeAdapter.notifyDataSetChanged();
    }

    /**
     * Handles item click events for the employee list.
     * Updates the selected index based on the clicked position.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked.
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            selectedIndex = -1;
        } else {
            selectedIndex = position;
        }
    }


    /**
     * Deletes the selected employee and all related orders and meals from the database.
     * Prompts the user with a confirmation dialog before performing the deletion.
     * After deletion, refreshes the employee list and resets the selected index.
     *
     * @param view The view that triggered the method (e.g., a button click).
     */
    public void deleteData(View view) {
        if (selectedIndex <= 0 || selectedIndex >= employeeIdList.size()) return;

        String selectedEmployeeInfo = employeeDisplayList.get(selectedIndex);

        new AlertDialog.Builder(this)
                .setTitle("Confirm Deletion")
                .setMessage("Are you sure you want to delete the following employee?\n\n" + selectedEmployeeInfo)
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    String employeeId = employeeIdList.get(selectedIndex);
                    database = dbHelper.getWritableDatabase();

                    cursor = database.query(Orders.TABLE_ORDERS, new String[]{Orders.MEAL_ID}, Orders.EMPLOYEE_ID + "=?", new String[]{employeeId}, null, null, null);
                    ArrayList<String> mealIds = new ArrayList<>();
                    int mealCol = cursor.getColumnIndex(Orders.MEAL_ID);
                    while (cursor.moveToNext()) {
                        mealIds.add(cursor.getString(mealCol));
                    }
                    cursor.close();

                    for (String mealId : mealIds) {
                        database.delete(Meals.TABLE_MEALS, Meals.MEAL_ID + "=?", new String[]{mealId});
                    }

                    database.delete(Orders.TABLE_ORDERS, Orders.EMPLOYEE_ID + "=?", new String[]{employeeId});
                    database.delete(Employees.TABLE_EMPLOYEES, Employees.CARD_ID + "=?", new String[]{employeeId});
                    database.close();

                    fetchEmployeeData();
                    selectedIndex = -1;

                    Toast.makeText(removeDataActivity.this, "Employee was deleted successfully.", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
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
