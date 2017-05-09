package ua.com.lviv.fly.touristhelper.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import ua.com.lviv.fly.touristhelper.R;
import ua.com.lviv.fly.touristhelper.TemplateApplication;
import ua.com.lviv.fly.touristhelper.ui.fragments.MapFragment;
import ua.com.lviv.fly.touristhelper.ui.fragments.NearbyPlacesFragment;
import ua.com.lviv.fly.touristhelper.ui.fragments.OptionsFragment;
import ua.com.lviv.fly.touristhelper.ui.fragments.PrefsFragment;
import ua.com.lviv.fly.touristhelper.ui.fragments.ProfileFragment;
import ua.com.lviv.fly.touristhelper.ui.fragments.SettingsFragment;
import ua.com.lviv.fly.touristhelper.ui.fragments.TestFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private Menu menu;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                initUserView();
            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setDefaultFragment(MapFragment.newInstance());


    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_camera:
                setFragment(MapFragment.newInstance());
                break;
            case R.id.nav_gallery:
                setFragment(NearbyPlacesFragment.newInstance());
                break;
            case R.id.nav_slideshow:
                setFragment(SettingsFragment.newInstance());
                break;
            case R.id.nav_share:
                setFragment(PrefsFragment.newInstance());
                break;
            case R.id.nav_send:
                setFragment(TestFragment.newInstance());
                break;
            case R.id.nav_opt:
                setFragment(OptionsFragment.newInstance());
                break;
            case R.id.nav_profile:
                setFragment(ProfileFragment.newInstance());
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        toolbar.setTitle(item.getTitle());
        return true;
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();
    }

    private void setDefaultFragment(Fragment fragment) {
        setFragment(fragment);
        toolbar.setTitle("Map");
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    private void initUserView() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(TemplateApplication.getSharedContext());
        String name = defaultSharedPreferences.getString(getString(R.string.user_first_name_key), getString(R.string.user_first_name_default_value));
        String email = defaultSharedPreferences.getString(getString(R.string.user_email_key), getString(R.string.user_email_title));

        View headerLayout = navigationView.getHeaderView(0);
        TextView userName = (TextView) headerLayout.findViewById(R.id.userName);
        userName.setText(name);

        TextView userEmail = (TextView) headerLayout.findViewById(R.id.userEmail);
        userEmail.setText(email);


    }
}
