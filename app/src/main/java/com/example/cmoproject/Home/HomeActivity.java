package com.example.cmoproject.Home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.cmoproject.Accounts.Accounts;
import com.example.cmoproject.Adapter.ViewPagerAdapter;
import com.example.cmoproject.Dashboard.Dashboard;
import com.example.cmoproject.Info.Info;
import com.example.cmoproject.Login.MainActivity;
import com.example.cmoproject.R;
import com.example.cmoproject.Settings.SettingsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private HomeActivityViewModel viewModel;



    //This is our viewPager
    private ViewPager viewPager;

    //Fragments

    Dashboard Dashboard;
    Accounts Accounts;
    Info Info;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        viewModel = ViewModelProviders.of(this).get(HomeActivityViewModel.class);


        //Initializing viewPager
        viewPager = findViewById(R.id.viewpager);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        View v = navigationView.getHeaderView(0);
        final TextView userEmail= (TextView ) v.findViewById(R.id.textViewName);

        final TextView userName = (TextView) v.findViewById(R.id.textViewNameUser);

        viewModel.userEmail.observe(this, new Observer <String> (){
            @Override
            public void onChanged(String email) {
                userEmail.setText(email);
            }
        });

        viewModel.userInfo.observe(this, user -> {
            if(user != null) {
                userName.setText(user.first_name + " " + user.last_name);
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bnavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_dashboard:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.action_accounts:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.action_info:
                                viewPager.setCurrentItem(2);
                                break;
                        }
                        return false;
                    }
                });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);
  }
  private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Dashboard =new Dashboard();
        Accounts =new Accounts();
        Info=new Info();
        adapter.addFragment(Dashboard);
        adapter.addFragment(Accounts);
        adapter.addFragment(Info);
        viewPager.setAdapter(adapter);

        //adapter.updateData();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sortvalues) {
            // Handle the camera action
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_calendaar) {

        } else if (id == R.id.nav_showaccounts) {

        } else if (id == R.id.nav_logout) {
            viewModel.LogOut();
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
