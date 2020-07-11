package com.example.purvesh.employee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class TaskAllocate_Fragment extends android.app.Fragment {

    Spinner sp,spp;

    Button btn_task;
    SQLiteDatabase db;
    final List<String> lst = new ArrayList<String>();
    final List<String> bst = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View myview=   inflater.inflate(R.layout.fragment_task_allocate_, container, false);

        sp=(Spinner)myview.findViewById(R.id.projname);

        spp=(Spinner)myview.findViewById(R.id.lang);


        lst.add("Employee Management");
        lst.add("EBook");
        lst.add("Green City");
        lst.add("College Management");
        lst.add("Online Shopping");
        lst.add("Doctor Appointment");
        lst.add("Driving School Appointment");

        bst.add("android");
        bst.add("java");
        bst.add("php");
        bst.add("dotnet");


        final ArrayAdapter<String> adp = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, lst);

        final ArrayAdapter<String> ad = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, bst);

        sp.setAdapter(adp);

        spp.setAdapter(ad);


        try {


            db = getActivity().openOrCreateDatabase("Employeemanage", Context.MODE_PRIVATE, null);
            db.execSQL("create table  taskallocate (taskid integer primary key autoincrement,sp varchar(25),spp varchar(20))");

        }

        catch (Exception e){
            //  Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }


        btn_task=(Button)myview.findViewById(R.id.btntask);

        btn_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String projectname=sp.getSelectedItem().toString();
                    String projlang=spp.getSelectedItem().toString();

                    db.execSQL("insert into taskallocate (sp,spp) values('"+projectname+"','"+projlang+"')");
                    Toast.makeText(getActivity(),"Task Allocated Successfully...",Toast.LENGTH_LONG).show();

                }
                catch (Exception ex)
                {
                   // Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG).show();

                }

            }
        });







        return myview;
    }

}
