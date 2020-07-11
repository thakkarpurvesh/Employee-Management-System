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


public class adminFeedbackFragment extends android.app.Fragment {

    final ArrayList<String> players = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    SQLiteDatabase db;
    GridView gv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View myview = inflater.inflate(R.layout.fragment_admin_feedback, container, false);

        // return inflater.inflate(R.layout.fragment_admin_feedback, container, false);
        try {

            db = getActivity().openOrCreateDatabase("Employeemanage", Context.MODE_PRIVATE, null);
            gv = (GridView) myview.findViewById(R.id.mygrid);
        } catch (Exception e) {
         //   Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }


        Cursor c = db.rawQuery("SELECT  name,email,txtfeedback,ratingstar FROM feedback", null);


        // Checking if no records found
        if (c.getCount() == 0) {

            Toast.makeText(getActivity(), "No records found", Toast.LENGTH_LONG).show();
        }
        // Appending records to a string buffer
       // StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {

            players.add(String.valueOf("Name      --> " + c.getString(0) + "\n"));
            players.add(String.valueOf("Email     --> " + c.getString(1) + "\n"));
            players.add(String.valueOf("Feedback  --> " + c.getString(2) + "\n"));
            players.add(String.valueOf("Rating Star --> " + c.getString(3) + "\n"));
            }

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, players);
        gv.setAdapter(adapter);

    return myview;
    }
}