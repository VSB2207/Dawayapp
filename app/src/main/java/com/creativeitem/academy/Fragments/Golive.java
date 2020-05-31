package com.creativeitem.academy.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.creativeitem.academy.Activities.PopUpActivity;
import com.creativeitem.academy.Activities.SignInActivity;
import com.creativeitem.academy.R;
import com.creativeitem.academy.Utils.Helpers;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;

public class Golive extends Fragment {
    private static final String TAG = "MyCourseFragment";
    GridView myCoursesGridLayout;
    private ProgressBar progressBar;
    Button signInButton;
    RelativeLayout Yougolive;
    RelativeLayout signInPlaceholder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_golive, container, false);
        init(view);
//        initProgressBar(view);
        if (isLoggedIn()) {
            this.loggedInView();
        } else {
            this.loggedOutView();
        }
        return view;
    }

    private void loggedOutView() {
            signInPlaceholder.setVisibility(View.VISIBLE);
        Yougolive.setVisibility(View.GONE);
    }

    private void loggedInView() {
           // fetching all of the my courses
        signInPlaceholder.setVisibility(View.GONE);
        Yougolive.setVisibility(View.VISIBLE);
    }

    private boolean isLoggedIn() {
        SharedPreferences preferences = getContext().getSharedPreferences(Helpers.SHARED_PREF, 0);
        int userValidity = preferences.getInt("userValidity", 0);
        if (userValidity == 1) {
            return true;
        }else{
            return false;
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, isLoggedIn() + "");
        if (isLoggedIn()) {
            this.loggedInView();
        } else {
            this.loggedOutView();
        }
    }

    private void init(View view) {
        myCoursesGridLayout = view.findViewById(R.id.myCoursesGridLayout);
        Yougolive = view.findViewById(R.id.yougolive);
        signInPlaceholder = view.findViewById(R.id.signInPlaceHolder);
        signInButton = view.findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                startActivity(intent);
            }
        });
    }

//    private void initProgressBar(View view) {
//        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
//        Sprite circularLoading = new Circle();
//        progressBar.setIndeterminateDrawable(circularLoading);
//
//    }
}