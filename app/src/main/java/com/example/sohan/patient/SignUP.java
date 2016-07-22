package com.example.sohan.patient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class SignUP extends AppCompatActivity {


    EditText name;
    EditText age;
    EditText height;
    EditText weight;
    EditText password;
    ProgressDialog progressDialog;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = (EditText)findViewById(R.id.editText);
        age = (EditText)findViewById(R.id.editText2);
        height = (EditText)findViewById(R.id.editText3);
        weight = (EditText)findViewById(R.id.editText4);
        password = (EditText)findViewById(R.id.editText5);
    }

    public void back(View v){
        String Name = name.getText().toString();
        String Password = password.getText().toString();
        //checkUserName(Name, Password);                         // checking whether name and password already exist

        SignUpRequest sur = new SignUpRequest();
//        sur.execute(Name, Password);  //while checking name
        sur.execute(Password);         // while checking password
        //confirmed(Name, Age, Height, Weight, Password);        // finally sending to server after proper validation

    }

    //public void checkUserName(String naame, String passsword){           // check already exist or not

        class SignUpRequest extends AsyncTask<String, Void, String>{



            String myUrl;
            protected void onPreExecute() {
                myUrl = "http://bdpricelist.com/patient/checkUserName.php";

            }

            @Override
            protected String doInBackground(String... args) {
                String name, password;
                String result=null;
                //name = args[0];
                password = args[0];
                try{
                    URL url = new URL(myUrl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//                    String data_to_send = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+ // checking name
//                            URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                    String data_to_send =  URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8"); // checking password

                    bufferedWriter.write(data_to_send);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream is = httpURLConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    // int intch;

                    while ((line = reader.readLine()) != null)   //line = reader.readLine() !=null
                    {
                        //    char ch = (char) intch;
                        //  String s = new String(Character.toString(ch).getBytes(), "UTF-8");
                        //sb.append(s);
                        sb.append(line + "\n");
                        break;
                    }
                    is.close();
                    result = sb.toString();
                }catch(MalformedURLException e){
                    e.printStackTrace();
                }catch(IOException f){
                    f.printStackTrace();
                }
                return result;
            }


            protected void onPostExecute(String result) {
                String s = result.trim();
                if(s.equalsIgnoreCase("success")){
//                    Intent i = new Intent(SignUP.this, SignUP.class);
//                    startActivity(i);
                    Toast.makeText(getApplicationContext(), "Sorry password already exist, Enter a new one", Toast.LENGTH_LONG).show();
                    password.setText("");
                }else {
                    //Toast.makeText(getApplicationContext(), "Failure from checkUserName", Toast.LENGTH_LONG).show();
                    String Name = name.getText().toString();
                    String Age = age.getText().toString();
                    String Height = height.getText().toString();
                    String Weight = weight.getText().toString();
                    String Password = password.getText().toString();

                    //confirmed(Name, Age, Height, Weight, Password);
                    SignUpConfirmed sc = new SignUpConfirmed();             // After proper validation data is uploaded to server
                    sc.execute(Name, Age, Height, Weight, Password);
                }
            }
        }
//        SignUpRequest sur = new SignUpRequest();
//        sur.execute(naame, passsword);

    //}

    //public void confirmed(String naame, String aage, String heightt, String weightt, String passwordd) {
//        SignUpConfirmed sc = new SignUpConfirmed();             // After proper validation data is uploaded to server
//        sc.execute(naame, aage, heightt, weightt, passwordd);

    //}

    class SignUpConfirmed extends AsyncTask<String, Void, String> {             // send data to server

          String myUrl;



        protected void onPreExecute() {
            myUrl ="http://bdpricelist.com/patient/signUp.php";
        }


        protected String doInBackground(String... args) {
            String name, age, height, weight, password;
            String result = null;
            String check = "0";
            String status = "0";
            name = args[0];
            age = args[1];
            height = args[2];
            weight = args[3];
            password = args[4];
            try{
                URL url = new URL(myUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_to_send = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("age","UTF-8")+"="+URLEncoder.encode(age,"UTF-8")+"&"+
                        URLEncoder.encode("height","UTF-8")+"="+URLEncoder.encode(height,"UTF-8")+"&"+
                        URLEncoder.encode("weight","UTF-8")+"="+URLEncoder.encode(weight,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+
                        URLEncoder.encode("check","UTF-8")+"="+URLEncoder.encode(check,"UTF-8")+"&"+
                        URLEncoder.encode("status","UTF-8")+"="+URLEncoder.encode(status,"UTF-8");
                bufferedWriter.write(data_to_send);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream is = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();

                String line = null;
                // int intch;

                while ((line = reader.readLine()) != null)   //line = reader.readLine() !=null
                {
                    //    char ch = (char) intch;
                    //  String s = new String(Character.toString(ch).getBytes(), "UTF-8");
                    //sb.append(s);
                    sb.append(line + "\n");
                    break;
                }
                is.close();
                result = sb.toString();
            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch(IOException f){
                f.printStackTrace();
            }
            return result;
        }


        protected void onPostExecute(String result) {
            String s = result.trim();
            if (s.equalsIgnoreCase("success")) {
                Toast.makeText(getApplicationContext(), "Sign up successful", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Data uploaded to database", Toast.LENGTH_LONG).show();
                retrieveID rID = new retrieveID();
                rID.execute(password.getText().toString());
                //startActivity(new Intent(SignUP.this, Welcome.class));
            } else {
                Toast.makeText(getApplicationContext(), "Sign up failure", Toast.LENGTH_LONG).show();
            }

        }
    }

    class retrieveID extends AsyncTask<String, Void, String> {             // send data to server

        String myUrl;



        protected void onPreExecute() {
            myUrl ="http://bdpricelist.com/patient/retrieveId.php";
        }


        protected String doInBackground(String... args) {
            String name, age, height, weight, password;
            String result = null;
            password = args[0];
            try{
                URL url = new URL(myUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_to_send = URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(data_to_send);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream is = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();

                String line = null;
                // int intch;

                while ((line = reader.readLine()) != null)   //line = reader.readLine() !=null
                {
                    //    char ch = (char) intch;
                    //  String s = new String(Character.toString(ch).getBytes(), "UTF-8");
                    //sb.append(s);
                    sb.append(line + "\n");
                    break;
                }
                is.close();
                result = sb.toString();
            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch(IOException f){
                f.printStackTrace();
            }
            return result;
        }


        protected void onPostExecute(String result) {
            String s = result.trim();
//            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SignUP.this);
//            SharedPreferences.Editor editor = prefs.edit();
//            editor.putString("string_id", s);
//            editor.commit();
            Intent i = new Intent(SignUP.this, Welcome.class);
            i.putExtra("sohan", s);
            startActivity(i);
        }
    }



}
