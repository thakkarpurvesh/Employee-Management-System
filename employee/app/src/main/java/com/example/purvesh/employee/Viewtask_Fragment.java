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


public class Viewtask_Fragment extends android.app.Fragment {

SQLiteDatabase db=null;
    final ArrayList<String> players=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    GridView gv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview= inflater.inflate(R.layout.fragment_viewtask_, container, false);
        String te= getActivity().getIntent().getExtras().getString("tecal").toString();
        Toast.makeText(getActivity(), te, Toast.LENGTH_LONG).show();


        try {

            db = getActivity().openOrCreateDatabase("Employeemanage", Context.MODE_PRIVATE, null);
            gv = (GridView) myview.findViewById(R.id.mygrid);
        }
        catch (Exception e){
         //   Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }

            //Cursor c = db.rawQuery("SELECT register.firstname, taskallcate.sp, taskallocate.spp FROM  register inner join taskallocate on register.regid=taskallocate.taskid", null);
            //Cursor c = db.rawQuery("select sp,spp from taskallocate where spp='"+technical+"' ", null);
        Cursor c = db.rawQuery("SELECT sp,spp FROM taskallocate where spp='"+te+"'", null);
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
            //players.add(String.valueOf("employee name-->"+c.getString(0)));
            players.add(String.valueOf("Project Name --> "+c.getString(0)));
            players.add(String.valueOf("Project Language -->"+c.getString(1)));

           }

        adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,players);
        gv.setAdapter(adapter);



        return myview;
    }


}
