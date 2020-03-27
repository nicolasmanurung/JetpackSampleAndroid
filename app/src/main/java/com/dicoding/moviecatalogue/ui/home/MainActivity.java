package com.dicoding.moviecatalogue.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dicoding.moviecatalogue.R;
import com.dicoding.moviecatalogue.ui.favorite.FavoriteFragment;
import com.dicoding.moviecatalogue.ui.movie.MovieFragment;
import com.dicoding.moviecatalogue.ui.tvshow.TVShowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private final String SELECTED_MENU = "selected_menu";
    private BottomNavigationView nView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItem = item -> {
        Fragment fragment = null;
        if (item.getItemId() == R.id.navigation_movie) {
            fragment = MovieFragment.newInstance();
        } else if (item.getItemId() == R.id.navigation_tvshow) {
            fragment = TVShowFragment.newInstance();
        } else if (item.getItemId() == R.id.navigation_favorite) {
            fragment = FavoriteFragment.newInstance();
        }

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.container_layout, fragment)
                    .commit();
        }
        return true;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nView = findViewById(R.id.navigation);
        nView.setOnNavigationItemSelectedListener(mOnNavigationItem);

        if (savedInstanceState != null) {
            savedInstanceState.getInt(SELECTED_MENU);
        } else {
            nView.setSelectedItemId(R.id.navigation_tvshow);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_MENU, nView.getSelectedItemId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
