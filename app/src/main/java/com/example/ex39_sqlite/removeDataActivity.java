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

import com.example.ex39_sqlite.HelperDB;

import java.util.ArrayList;

public class removeDataActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView lv;
    ArrayAdapter<String> adp;
    SQLiteDatabase db;
    HelperDB hlp;
    Cursor crsr;

    int selectedIndex = -1;
    ArrayList<String> employeesArray, employeeIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_data);

        lv = findViewById(R.id.lv);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(this);

        hlp = new HelperDB(this);

        employeesArray = new ArrayList<>();
        employeeIds = new ArrayList<>();

        getEmployees();
    }

    private void getEmployees() {
        db = hlp.getReadableDatabase();
        crsr = db.query(Employees.TABLE_EMPLOYEES, null, null, null, null, null, null);

        employeesArray.clear();
        employeeIds.clear();

        int col1 = crsr.getColumnIndex(Employees.CARD_ID);
        int col2 = crsr.getColumnIndex(Employees.FIRST_NAME);
        int col3 = crsr.getColumnIndex(Employees.LAST_NAME);
        int col4 = crsr.getColumnIndex(Employees.COMPANY);
        int col5 = crsr.getColumnIndex(Employees.ID_NUMBER);
        int col6 = crsr.getColumnIndex(Employees.PHONE);

        crsr.moveToFirst();
        employeesArray.add("Employees");
        employeeIds.add("");

        while (!crsr.isAfterLast()) {
            String record =
                    "ID: " + crsr.getString(col1) + "\n" +
                            "Card ID: " + crsr.getString(col6) + "\n" +
                            "Name: " + crsr.getString(col3) + " " + crsr.getString(col4) + "\n" +
                            "Phone: " + crsr.getString(col5) + "\n" +
                            "Company: " + crsr.getString(col2);
            employeesArray.add(record);
            employeeIds.add(crsr.getString(col1));
            crsr.moveToNext();
        }
        crsr.close();
        db.close();

        adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employeesArray);
        lv.setAdapter(adp);

        adp.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long rowId) {
        if (pos == 0) {
            selectedIndex = -1;
        }
        selectedIndex = pos;
    }

    public void deleteData(View view) {
        if (selectedIndex <= 0 || selectedIndex >= employeeIds.size()) return;

        String employeeInfo = employeesArray.get(selectedIndex);

        new AlertDialog.Builder(this)
                .setTitle("מחיקת עובד")
                .setMessage("האם את בטוחה שברצונך למחוק את העובד?\n\n" + employeeInfo)
                .setPositiveButton("כן", (dialog, which) -> {
                    String employeeId = employeeIds.get(selectedIndex);
                    db = hlp.getWritableDatabase();

                    crsr = db.query(Orders.TABLE_ORDERS, new String[]{Orders.MEAL_ID}, Orders.EMPLOYEE_ID + "=?", new String[]{employeeId}, null, null, null);
                    ArrayList<String> mealIds = new ArrayList<>();
                    int mealIdCol = crsr.getColumnIndex(Orders.MEAL_ID);
                    while (crsr.moveToNext()) {
                        mealIds.add(crsr.getString(mealIdCol));
                    }
                    crsr.close();

                    for (String mealId : mealIds) {
                        db.delete(Meals.TABLE_MEALS, Meals.MEAL_ID + "=?", new String[]{mealId});
                    }
                    db.delete(Orders.TABLE_ORDERS, Orders.ORDER_ID + "=?", new String[]{employeeId});
                    db.delete(Employees.TABLE_EMPLOYEES, Employees.ID_NUMBER+ "=?", new String[]{employeeId});
                    db.close();

                    getEmployees();
                    selectedIndex = -1;

                    Toast.makeText(removeDataActivity.this, "העובד נמחק בהצלחה!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("לא", null)
                .show();
    }

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