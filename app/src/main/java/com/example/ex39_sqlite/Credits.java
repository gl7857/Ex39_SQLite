package com.example.ex39_sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity for displaying credits.
 * This activity shows the credits screen and allows the user to navigate back to the main activity.
 *
 * @author      Gali Lavi <gl7857@bs.amalnet.k12.il>
 * @version     1.0
 * @since       15/04/2025
 *
 * short description:
 *        This activity enables edge-to-edge display and shows a credits screen.
 *        It provides a simple interface with a menu that allows the user to navigate back to
 *        the main screen.
 */

public class Credits extends AppCompatActivity {
    /**
     * Called when the activity is first created.
     * Enables edge-to-edge display and sets the content view to the credits screen layout.
     *
     * @param savedInstanceState a Bundle containing the activity's previously saved state
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_credits);
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
