package com.example.purvesh.employee;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class userloginActivity extends AppCompatActivity {
    TextView signup;
    TextView txtmove;
    Button btnLogin;
    EditText txt1,txt2;
    SQLiteDatabase db=null;
    LinearLayout container;
    AnimationDrawable anim;

   // boolean flag=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        txtmove=(TextView)findViewById(R.id.fp);
        txtmove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(userloginActivity.this,forgotActivity.class);
                startActivity(i);
            }
        });
        
        signup = (TextView) findViewById(R.id.txtsign);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(userloginActivity.this, RegisterActivity.class);
                startActivity(s);
            }
        });

        try
        {
            db= getApplicationContext().openOrCreateDatabase("Employeemanage",MODE_PRIVATE, null);


           // Toast.makeText(getApplicationContext(),"Connection is successfull...", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex) {
        //    Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
        }


        btnLogin=(Button)findViewById(R.id.btnlogin);
        txt1=(EditText)findViewById(R.id.name);
        txt2=(EditText)findViewById(R.id.pass);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                try{

                    String uname=txt1.getText().toString();
                    String pass=txt2.getText().toString();
                    Cursor c=db.rawQuery("select regid, email, password,technical from register",null);

                    if(c.getCount()==0){

                        Toast.makeText(getApplication(),"No Records Found",Toast.LENGTH_LONG).show();
                    }


                    while(c.moveToNext()){
                        int rid = Integer.parseInt(c.getString(0));
                            String em=c.getString(1);
                            String ps=c.getString(2);
                        String tec=c.getString(3);

                        if (uname.isEmpty() && pass.isEmpty())
                        {
                            // flag=false;
                            txt1.setError("Enter Username");
                            txt1.requestFocus();
                            txt2.setError("Enter Password");

                            //Toast.makeText(getApplicationContext(), "Input Must Not be empty", Toast.LENGTH_SHORT).show();
                        }
                        else if (uname.equals(em) && pass.equals(ps)) {

                           // flag = true;
                            Intent myint = new Intent(userloginActivity.this, employeemanageActivity.class);

                             myint.putExtra("user", em);
                             myint.putExtra("rid",rid);
                             myint.putExtra("pass",ps);
                            myint.putExtra("tecal",tec);

                            startActivity(myint);
                            finish();
                        }/*
                         else
                        {
                            Toast.makeText(getApplicationContext(), "Username or Password is incorrect",Toast.LENGTH_SHORT).show();
                        }*/

                    }


                }
                catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });

        container=(LinearLayout)findViewById(R.id.container);

        anim=(AnimationDrawable)container.getBackground();
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
