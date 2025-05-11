package com.example.ex39_sqlite;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {
    private static final String DATABASE_NAME = "dbexam.db";
    private static final int DATABASE_VERSION = 1;

    private AlertDialog.Builder adb;

    private LinearLayout mydialog;

    private SQLiteDatabase db;
    private HelperDB hlp;

    int choice;

    private EditText etCardId,etFirstName,etLastName,etCompany,etIdNumber,etPhone
                    ,etMealId,etStarter,etMainCourse,etSideDish,etDessert,etDrink
                    ,etProviderId,etCompanyName,etMainPhone,etSecondaryPhone
                     ,etOrderId,etDate,etTime,etEmployeeId;

    private String firstName,lastName,company,idNumber,phone,
            starter,mainCourse,sideDish,dessert,drink
            ,mainPhone,secondaryPhone,date,time,cardId,mealId,providerId
            ,orderId,employeeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        db.close();

    }


    public void worker_click(View view){
        mydialog = (LinearLayout) getLayoutInflater().inflate(R.layout.my_dialog_worker, null);
        choice = 1;

        etCardId= mydialog.findViewById(R.id.etCardId);
        etFirstName = mydialog.findViewById(R.id.etFirstName);
        etLastName= mydialog.findViewById(R.id.etLastName);
        etCompany = mydialog.findViewById(R.id.etCompany);
        etIdNumber = mydialog.findViewById(R.id.etIdNumber);
        etPhone = mydialog.findViewById(R.id.etPhone);

        adb = new AlertDialog.Builder(this);
        adb.setView(mydialog);
        adb.setTitle("Add worker");
        adb.setPositiveButton("Enter", myclick);
        adb.setNegativeButton("Cancel", myclick);
        adb.setNeutralButton("Reset", myclick);

        adb.show();
    }



    public void meal_click(View view){
        mydialog = (LinearLayout) getLayoutInflater().inflate(R.layout.my_dialog_meal, null);
        choice = 2;

        etMealId = mydialog.findViewById(R.id.etMealId);
        etStarter = mydialog.findViewById(R.id.etStarter);
        etMainCourse = mydialog.findViewById(R.id.etMainCourse);
        etSideDish = mydialog.findViewById(R.id.etSideDish);
        etDessert = mydialog.findViewById(R.id.etDessert);
        etDrink = mydialog.findViewById(R.id.etDrink);

        adb = new AlertDialog.Builder(this);
        adb.setView(mydialog);
        adb.setTitle("Add meal");
        adb.setPositiveButton("Enter", myclick);
        adb.setNegativeButton("Cancel", myclick);
        adb.setNeutralButton("Reset", myclick);

        adb.show();
    }

    public void foodProvider_click(View view){
        mydialog = (LinearLayout) getLayoutInflater().inflate(R.layout.my_dialog_food_provider, null);
        choice = 3;

        etProviderId = mydialog.findViewById(R.id.etProviderId);
        etCompanyName = mydialog.findViewById(R.id.etCompanyName);
        etMainPhone = mydialog.findViewById(R.id.etMainPhone);
        etSecondaryPhone = mydialog.findViewById(R.id.etSecondaryPhone);

        adb = new AlertDialog.Builder(this);
        adb.setView(mydialog);
        adb.setTitle("Add food provider");
        adb.setPositiveButton("Enter", myclick);
        adb.setNegativeButton("Cancel", myclick);
        adb.setNeutralButton("Reset", myclick);

        adb.show();
    }

    public void order_click(View view){
        mydialog = (LinearLayout) getLayoutInflater().inflate(R.layout.my_dialog_order, null);
        choice = 4;

        etOrderId = mydialog.findViewById(R.id.etOrderId);
        etDate = mydialog.findViewById(R.id.etDate);
        etTime = mydialog.findViewById(R.id.etTime);
        etEmployeeId = mydialog.findViewById(R.id.etEmployeeId);
        etMealId = mydialog.findViewById(R.id.etMealId1);
        etProviderId = mydialog.findViewById(R.id.etProviderId1);

        adb = new AlertDialog.Builder(this);
        adb.setView(mydialog);
        adb.setTitle("Add order");
        adb.setPositiveButton("Enter", myclick);
        adb.setNegativeButton("Cancel", myclick);
        adb.setNeutralButton("Reset", myclick);

        adb.show();
    }

    DialogInterface.OnClickListener myclick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                if (choice == 1) {//new worker
                    if(isValidId(etIdNumber.getText().toString()) &&
                            isValidPhoneNumber(etPhone.getText().toString()) &&
                            !etCardId.getText().toString().isEmpty()&&
                            !etLastName.getText().toString().isEmpty() &&
                            !etFirstName.getText().toString().isEmpty()&&
                            !etCompany.getText().toString().isEmpty()){
                        
                        cardId = etCardId.getText().toString();
                        lastName = etLastName.getText().toString();
                        firstName= etFirstName.getText().toString();
                        company = etCompany.getText().toString();
                        phone = etPhone.getText().toString();
                        idNumber = etIdNumber.getText().toString();

                        ContentValues cv = new ContentValues();

                        cv.put(Employees.CARD_ID, cardId);
                        cv.put(Employees.LAST_NAME, lastName);
                        cv.put(Employees.FIRST_NAME, firstName);
                        cv.put(Employees.COMPANY, company);
                        cv.put(Employees.ID_NUMBER, idNumber);
                        cv.put(Employees.PHONE, phone);

                        db = hlp.getWritableDatabase();
                        db.insert(Employees.TABLE_EMPLOYEES, null, cv);
                        db.close();
                    }
                } else if (choice == 2) {// new meal
                    if (!etMealId.getText().toString().isEmpty() &&
                            !etStarter.getText().toString().isEmpty() &&
                            !etMainCourse.getText().toString().isEmpty()&&
                            !etSideDish.getText().toString().isEmpty() &&
                            !etDessert.getText().toString().isEmpty()&&
                            !etDrink.getText().toString().isEmpty()) {

                        starter = etStarter.getText().toString();
                        mainCourse = etMainCourse.getText().toString();
                        sideDish = etSideDish.getText().toString();
                        dessert = etDessert.getText().toString();
                        drink = etDrink.getText().toString();

                        ContentValues cv = new ContentValues();

                        cv.put(Meals.MEAL_ID, mealId);
                        cv.put(Meals.STARTER, starter);
                        cv.put(Meals.MAIN_COURSE, mainCourse);
                        cv.put(Meals.SIDE_DISH, sideDish);
                        cv.put(Meals.DESSERT, dessert);
                        cv.put(Meals.DRINK, drink);

                        db = hlp.getWritableDatabase();
                        db.insert(Meals.TABLE_MEALS, null, cv);
                        db.close();
                    }
                } else if (choice == 3) {//new food provider
                    if(!etProviderId.getText().toString().isEmpty() &&
                        isValidPhoneNumber(etMainPhone.getText().toString()) &&
                        isValidPhoneNumber(etSecondaryPhone.getText().toString())&&
                        !etCompanyName.getText().toString().isEmpty()&&
                        !etProviderId.getText().toString().isEmpty()){

                        mainPhone = etMainPhone.getText().toString();
                        secondaryPhone = etSecondaryPhone.getText().toString();
                        company = etCompanyName.getText().toString();
                        providerId = etProviderId.getText().toString();

                        ContentValues cv = new ContentValues();

                        cv.put(FoodProviders.PROVIDER_ID, providerId);
                        cv.put(FoodProviders.COMPANY_NAME, company);
                        cv.put(FoodProviders.MAIN_PHONE, mainPhone);
                        cv.put(FoodProviders.SECONDARY_PHONE, secondaryPhone);

                        db = hlp.getWritableDatabase();
                        db.insert(FoodProviders.TABLE_FOOD_PROVIDERS, null, cv);
                        db.close();
                    }
                } else if (choice == 4) { // new order
                    if(!etOrderId.getText().toString().isEmpty() &&
                        isValidDate(etDate.getText().toString()) &&
                        isValidTime(etTime.getText().toString()) &&
                        !etEmployeeId.getText().toString().isEmpty() &&
                        !etMealId.getText().toString().isEmpty() &&
                        !etProviderId.getText().toString().isEmpty()){

                        orderId = etOrderId.getText().toString();
                        date = etDate.getText().toString();
                        time = etTime.getText().toString();
                        employeeId = etEmployeeId.getText().toString();
                        mealId = etMealId.getText().toString();
                        providerId = etProviderId.getText().toString();

                        ContentValues cv = new ContentValues();

                        cv.put(Orders.ORDER_ID, orderId);
                        cv.put(Orders.DATE, date);
                        cv.put(Orders.TIME, time);
                        cv.put(Orders.EMPLOYEE_ID, employeeId);
                        cv.put(Orders.MEAL_ID, mealId);
                        cv.put(Orders.PROVIDER_ID, providerId);

                        db = hlp.getWritableDatabase();
                        db.insert(Orders.TABLE_ORDERS, null, cv);
                        db.close();
                    }
                }
            }

            if (which == DialogInterface.BUTTON_NEGATIVE) {
                dialog.cancel();
            }

            if (which == DialogInterface.BUTTON_NEUTRAL) {
                if (choice == 1) {
                    etCardId.setText("");
                    etFirstName.setText("");
                    etLastName.setText("");
                    etCompany.setText("");
                    etIdNumber.setText("");
                    etPhone.setText("");

                } else if (choice == 2) {
                    etMealId.setText("");
                    etStarter.setText("");
                    etMainCourse.setText("");
                    etSideDish.setText("");
                    etDessert.setText("");
                    etDrink.setText("");

                } else if (choice == 3) {
                    etProviderId.setText("");
                    etCompanyName.setText("");
                    etMainPhone.setText("");
                    etSecondaryPhone.setText("");

                } else if (choice == 4) {
                    etOrderId.setText("");
                    etDate.setText("");
                    etTime.setText("");
                    etEmployeeId.setText("");
                    etMealId.setText("");
                    etProviderId.setText("");
                }
            }
        }
    };

    public boolean isValidId(String id){
        return id.length() == 9 && !id.isEmpty();
    }


    public boolean isValidDate(String date){
        if (date.length() != 10 || date.charAt(2) != '/' || date.charAt(5) != '/' || date.isEmpty())
            return false;

        try {
            int day = Integer.parseInt(date.substring(0, 2));
            int month = Integer.parseInt(date.substring(3, 5));
            int year = Integer.parseInt(date.substring(6));

            if (month < 1 || month > 12 || day < 1)
                return false;

            int[] daysInMonth = new int[12];
            daysInMonth[0] = 31;
            daysInMonth[2] = 31;
            daysInMonth[3] = 30;
            daysInMonth[4] = 31;
            daysInMonth[5] = 30;
            daysInMonth[6] = 31;
            daysInMonth[7] = 31;
            daysInMonth[8] = 30;
            daysInMonth[9] = 31;
            daysInMonth[10] = 30;
            daysInMonth[11] = 31;

            if (isLeapYear(year)) {
                daysInMonth[1] = 29;
            } else {
                daysInMonth[1] = 28;
            }

            return day <= daysInMonth[month - 1];
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidPhoneNumber(String phone) {
        if (phone.length() != 10 || !phone.substring(0, 2).equals("05") || phone.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isValidTime(String time) {
        if (time.length() != 5 || time.charAt(2) != ':' || time.isEmpty())
            return false;

        try {
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(3));

            if (hour < 0 || hour > 23 || minute < 0 || minute > 59)
                return false;

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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