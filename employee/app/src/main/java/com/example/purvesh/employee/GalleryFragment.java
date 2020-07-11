package com.example.purvesh.employee;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class GalleryFragment extends android.app.Fragment {


    ImageView slider;
    int[] imageArray = {


            R.drawable.galone,
            R.drawable.galtwo,
            R.drawable.galthree,
            R.drawable.galfour,
            R.drawable.galfive,
            R.drawable.galsix,
            R.drawable.galseven,
            R.drawable.galeight,
            R.drawable.galnine,
            R.drawable.galten};

 
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);



        slider=(ImageView)rootView.findViewById(R.id.slider);
        final Handler handler = new Handler();

        final Runnable runnable = new Runnable() {

            int i = 0;
            public void run() {

                slider.setImageResource(imageArray[i]);
                i++;

                if(i > imageArray.length - 1) {
                    i = 0;
                }
                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(runnable, 2000);




return rootView;
    }



}