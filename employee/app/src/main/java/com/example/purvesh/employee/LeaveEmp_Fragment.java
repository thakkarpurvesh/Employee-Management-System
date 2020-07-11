package com.example.purvesh.employee;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LeaveEmp_Fragment extends android.app.Fragment {
    Button requstbtn;
    EditText txt1,txt2,txt3,txt4;
    SQLiteDatabase db=null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View myview=inflater.inflate(R.layout.fragment_leave_emp_, container, false);



        try
        {
            db=getActivity().openOrCreateDatabase("Employeemanage", Context.MODE_PRIVATE,null);
            db.execSQL("create table tbl_leave (lid integer primary key autoincrement,name varchar(15),from_date varchar2(15), to_date varchar2(15),reason varchar2(50))");

         //   Toast.makeText(getActivity(), "Connection is successfull.....", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
            //  Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }

        txt1=(EditText)myview.findViewById(R.id.txtname);
        txt2=(EditText)myview.findViewById(R.id.frmdate);
        txt3=(EditText)myview.findViewById(R.id.todate);
        txt4=(EditText)myview.findViewById(R.id.reason);
        requstbtn=(Button)myview.findViewById(R.id.request);

        requstbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{

                    String name=txt1.getText().toString();
                    String from_date=txt2.getText().toString();
                    String to_date=txt3.getText().toString();
                    String reason=txt4.getText().toString();
                   // String le=getActivity().getIntent().getExtras().getString("rid").toString();
                    Cursor c=db.rawQuery("select email from register",null);
                    while(c.moveToNext()) {

                        String em = c.getString(0);
                        //    int ratin=ratingbar1.getNumStars();
                        if (name.isEmpty() && from_date.isEmpty() && to_date.isEmpty() && reason.isEmpty()) {

                            Toast.makeText(getActivity(), "Input must not be empty..", Toast.LENGTH_SHORT).show();
                        }
                        else
                            {
                            if (name.equals(em)) {
                                db.execSQL("insert into tbl_leave (name,from_date,to_date,reason) values('" + name + "','" + from_date + "','" + to_date + "','" + reason + "') ");
                                Toast.makeText(getActivity(), "Records Saved Successfully....", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(), "Request not send", Toast.LENGTH_SHORT).show();
                }


            }
        });


        return myview;
    }


}
