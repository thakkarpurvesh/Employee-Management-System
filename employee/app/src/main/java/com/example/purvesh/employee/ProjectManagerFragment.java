package com.example.purvesh.employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ProjectManagerFragment extends android.app.Fragment {
    String uname,pass;
    EditText username,password;
    Button btn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview= inflater.inflate(R.layout.fragment_project_manager, container, false);

        username = (EditText)myview.findViewById(R.id.name);
        password = (EditText)myview.findViewById(R.id.pass);
        btn=(Button)myview.findViewById(R.id.btnlogin);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uname=username.getText().toString();
                pass=password.getText().toString();
                try {
                    if(uname.equals("123") && pass.equals("123")){

                       // Toast.makeText(getActivity(), "Login Successfull", Toast.LENGTH_SHORT).show();
                        Intent a = new Intent(getActivity(), Project_manager_navActivity.class);
                        startActivity(a);
                    }
                    else if (uname.isEmpty() && pass.isEmpty()) {

                        username.setError("Enter Username");
                        username.requestFocus();
                        password.setError("Enter Password");
                      //  password.requestFocus();
                    }
                    else {
                        Toast.makeText(getActivity(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                        //wrong password
                    }

                }
                catch (Exception ex){

                   // Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });


     return myview;
    }
}
