package com.st18apps.passwordgen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.st18apps.passwordgen.ui.fragment.aboutApp.AboutAppFragment;
import com.st18apps.passwordgen.ui.fragment.favorites.FavoritesFragment;
import com.st18apps.passwordgen.ui.fragment.generatePassword.GeneratePasswordFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    //final String CURRENT_FRAGMENT = "Current fragment";
    //final String TAG_GENERATE_PASSWORD = "GeneratePasswordFragment";
    //final String TAG_FAVORITE = "FavoriteFragment";
    //final String TAG_ABOUT_APP = "AboutAppFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null){
            fragment = new GeneratePasswordFragment();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content, fragment).commit();
        }
        /*
        if (savedInstanceState != null) {
            currentFragment = savedInstanceState.getInt(CURRENT_FRAGMENT, 1);
        }

        fragmentManager = getSupportFragmentManager();

        switch (currentFragment) {

            case 1:
                fragment = new GeneratePasswordFragment();
                currentFragment = 1;
                break;
            case 2:
                fragment = new FavoritesFragment();
                currentFragment = 2;
                break;
            case 3:
                fragment = new AboutAppFragment();
                currentFragment = 3;
                break;
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, fragment).commit();

        */
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new GeneratePasswordFragment();
                  //  currentFragment = 1;
                    break;
                case R.id.navigation_favorite:
                    fragment = new FavoritesFragment();
                   // currentFragment = 2;
                    break;
                case R.id.navigation_about:
                    fragment = new AboutAppFragment();
                   // currentFragment = 3;
                    break;
            }
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content, fragment).commit();
            return true;
        }
    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
       // outState.putInt(CURRENT_FRAGMENT, currentFragment);

    }
}
