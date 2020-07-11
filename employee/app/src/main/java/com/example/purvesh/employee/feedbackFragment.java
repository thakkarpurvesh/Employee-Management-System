package com.example.purvesh.employee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;


public class feedbackFragment extends android.app.Fragment {

    RatingBar ratingbar1;
    Button ratingbtn;
    EditText txt1,txt2,txt3;
    SQLiteDatabase db=null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View myview =inflater.inflate(R.layout.fragment_feedback, container, false);

        try
        {
            db=getActivity().openOrCreateDatabase("Employeemanage", Context.MODE_PRIVATE,null);
            db.execSQL("create table feedback (regid integer primary key autoincrement,name varchar(15),email varchar2(15),txtfeedback varchar2(50),ratingstar varchar2(10))");

          //  Toast.makeText(getActivity(), "Connection is successfull.....", Toast.LENGTH_SHORT).show();


        }
        catch (Exception e){
          //  Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }


        txt1=(EditText)myview.findViewById(R.id.txtname);
        txt2=(EditText)myview.findViewById(R.id.txtmail);
        txt3=(EditText)myview.findViewById(R.id.yourmess);
        ratingbar1=(RatingBar)myview.findViewById(R.id.rating);
        ratingbtn=(Button)myview.findViewById(R.id.btnrating);

       /* String rating=String.valueOf(ratingbar1.getRating());
        Toast.makeText(getActivity(), rating, Toast.LENGTH_LONG).show();
*/

        ratingbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            try{

                String name=txt1.getText().toString();
                String email=txt2.getText().toString();
                String txtfeed=txt3.getText().toString();
                String rating=String.valueOf(ratingbar1.getRating());
            //    int ratin=ratingbar1.getNumStars();
                if(name.isEmpty()||email.isEmpty()||txtfeed.isEmpty()){

                    Toast.makeText(getActivity(), "Input must not be empty..", Toast.LENGTH_SHORT).show();
                }


                else
                {
                        db.execSQL("insert into feedback (name,email,txtfeedback,ratingstar ) values('" + name + "','" + email + "','" + txtfeed + "','" + rating + "') ");
                        Toast.makeText(getActivity(), "Records Saved Successfully....", Toast.LENGTH_SHORT).show();


                }

            }
            catch (Exception e)
            {
                Toast.makeText(getActivity(), "Feedback error", Toast.LENGTH_SHORT).show();
            }


        }
    });


        return myview;
    }

}
