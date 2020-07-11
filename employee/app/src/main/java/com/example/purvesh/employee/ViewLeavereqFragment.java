package com.example.purvesh.employee;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class ViewLeavereqFragment extends android.app.Fragment {
    SQLiteDatabase db;
    TextView txt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview= inflater.inflate(R.layout.fragment_view_leavereq, container, false);

        String le= getActivity().getIntent().getExtras().getString("user").toString();
        Toast.makeText(getActivity(), le, Toast.LENGTH_LONG).show();

        txt=(TextView)myview.findViewById(R.id.view);

        try {

            db = getActivity().openOrCreateDatabase("Employeemanage", Context.MODE_PRIVATE, null);


        }
        catch (Exception e){
        //    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }

        Cursor c = db.rawQuery("SELECT leave FROM leavemng ", null);


        if (c.getCount() == 0) {

            Toast.makeText(getActivity(),"No records found",Toast.LENGTH_LONG).show();
        }

        while (c.moveToNext()) {
            String s = c.getString(0);
            //String s1=c.getString(1);

            txt.setText(s);

        }


        return myview;
    }
}
