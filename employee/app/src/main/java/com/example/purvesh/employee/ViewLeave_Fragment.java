package com.example.purvesh.employee;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;


public class ViewLeave_Fragment extends android.app.Fragment {
    final ArrayList<String> players=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    SQLiteDatabase db;
    GridView gv;
    Button btna,btnr;
    EditText txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview= inflater.inflate(R.layout.fragment_view_leave_, container, false);

        btna=(Button)myview.findViewById(R.id.btnapprove);
        btnr=(Button)myview.findViewById(R.id.btnreject);
        txt=(EditText)myview.findViewById(R.id.em);


        try {

            db = getActivity().openOrCreateDatabase("Employeemanage", Context.MODE_PRIVATE, null);
            db.execSQL("create table leavemng(leave varchar(20))");
           // Toast.makeText(getActivity(),"Leave Table Created",Toast.LENGTH_LONG).show();
            gv = (GridView) myview.findViewById(R.id.mygrid);
        }
        catch (Exception e){
            //Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }


        Cursor c = db.rawQuery("SELECT name,from_date,to_date,reason FROM tbl_leave ", null);


        if (c.getCount() == 0) {

            Toast.makeText(getActivity(),"No records found",Toast.LENGTH_LONG).show();
        }
        // Appending records to a string buffer

        while (c.moveToNext()) {

           // String eml = c.getString(0);

            players.add(String.valueOf("Emp Name --> "+c.getString(0)+"\n"));
            players.add(String.valueOf("From Date --> "+c.getString(1)+"\n"));
            players.add(String.valueOf("To Date --> "+c.getString(2)+"\n"));
            players.add(String.valueOf("Reason --> "+c.getString(3)+"\n"));

            adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,players);
            gv.setAdapter(adapter);
        }

        btna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String ema = txt.getText().toString();
                    //  if (ema.equals(eml)) {
                    db.execSQL("insert into leavemng (leave) values ('Approved') ");
                    Toast.makeText(getActivity(), "Leave Approved successfully", Toast.LENGTH_LONG).show();
                    //  }
                }
                catch (Exception ex)
                {
                    Toast.makeText(getActivity(),ex.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });

        btnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    //  if (ema.equals(eml)) {
                    db.execSQL("insert into leavemng (leave) values ('Rejected') ");
                    Toast.makeText(getActivity(), "Leave Rejected successfully", Toast.LENGTH_LONG).show();
                    // }

                }
                catch (Exception ex)
                {
                    Toast.makeText(getActivity(),ex.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });





        return myview;
    }




}
