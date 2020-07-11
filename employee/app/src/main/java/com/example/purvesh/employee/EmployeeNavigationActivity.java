package com.example.purvesh.employee;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class EmployeeNavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.employee_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentManager fragmentManager=getFragmentManager();
        int id = item.getItemId();

        if (id == R.id.nav_aboutus) {

            fragmentManager.beginTransaction().replace(R.id.content_frame,new aboutusFragment()).commit();

        } else if (id == R.id.nav_contactus) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new contactusFragment()).commit();

        } else if (id == R.id.nav_home) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new homeFragment()).commit();

        }
       /* else if (id == R.id.nav_feedback) {
            fragmentManager.beginTransaction().replace(R.id.content_nav,new feedbackFragment()).commit();

        }*/ else if (id == R.id.nav_adlogin) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new adminloginFragment()).commit();

        } else if(id == R.id.nav_projectmng) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new ProjectManagerFragment()).commit();
        }
        else if (id == R.id.nav_gallery) {

            fragmentManager.beginTransaction().replace(R.id.content_frame,new GalleryFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //home onclick
    public void loginmove(View view) {
        Intent i=new Intent(EmployeeNavigationActivity.this,userloginActivity.class);
        startActivity(i);
    }



}
