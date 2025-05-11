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

        // יצירת האדפטר עם הערכים של ה-Spinner
        adp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sortFilterArr);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filterSortSpin.setAdapter(adp);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        tbl.clear(); // ניקוי רשימת ה-ListView
        db = hlp.getReadableDatabase(); // קריאה לבסיס נתונים

        if (pos == 0) {
            return; // אם לא בחרו אפשרות, אל תבצע כל פעולה
        }

        if (pos == 1) { // הצגת שמות משפחה בלבד
            getLastNamesOnly();
        } else if (pos == 2) { // מיון לפי שם פרטי
            getAllEmployeesSortedByFirstName();
        } else if (pos == 3) { // מיון לפי תעודת זהות
            getAllEmployeesSortedByIdNumber();
        }

        db.close(); // סגירת חיבור לבסיס נתונים

        // הגדרת האדפטר ל- ListView עם המידע החדש
        adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tbl);
        lvrecords.setAdapter(adp);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

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
    private void getAllEmployeesSortedByFirstName() {
        Cursor crsr = db.query(Employees.TABLE_EMPLOYEES,
                null, null, null, null, null,
                Employees.FIRST_NAME + " ASC");

        extractEmployeeData(crsr);
    }

    // פונקציה למיין לפי תעודת זהות
    private void getAllEmployeesSortedByIdNumber() {
        Cursor crsr = db.query(Employees.TABLE_EMPLOYEES,
                null, null, null, null, null,
                Employees.ID_NUMBER + " ASC");

        extractEmployeeData(crsr);
    }

    // פונקציה משותפת לקריאת כל עמודות העובד
    private void extractEmployeeData(Cursor crsr) {
        int colId = crsr.getColumnIndex(Employees.CARD_ID);
        int colFirst = crsr.getColumnIndex(Employees.FIRST_NAME);
        int colLast = crsr.getColumnIndex(Employees.LAST_NAME);
        int colCompany = crsr.getColumnIndex(Employees.COMPANY);
        int colIdNumber = crsr.getColumnIndex(Employees.ID_NUMBER);
        int colPhone = crsr.getColumnIndex(Employees.PHONE);

        crsr.moveToFirst();
        while (!crsr.isAfterLast()) {
            String line = "Card ID: " + crsr.getInt(colId) +
                    ", Name: " + crsr.getString(colFirst) + " " + crsr.getString(colLast) +
                    ", Company: " + crsr.getString(colCompany) +
                    ", ID Number: " + crsr.getString(colIdNumber) +
                    ", Phone: " + crsr.getString(colPhone);
            tbl.add(line);
            crsr.moveToNext();
        }
        crsr.close();
    }

    @Override
    public boolean onOptionsItemSelected(@Nullable MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuCredits) {
            Intent si = new Intent(this, Credits.class);
            startActivity(si);
        } else if (id == R.id.menuSortAndFilter) {
            Intent si = new Intent(this, sortActivity.class);
            startActivity(si);
        } else if (id == R.id.menuDisplayData) {
            Intent si = new Intent(this, DisplayActivity.class);
            startActivity(si);
        } else if (id == R.id.menuAddData) {
            Intent si = new Intent(this, AddActivity.class);
            startActivity(si);
        } else if (id == R.id.menuRemoveData) {
            Intent si = new Intent(this, removeDataActivity.class);
            startActivity(si);
        }
        return true;
    }
}
