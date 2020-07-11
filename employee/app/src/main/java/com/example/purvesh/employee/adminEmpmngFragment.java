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


public class adminEmpmngFragment extends android.app.Fragment {
Button btnsave,btndelete,btnupdate,btnsall,btnselect;
EditText nametxt,emailtxt2,mobtxt3,searchtxt;
SQLiteDatabase db=null;
String name,email,mob,search;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview=inflater.inflate(R.layout.fragment_admin_empmng, container, false);


        try {

            db = getActivity().openOrCreateDatabase("Employeemanage", Context.MODE_PRIVATE, null);
           // Toast.makeText(getActivity(), "Connection is successfull.....", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
           // Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }

    nametxt=(EditText)myview.findViewById(R.id.txtname);
    emailtxt2=(EditText)myview.findViewById(R.id.txtmail);
    mobtxt3=(EditText)myview.findViewById(R.id.txtmob);
    searchtxt=(EditText)myview.findViewById(R.id.txtsearch);

    btnsave=(Button)myview.findViewById(R.id.btnsave);
    btndelete=(Button)myview.findViewById(R.id.btndelete);
    btnsall=(Button)myview.findViewById(R.id.btnsall);
    btnupdate=(Button)myview.findViewById(R.id.btnupdate);
    btnselect=(Button)myview.findViewById(R.id.btnselect);


    btnsave.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.btnsave)
            {
                name= nametxt.getText().toString().trim();

                mob=mobtxt3.getText().toString().trim();
                email=emailtxt2.getText().toString().trim();

                if (name.equals("") || mob.equals("") || email.equals(""))
                {
                    Toast.makeText(getActivity(),"Fields can not be empty", Toast.LENGTH_SHORT).show();
                    }

                else {
                    db.execSQL("insert into register(firstname,lastname,email) values('"+name +"','" + mob + "','" + email + "') ");
                    Toast.makeText(getActivity(),"Record Saved",Toast.LENGTH_SHORT).show();
                }
            }
        }
    });

    btnsall.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.btnsall)
            {
                Cursor c=db.rawQuery("select * from register", null);
                if (c.getCount()==0)
                {
                    Toast.makeText(getActivity(),"Database is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (c.moveToNext())
                {
                    buffer.append("Employee Name: "+c.getString(1)+"\n");
                    buffer.append("Employee Mobile No: "+c.getString(2)+"\n");
                    buffer.append("Employee E-mail:"+c.getString(3)+"\n");
                    buffer.append("Employee Password"+c.getString(4)+"\n");
                }
                Toast.makeText(getActivity(),buffer.toString(),Toast.LENGTH_LONG).show();

            }
        }
    });

    btnselect.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.btnselect)
            {
                search=searchtxt.getText().toString().trim();
                if (search.equals(""))
                {
                    Toast.makeText(getActivity(),"Enter Employee Name",Toast.LENGTH_SHORT).show();
                    return;
                }

                Cursor c= db.rawQuery("select * from register where firstname= '"+ search+"'",null);
                if (c.moveToNext())
                {
                    nametxt.setText(c.getString(1));
                    mobtxt3.setText(c.getString(2));
                    emailtxt2.setText(c.getString(3));
                }
                else {
                    Toast.makeText(getActivity(),"Data Not Found",Toast.LENGTH_SHORT).show();
                }

            }
        }
    });

    btnupdate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            search=searchtxt.getText().toString().trim();
            name=nametxt.getText().toString().trim();
            mob=mobtxt3.getText().toString().trim();
            email=emailtxt2.getText().toString().trim();

            if (search.equals(""))
            {
                Toast.makeText(getActivity(),"Please Enter Employee Nane to Update",Toast.LENGTH_SHORT).show();
                return;
            }
            Cursor cupdate=db.rawQuery("select * from register where firstname= '"+ search+"'",null);
            if (cupdate.moveToNext())
            {
                if (name.equals("") || mob.equals("") || email.equals(""))
                {
                    Toast.makeText(getActivity(),"Fields can not be Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    db.execSQL("update register set firstname='"+name+"',lastname='"+mob+"',email='"+email+"' where firstname='"+search+"' ");
                    Toast.makeText(getActivity(),"Record Modified",Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(getActivity(),"Data Not Found",Toast.LENGTH_SHORT).show();
            }

        }
    });

    btndelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId()==R.id.btndelete)
            {
                search=searchtxt.getText().toString().trim();
                if (search.equals(""))
                {
                    Toast.makeText(getActivity(),"Please Enter Employee Name to Delete",Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor cdel=db.rawQuery("select * from register where firstname='"+search+"'",null);
                if (cdel.moveToNext())
                {
                    db.execSQL("delete from register where firstname='"+search+"' ");
                    Toast.makeText(getActivity(),"Record Deleted",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(),"Data not found",Toast.LENGTH_SHORT).show();
                }


            }
        }
    });

     return myview;
    }



}
