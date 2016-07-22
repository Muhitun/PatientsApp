package com.example.sohan.patient;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class Welcome extends AppCompatActivity {

    @Override
    public void onBackPressed() {

    }

    EditText showID;
    String receivedID;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        showID = (EditText)findViewById(R.id.editText13);
        Intent receive = getIntent();
        String id = receive.getStringExtra("sohan");
        showID.setText("Your id is : "+id);
//        SignIn sn = new SignIn();
//        receivedID = sn.getID();
//        showID.setText("Your id is : "+receivedID);
    }

    public void signUp(View view){
        Intent i = new Intent(this, SignUP.class);
        startActivity(i);
    }

    public void signIn(View v){
        Intent i = new Intent(this, SignIn.class);
        startActivity(i);
    }

}
