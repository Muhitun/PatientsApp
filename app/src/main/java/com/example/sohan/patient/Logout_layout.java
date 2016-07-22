package com.example.sohan.patient;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Sohan on 5/20/2016.
 */
public class Logout_layout extends Fragment {
 public static String message = "Sohan";
    Context c;
    View myView;



    public Logout_layout(Context context){

        c = context;
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SignIn sn = new SignIn();
        sn.setID();
        Intent i = new Intent(c, Welcome.class);
        startActivity(i);
        return null;
    }


}
