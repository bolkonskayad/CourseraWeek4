package com.example.courseraweek4;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment startFragment = new StartFragment();
        changePage(startFragment);

        SharedPreferencesHelper mSharedPreferencesHelper = new SharedPreferencesHelper(this);

        if (mSharedPreferencesHelper == null) {
            mSharedPreferencesHelper.addSearching(Browsers.GOOGLE.toString());
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() == 1) {
            finish();
        } else {
            fragmentManager.popBackStack();
        }
    }

    private void changePage(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment)
                .addToBackStack(fragment.getClass().getName()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                showToast(getString(R.string.settings));
                Fragment settingsFragment = new SettingsFragment();
                changePage(settingsFragment);
                break;

            case R.id.menu_search:
                showToast(getString(R.string.search));
                Fragment searchingFragment = new SearchFragment();
                changePage(searchingFragment);
                break;
            case R.id.menu_out:
                showToast(getString(R.string.logout));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}