package com.example.purvesh.employee;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;


public class JavaEmp_Fragment extends android.app.Fragment {


    final ArrayList<String> players=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    SQLiteDatabase db;
    GridView gv;


    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview= inflater.inflate(R.layout.fragment_java_emp_, container, false);



        try {

            db = getActivity().openOrCreateDatabase("Employeemanage", Context.MODE_PRIVATE, null);
            gv = (GridView) myview.findViewById(R.id.mygrid);
        }
        catch (Exception e){
           // Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }


        Cursor c = db.rawQuery("SELECT * FROM register where technical='java' or technical='Java' or technical='JAVA' ", null);

        // Checking if no records found
        if (c.getCount() == 0) {

            Toast.makeText(getActivity(),"No records found",Toast.LENGTH_LONG).show();
        }
        // Appending records to a string buffer
       StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            //buffer.append("Auto Id: "+c.getString(0)+"\n");

            /*buffer.append("Roll no: " + c.getString(0) + "\n");
            buffer.append("Name: " + c.getString(1) + "\n");
            buffer.append("Marks: " + c.getString(2) + "\n");*/

            players.add(String.valueOf("Reg ID --> "+c.getString(0)+"\n"));
            players.add(String.valueOf("First name --> "+c.getString(1)+"\n"));
            players.add(String.valueOf("Mobile No --> "+c.getString(2)+"\n"));
            players.add(String.valueOf("Email Id --> "+c.getString(3)+"\n"));
            players.add(String.valueOf("Project Language --> "+c.getString(4)+"\n"));
            players.add(String.valueOf("Password --> "+c.getString(5)+"\n"));
        }

        adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,players);
        gv.setAdapter(adapter);


return myview;
    }

}
