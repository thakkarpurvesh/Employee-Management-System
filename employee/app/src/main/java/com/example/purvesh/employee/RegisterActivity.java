package com.example.purvesh.employee;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    Button btnmove;
    Spinner technical;
    SQLiteDatabase db=null;
    EditText Text2,Text3,Text4,Text6,Text7,Text8,Text9,Text10;
    String gend="";
     android.widget.RadioButton male,female;
    ScrollView regcolor;
    AnimationDrawable anim;
    final List<String> bst = new ArrayList<String>();
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        technical=(Spinner)findViewById(R.id.editText8);

        bst.add("android");
        bst.add("java");
        bst.add("php");
        bst.add("dotnet");


        final ArrayAdapter<String> adp = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, bst);

        technical.setAdapter(adp);


        try
        {
            db= openOrCreateDatabase("Employeemanage",MODE_PRIVATE, null);
            db.execSQL("create table register (regid integer primary key autoincrement,firstname varchar(15),lastname varchar2(15),email varchar2(10),addr varchar2(50),dob date(15),qual varchar(30),gend varchar2(20),technical varchar2(15), password varchar2(15))");

           // Toast.makeText(getApplicationContext(),"Table created successfully", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex) {

        }



        btnmove=(Button)findViewById(R.id.button3);
        Text2=(EditText)findViewById(R.id.editText2);
        Text3=(EditText)findViewById(R.id.editText3);
        Text4=(EditText)findViewById(R.id.editText4);
        Text6=(EditText)findViewById(R.id.editText6);
        Text7=(EditText)findViewById(R.id.editText7);
        Text8=(EditText)findViewById(R.id.txtadd);
        Text9=(EditText)findViewById(R.id.txtdob);
        Text10=(EditText)findViewById(R.id.txtqln);

        male=(android.widget.RadioButton)findViewById(R.id.rdm);
        female=(android.widget.RadioButton)findViewById(R.id.rdf);

        btnmove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    String fname=Text2.getText().toString();
                    String mobno=Text3.getText().toString();
                    String emi=Text4.getText().toString();
                    String ts=technical.getSelectedItem().toString();
                    String add=Text8.getText().toString();
                    String dob=Text9.getText().toString();
                    String qual=Text10.getText().toString();
                    String pass=Text6.getText().toString();
                    String cpass=Text7.getText().toString();

                    Text2.requestFocus();

                    if(fname.isEmpty()|| mobno.isEmpty()||emi.isEmpty()||ts.isEmpty()||add.isEmpty()||dob.isEmpty()||qual.isEmpty()|| pass.isEmpty()||cpass.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Input must not be empty..", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        if(cpass.equals(pass)) {

                            if(!male.isChecked() && !female.isChecked())
                    {
                        Toast.makeText(getApplicationContext(), "Pls Select Your Gender", Toast.LENGTH_LONG).show();
                    }

                    if(male.isChecked()){

                        gend="Male";
                    }
                    if(female.isChecked()){

                        gend="Female";
                    }
                            db.execSQL("insert into register(firstname,lastname,email,technical,addr,dob,qual,gend,password) values('"+fname +"','" + mobno + "','" + emi + "','" + ts + "','"+ add +"','" + dob + "','" + qual + "','" + gend + "','" + pass + "') ");
                            Toast.makeText(getApplicationContext(), "Records Saved Successfully....", Toast.LENGTH_LONG).show();
                            Intent r = new Intent(RegisterActivity.this, userloginActivity.class);
                            r.putExtra("fname", fname);
                            r.putExtra("taskID", fname);
                            //r.putExtra("tech", ts);
                            // r.putExtra("mobno", );
                            startActivity(r);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Password not matched", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
                }
            }
        });

        regcolor=(ScrollView) findViewById(R.id.regcolor);

        anim=(AnimationDrawable)regcolor.getBackground();
        anim.setEnterFadeDuration(2000);
        anim.setExitFadeDuration(2000);


    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (anim!=null && !anim.isRunning())
            anim.start();
    }


    @Override
    protected void onPause() {
        super.onPause();

        if (anim!=null && anim.isRunning())
            anim.stop();


    }
}
