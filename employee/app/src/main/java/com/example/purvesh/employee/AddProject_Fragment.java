package com.example.purvesh.employee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddProject_Fragment extends android.app.Fragment {
Button btn;
EditText txt1,txt2,txt3,txt4;
SQLiteDatabase db=null;
String pname,plang,sdate,edate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview=inflater.inflate(R.layout.fragment_add_project_, container, false);


        try {

            db = getActivity().openOrCreateDatabase("Employeemanage", Context.MODE_PRIVATE, null);
            db.execSQL("create table projects (pid integer primary key autoincrement,pname varchar(20),plang varchar2(15),stdate varchar2(15),endate varchar2(15))");
           // Toast.makeText(getActivity(), "Connection is successfull.....", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
          //  Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }

        txt1=(EditText)myview.findViewById(R.id.name);
        txt2=(EditText)myview.findViewById(R.id.language);
        txt3=(EditText)myview.findViewById(R.id.stdate);
        txt4=(EditText)myview.findViewById(R.id.endate);

        btn=(Button)myview.findViewById(R.id.btnsave);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    pname = txt1.getText().toString();
                    plang = txt2.getText().toString();
                    sdate = txt3.getText().toString();
                    edate = txt4.getText().toString();

                    if (pname.isEmpty() || plang.isEmpty() || sdate.isEmpty() || edate.isEmpty()) {
                        Toast.makeText(getActivity(), "Input must not be empty..", Toast.LENGTH_LONG).show();
                    }
                    else {

                        db.execSQL("insert into projects (pname, plang, stdate, endate) values('" + pname + "','" + plang + "','" + sdate + "','" + edate + "') ");

                        Toast.makeText(getActivity(), "Records Saved Successfully....", Toast.LENGTH_LONG).show();
                    }
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
