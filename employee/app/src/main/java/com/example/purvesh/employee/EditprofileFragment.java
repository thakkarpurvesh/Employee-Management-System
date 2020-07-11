package com.example.purvesh.employee;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;


public class EditprofileFragment extends android.app.Fragment {
    ImageView imageview;
    Button btnImage;
    EditText un, fn, ln;

    Button upbtn;
    SQLiteDatabase db = null;
    TextView txtuser1;
    private String imagepath = null;
    private ProgressDialog dialog = null;
    private int serverResponseCode = 0;
    private String upLoadServerUri = null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View myview = inflater.inflate(R.layout.fragment_editprofile, container, false);

        // return inflater.inflate(R.layout.fragment_editprofile, container, false);

        txtuser1 = (TextView) myview.findViewById(R.id.txtshow);

        String user = getActivity().getIntent().getExtras().getString("user").toString();
        txtuser1.setText("Welcome" + " " + user);


        final String myuser = getActivity().getIntent().getExtras().getString("user").toString();


        fn = (EditText) myview.findViewById(R.id.fname);
        ln = (EditText) myview.findViewById(R.id.lname);
        un = (EditText) myview.findViewById(R.id.uname);


        try {
            db = getActivity().openOrCreateDatabase("Employeemanage", Context.MODE_PRIVATE, null);
            db.execSQL("create table if not exists imagetb (img)");
          //  Toast.makeText(getActivity(), "Connection is successfull.....", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
          //  Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
        }

        try {
            Cursor c = db.rawQuery("select email, firstname, lastname  from register where email='" + myuser + "'", null);

            while (c.moveToNext()) {

                String dun = c.getString(0);
                String dfn = c.getString(1);
                String dln = c.getString(2);

                un.setText(String.valueOf(dun));
                fn.setText(String.valueOf(dfn));
                ln.setText(String.valueOf(dln));
            }
        }

        catch (Exception ex) {
          //  Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG).show();
        }

        upbtn=(Button)myview.findViewById(R.id.ebtn);

        upbtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                try
                {
                    db.execSQL("update register set email='"+un.getText().toString()+"',firstname='"+fn.getText().toString()+"',lastname='"+ln.getText().toString()+"' where email='" + myuser + "'");
                    Toast.makeText(getActivity(), "Profile Updated Successfully", Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                   // Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });


        //image upload code

        imageview = (ImageView) myview.findViewById(R.id.imgUpload);
        btnImage = (Button) myview.findViewById(R.id.btnUploadImg);

        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), 1);
            }
        });

        btnImage.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                //dialog = ProgressDialog.show(MainActivity.this, "", "Uploading file...",true);
                //messageText.setText("uploading started.....");

                Toast.makeText(getActivity(), "Uploading started..", Toast.LENGTH_LONG).show();
                new Thread(new Runnable() {

                    public void run() {
                        //  uploadFile(imagepath);
                    }
                }).start();
            }
        });

        return myview;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //imageview=(ImageView)findViewById(R.id.imageView);
       /* try {
            if (requestCode == 1 && resultCode == RESULT_OK) {

                Uri selectedImageUri = data.getData();
                imagepath = getPath(selectedImageUri);
                Bitmap bitmap = BitmapFactory.decodeFile(imagepath);
                imageview.setImageBitmap(bitmap);
                //messageText.setText("Uploading file path:" + imagepath);
                Toast.makeText(getActivity(), "Execute", Toast.LENGTH_LONG).show();
            }
        } catch (Exception ex) {
            Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG).show();
        }*/

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {

                Uri selectedImageUri = data.getData();
                imagepath = getPath(selectedImageUri);

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);

                imageview.setImageBitmap(bitmap);

                // byte[] image= new byte[fis.available()];
                //fis.read(image);
                //fis.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String getPath(Uri uri) {

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public void save(View view) throws IOException {

        Toast.makeText(getActivity(), "Image Save", Toast.LENGTH_SHORT).show();

        FileInputStream fis = new FileInputStream("/storage/sdcard/demoImage.jpg");

        //FileInputStream fis = new FileInputStream("/phone/demoImage.jpg");

        byte[] image = new byte[fis.available()];
        fis.read(image);
        ContentValues values = new ContentValues();
        values.put("a", image);
        db.insert("imageTb", null, values);
        fis.close();
        Toast.makeText(getActivity(), "Done", Toast.LENGTH_SHORT).show();
    }

    public void get(View view) {

        Cursor c = db.rawQuery("select * from imagetb", null);

        if (c.moveToNext()) {
            byte[] image = c.getBlob(0);
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            imageview.setImageBitmap(bmp);
            Toast.makeText(getActivity(), "Done", Toast.LENGTH_SHORT).show();
        }


    }
}

 /*  public int uploadFile(String sourceFileUri) {

       int day, month, year;
       int second, minute, hour;
       GregorianCalendar date = new GregorianCalendar();

       day = date.get(Calendar.DAY_OF_MONTH);
       month = date.get(Calendar.MONTH);
       year = date.get(Calendar.YEAR);

       second = date.get(Calendar.SECOND);
       minute = date.get(Calendar.MINUTE);
       hour = date.get(Calendar.HOUR);

       String name = (hour + "" + minute + "" + second + "" + day + "" + (month + 1) + "" + year);
       String tag = name + ".jpg";
       String fileName = sourceFileUri.replace(sourceFileUri, tag);

       HttpURLConnection conn = null;
       DataOutputStream dos = null;
       String lineEnd = "\r\n";
       String twoHyphens = "--";
       String boundary = "*****";
       int bytesRead, bytesAvailable, bufferSize;
       byte[] buffer;
       int maxBufferSize = 1 * 1024 * 1024;
       File sourceFile = new File(sourceFileUri);

       if (!sourceFile.isFile()) {
           dialog.dismiss();
           Log.e("uploadFile", "Source File not exist :" + imagepath);
           getActivity().runOnUiThread(new Runnable() {
               public void run() {
                   //messageText.setText("Source File not exist :" + imagepath);
                   Toast.makeText(getActivity(), "Source File not exist :" + imagepath, Toast.LENGTH_SHORT).show();
               }
           });
           return 0;
       } else {
           try {

               // open a URL connection to the Servlet
               FileInputStream fileInputStream = new FileInputStream(sourceFile);
               URL url = new URL(upLoadServerUri);

               // Open a HTTP  connection to  the URL
               conn = (HttpURLConnection) url.openConnection();
               conn.setDoInput(true); // Allow Inputs
               conn.setDoOutput(true); // Allow Outputs
               conn.setUseCaches(false); // Don't use a Cached Copy
               conn.setRequestMethod("POST");
               conn.setRequestProperty("Connection", "Keep-Alive");
               conn.setRequestProperty("ENCTYPE", "multipart/form-data");
               conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
               conn.setRequestProperty("uploaded_file", fileName);

               dos = new DataOutputStream(conn.getOutputStream());

               dos.writeBytes(twoHyphens + boundary + lineEnd);
               dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"" + fileName + "\"" + lineEnd);
               dos.writeBytes(lineEnd);

               bytesAvailable = fileInputStream.available();

               bufferSize = Math.min(bytesAvailable, maxBufferSize);
               buffer = new byte[bufferSize];

               bytesRead = fileInputStream.read(buffer, 0, bufferSize);
               Toast.makeText(getActivity(), "Before Whilte.", Toast.LENGTH_SHORT).show();

               while (bytesRead > 0) {

                   dos.write(buffer, 0, bufferSize);
                   bytesAvailable = fileInputStream.available();
                   bufferSize = Math.min(bytesAvailable, maxBufferSize);
                   bytesRead = fileInputStream.read(buffer, 0, bufferSize);
               }

               dos.writeBytes(lineEnd);
               dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

               serverResponseCode = conn.getResponseCode();
               String serverResponseMessage = conn.getResponseMessage();

               Log.i("uploadFile", "HTTP Response is : " + serverResponseMessage + ": " + serverResponseCode);

               if (serverResponseCode == 200) {

                   getActivity().runOnUiThread(new Runnable() {
                       public void run() {
                           String msg = "File Upload Completed.\n\n See uploaded file here : \n\n" + " D:/Android/uploads";
                           //messageText.setText(msg);
                           Toast.makeText(getActivity(), msg + "File Upload Complete.", Toast.LENGTH_SHORT).show();
                       }
                   });
               }
               fileInputStream.close();
               dos.flush();
               dos.close();

           } catch (Exception e) {
               Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
           }
           dialog.dismiss();
           return serverResponseCode;
       }
   }}



        try {
            Cursor c = db.rawQuery("select email, firstname, lastname  from register where email='" + myuser + "'", null);

            while (c.moveToNext()) {

                String dun = c.getString(0);
                String dfn = c.getString(1);
                String dln = c.getString(2);

                un.setText(String.valueOf(dun));
                fn.setText(String.valueOf(dfn));
                ln.setText(String.valueOf(dln));
            }
        }

        catch (Exception ex) {
            Toast.makeText(getActivity(), ex.toString(), Toast.LENGTH_LONG).show();
        }

        upbtn=(Button)myview.findViewById(R.id.ebtn);

        upbtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                try
                {
                    db.execSQL("update register set email='"+un.getText().toString()+"',firstname='"+fn.getText().toString()+"',lastname='"+ln.getText().toString()+"' where email='" + myuser + "'");
                    Toast.makeText(getActivity(), "Profile Updated Successfully", Toast.LENGTH_LONG).show();
                }
                catch (Exception e){
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });


        return myview;

    }

}*/
