package com.example.purvesh.employee;
//Admin login activity

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class adminloginFragment extends android.app.Fragment {

    String uname,pass;
    EditText username,password;
    Button btn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View myview=inflater.inflate(R.layout.fragment_adminlogin, container, false);

       // return inflater.inflate(R.layout.fragment_adminlogin, container, false);

        username = (EditText)myview.findViewById(R.id.nm);
        password = (EditText)myview.findViewById(R.id.pass);
        btn=(Button)myview.findViewById(R.id.btnadmin);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uname=username.getText().toString();
                pass=password.getText().toString();
                try {
                    if(uname.equals("admin") && pass.equals("admin")){

                      //  Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent a = new Intent(getActivity(), AdminloginActivity.class);
                        startActivity(a);
                    }
                    else if(uname.isEmpty() && pass.isEmpty()) {

                        username.setError("Enter Username");
                        username.requestFocus();
                        password.setError("Enter Password");

                       // Toast.makeText(getActivity(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                        //wrong password
                    }
                }
                catch (Exception ex){

                    Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });


        return myview;
    }

    }

