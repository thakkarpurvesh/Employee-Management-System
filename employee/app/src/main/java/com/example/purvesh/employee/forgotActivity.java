package com.example.purvesh.employee;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class forgotActivity extends AppCompatActivity {

    EditText txt1,txt2;
    String phoneNo;
    Button btnOtp,btnback;
    SQLiteDatabase db = null;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);


        btnOtp = (Button) findViewById(R.id.btnFP);
        txt1 = (EditText) findViewById(R.id.editText);

        btnback=(Button)findViewById(R.id.btnlog);

        try {
            db = openOrCreateDatabase("Employeemanage", MODE_PRIVATE, null);
           // Toast.makeText(getApplication(), "Connection is successfull.....", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
          //  Toast.makeText(getApplication(), e.toString(), Toast.LENGTH_LONG).show();
        }

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g=new Intent(forgotActivity.this,userloginActivity.class);
                startActivity(g);
            }
        });

        btnOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String phoneNo = txt1.getText().toString();

                    Cursor c = db.rawQuery("select lastname,password from register", null);

                    if (c.getCount() == 0) {

                        Toast.makeText(getApplication(), "No Records Found", Toast.LENGTH_LONG).show();
                    }
                    if (phoneNo.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Input Must Not be empty", Toast.LENGTH_LONG).show();
                    }

                    while (c.moveToNext()) {

                        String ph = c.getString(0);
                        String ps = c.getString(1);

                        if (phoneNo.equals(ph)) {

                          //  Toast.makeText(getApplicationContext(), "Your Password:"+ps, Toast.LENGTH_LONG).show();
                            try {
                                Toast.makeText(getApplicationContext(), "SMS Sent!...", Toast.LENGTH_LONG).show();
                                SmsManager smsManager = SmsManager.getDefault();
                                smsManager.sendTextMessage(phoneNo, null, "Your Password --> "+ps, null, null);

                            }
                            catch (Exception ex){
                                Toast.makeText(getApplicationContext(),ex.toString(), Toast.LENGTH_LONG).show();
                            }

                        }
                    }

                }
                catch (Exception ex) {}

            }
        });
    }
}
