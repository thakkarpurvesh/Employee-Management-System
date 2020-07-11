package com.example.purvesh.employee;
// All Employee Records View To Admin
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {

    final ArrayList<String> players=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    SQLiteDatabase db;
    GridView gv;

    protected void onCreate(Bundle savedInstanceState) {
        
	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {

            db = openOrCreateDatabase("Employeemanage", Context.MODE_PRIVATE, null);
            gv = (GridView) findViewById(R.id.mygrid);
        }
        catch (Exception e){
          //  Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

        Cursor c = db.rawQuery("SELECT * FROM register", null);

        // Checking if no records found
        if (c.getCount() == 0) {

            Toast.makeText(getApplicationContext(),"No records found",Toast.LENGTH_LONG).show();
            return;
        }
        // Appending records to a string buffer
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {

            players.add(String.valueOf("Reg ID --> "+c.getString(0)+"\n"));
            players.add(String.valueOf("First name --> "+c.getString(1)+"\n"));
            players.add(String.valueOf("Mobile No --> "+c.getString(2)+"\n"));
            players.add(String.valueOf("Email Id --> "+c.getString(3)+"\n"));
            players.add(String.valueOf("Address --> "+c.getString(4)+"\n"));
            players.add(String.valueOf("Date of Birth --> "+c.getString(5)+"\n"));
            players.add(String.valueOf("Qualification --> "+c.getString(6)+"\n"));
            players.add(String.valueOf("Gender --> "+c.getString(7)+"\n"));
            players.add(String.valueOf("Project Language --> "+c.getString(8)+"\n"));
            players.add(String.valueOf("Password --> "+c.getString(9)+"\n"+"----------------"));

        }

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,players);
        gv.setAdapter(adapter);
	}
}
