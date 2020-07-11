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
import android.widget.TextView;

public class employeemanageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView mytxt;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeemanage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String myuser=getIntent().getExtras().getString("user").toString();

        mytxt=(TextView)findViewById(R.id.txtView);
        mytxt.setText(myuser);

/*        String fname=getIntent().getExtras().getString("fname").toString();

        mytxt=(TextView)findViewById(R.id.txtView);
        mytxt.setText(fname);
*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
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
        getMenuInflater().inflate(R.menu.employeemanage, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        FragmentManager fragmentManager=getFragmentManager();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cngpass) {

            fragmentManager.beginTransaction().replace(R.id.content_employeemng,new changepassFragment()).commit();
        }
        else if (id == R.id.action_feedback) {

            fragmentManager.beginTransaction().replace(R.id.content_employeemng, new feedbackFragment()).commit();

        }
        else if (id == R.id.action_logout) {

            Intent g=new Intent(getApplicationContext(),EmployeeNavigationActivity.class);
            startActivity(g);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentManager fragmentManager=getFragmentManager();

        int id = item.getItemId();

        if (id == R.id.nav_editprofile) {
            fragmentManager.beginTransaction().replace(R.id.content_employeemng, new EditprofileFragment()).commit();

        }/* else if (id == R.id.nav_cngpass) {

            fragmentManager.beginTransaction().replace(R.id.content_employeemng, new changepassFragment()).commit();


        }*/ else if(id == R.id.nav_viewtask) {
            fragmentManager.beginTransaction().replace(R.id.content_employeemng,new Viewtask_Fragment()).commit();

        }
        else if(id==R.id.nav_leave) {
            fragmentManager.beginTransaction().replace(R.id.content_employeemng,new LeaveEmp_Fragment()).commit();
            
        }
        else if(id==R.id.nav_leaveview) {
            fragmentManager.beginTransaction().replace(R.id.content_employeemng,new ViewLeavereqFragment()).commit();
        }
        else if (id == R.id.nav_logout) {
            Intent p=new Intent(employeemanageActivity.this, EmployeeNavigationActivity.class);
            startActivity(p);
            finish();
        }
     /*   else if(id == R.id.nav_feedback) {

            fragmentManager.beginTransaction().replace(R.id.content_employeemng, new feedbackFragment()).commit();

        }*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
