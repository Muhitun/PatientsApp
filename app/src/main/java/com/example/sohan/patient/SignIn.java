package com.example.sohan.patient;

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

public class SignIn extends AppCompatActivity {
    public static String message = "Sohan";
    public static String ID;
    EditText password, name;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        name = (EditText)findViewById(R.id.editText6);
        password = (EditText)findViewById(R.id.editText7);
    }

    public void submit(View v){
        String Name = name.getText().toString();
        String Password=password.getText().toString();
        signIn sn = new signIn();
        sn.execute(Name, Password);
    }

    public String getID(){
        return ID;
    }

    public void setID(){
        ID = "";
    }

    class signIn extends AsyncTask<String, Void, String> {             // send data to server

        String myUrl;



        protected void onPreExecute() {
            myUrl ="http://bdpricelist.com/patient/login.php";
        }


        protected String doInBackground(String... args) {
            String name, age, height, weight, password;
            String result = null;
            String check = "0";
            String status = "0";
            name = args[0];
            password = args[1];
            try{
                URL url = new URL(myUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_to_send = URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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
                Toast.makeText(getApplicationContext(), "Sign in successful", Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Welcome " + name.getText() , Toast.LENGTH_LONG).show();
                retrieveID rid = new retrieveID();
                rid.execute(password.getText().toString());
                Intent i = new Intent(SignIn.this, MainActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(), "Sorry wrong username or password", Toast.LENGTH_LONG).show();
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
            ID = s;
            Toast.makeText(getApplicationContext(), "Your id is : " + ID, Toast.LENGTH_LONG).show();
        }
    }

}
