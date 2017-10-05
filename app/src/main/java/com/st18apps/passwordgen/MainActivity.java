package com.st18apps.passwordgen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.st18apps.passwordgen.ui.fragment.aboutApp.AboutAppFragment;
import com.st18apps.passwordgen.ui.fragment.favorites.FavoritesFragment;
import com.st18apps.passwordgen.ui.fragment.generatePassword.GeneratePasswordFragment;
import com.st18apps.passwordgen.ui.fragment.generatePassword.HelpDialog;

public class MainActivity extends AppCompatActivity {

    private Fragment mFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            mFragment = new GeneratePasswordFragment();
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.replace(R.id.content, mFragment).commit();
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menuInfo) {
            showHelpDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mFragment = new GeneratePasswordFragment();
                    showHelpMenu();
                    break;
                case R.id.navigation_favorite:
                    mFragment = new FavoritesFragment();
                    hideHelpMenu();
                    break;
                case R.id.navigation_about:
                    mFragment = new AboutAppFragment();
                    hideHelpMenu();
                    break;
            }
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.replace(R.id.content, mFragment).commit();
            return true;
        }
    };

    public void showHelpDialog() {

        android.app.FragmentManager fm = this.getFragmentManager();
        HelpDialog helpDialog = new HelpDialog();
        helpDialog.show(fm, "Help Dialog Fragment");
    }

    public void showHelpMenu() {
        ActionMenuItemView item = (ActionMenuItemView) findViewById(R.id.menuInfo);
        item.setVisibility(View.VISIBLE);
    }

    public void hideHelpMenu() {
        ActionMenuItemView item = (ActionMenuItemView) findViewById(R.id.menuInfo);
        item.setVisibility(View.GONE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
