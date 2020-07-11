package com.example.purvesh.employee;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class login extends AppCompatActivity {

    ImageView imgLogo;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       /* imgLogo=(ImageView)findViewById(R.id.imgLogo);
        Animation rotateAnim = AnimationUtils.loadAnimation(this,R.anim.scale);
        imgLogo.startAnimation(rotateAnim);
*/
        Handler hand=new Handler();

        hand.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent home=new Intent(login.this,EmployeeNavigationActivity.class);
                startActivity(home);
                finish();
            }
        },3000);
    }
}
