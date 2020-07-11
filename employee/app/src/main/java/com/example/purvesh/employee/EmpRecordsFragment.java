package com.example.purvesh.employee;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class EmpRecordsFragment extends android.app.Fragment {
    SQLiteDatabase db=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview=inflater.inflate(R.layout.fragment_emp_records, container, false);

        // return inflater.inflate(R.layout.fragment_emp_records, container, false);
        try {

            db = getActivity().openOrCreateDatabase("Employeemanage", Context.MODE_PRIVATE, null);
           // Toast.makeText(getActivity(), "Connection is successfull.....", Toast.LENGTH_LONG).show();
        }

        catch (Exception e){
          // Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }

                // Retrieving all records
                Cursor c = db.rawQuery("SELECT * FROM tbl_register", null);

                // Checking if no records found
                if (c.getCount() == 0) {
                    showMessage("No Records Found", "No records found");
                }

                // Appending records to a string buffer
                StringBuffer buffer = new StringBuffer();

                while (c.moveToNext()) {
                    //buffer.append("Auto Id: "+c.getString(0)+"\n");
                    buffer.append("regid"+"       -->\t\t "+ c.getString(0) + "\n\n");
                    buffer.append("firstname"+"   --> "+ c.getString(1) + "\n\n");
                    buffer.append("mobile"+"    --> "+ c.getString(2) + "\n\n");
                    buffer.append("email"+"       -->\t\t "+ c.getString(3) + "\n\n");
                    buffer.append("project language"+"    -->\t\t "+ c.getString(4) + "\n\n");
                    buffer.append("password"+"    --> "+ c.getString(5) + "\n\n");
                }

                // Displaying all records
                showMessage("Employee Details", buffer.toString());

        return myview;
    }

    public void showMessage(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
