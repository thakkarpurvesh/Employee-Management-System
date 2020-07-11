package com.example.purvesh.employee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class changepassFragment extends android.app.Fragment {

    EditText op,np,cp;
    Button uppbtn;
    SQLiteDatabase db=null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview=inflater.inflate(R.layout.fragment_changepass, container, false);

        //  return inflater.inflate(R.layout.fragment_changepass, container, false);
        final String myuser=getActivity().getIntent().getExtras().getString("user").toString();
        final String mypass=getActivity().getIntent().getExtras().getString("pass").toString();
        op = (EditText)myview.findViewById(R.id.opass);
        np = (EditText) myview.findViewById(R.id.npass);
        cp = (EditText)myview.findViewById(R.id.cpass);

        try
        {
            db=getActivity().openOrCreateDatabase("Employeemanage", Context.MODE_PRIVATE,null);

          //  Toast.makeText(getActivity(), "Connection is successfull.....", Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
           // Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }
    /*    try {
            Cursor c = db.rawQuery("select   from register where ='" + myuser + "'", null);

            while (c.moveToNext()) {

                String dun = c.getString(0);
                String dfn = c.getString(1);
                String dln = c.getString(2);

                op.setText(String.valueOf(dun));
                np.setText(String.valueOf(dfn));
                cp.setText(String.valueOf(dln));
            }
        }

        catch (Exception ex) {
            Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG).show();
        }
*/
        uppbtn=(Button)myview.findViewById(R.id.cbtn);

        uppbtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                try
                {
                    if(mypass.equals(op.getText().toString())) {
                        db.execSQL("update register set password='" + np.getText().toString() + "' where email='" + myuser + "'");
                        Toast.makeText(getActivity(), "Password Changed Successfully", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getActivity(), "Old Password Not MATCH", Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e){
                  //  Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });


        return myview;

    }
}
