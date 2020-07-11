package com.example.purvesh.employee;
//Contact Us activity

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class contactusFragment extends android.app.Fragment {

    EditText txt1,txt2,txt3;
    SQLiteDatabase db=null;
    Button contbtn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

     //   return inflater.inflate(R.layout.fragment_contactus, container, false);
        View myview =inflater.inflate(R.layout.fragment_contactus, container, false);

        try
        {
            db=getActivity().openOrCreateDatabase("Employeemanage", Context.MODE_PRIVATE,null);
            db.execSQL("create table contactus (regid integer primary key autoincrement,name varchar(15),email varchar2(15),txtmess varchar2(50))");

           // Toast.makeText(getActivity(), "Connection is successfull.....", Toast.LENGTH_LONG).show();

        }
        catch (Exception e){
          //  Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }


        txt1=(EditText)myview.findViewById(R.id.txtname);
        txt2=(EditText)myview.findViewById(R.id.txtmail);
        txt3=(EditText)myview.findViewById(R.id.yourmess);

        contbtn=(Button)myview.findViewById(R.id.btncu);

        contbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    String name=txt1.getText().toString();
                    String email=txt2.getText().toString();
                    String mess=txt3.getText().toString();

                    if(name.isEmpty()||email.isEmpty()||mess.isEmpty()){

                        Toast.makeText(getActivity(), "Input must not be empty..", Toast.LENGTH_LONG).show();
                    }


                    else
                    {
                        db.execSQL("insert into contactus (name,email,txtmess ) values('" + name + "','" + email + "','" + mess + "') ");
                        Toast.makeText(getActivity(), "Records Saved Successfully....", Toast.LENGTH_LONG).show();


                    }

                }
                catch (Exception e)
                {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG).show();
                }



            }
        });



        return myview;
    }
}
