package com.example.sohan.patient;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

/**
 * Created by Sohan on 5/20/2016.
 */
public class Edit_info extends Fragment {

    View myView;
    String id;
    EditText name,age,height,weight,password;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.edit_info, container, false);
        SignIn sn = new SignIn();
        String receivedID = sn.getID();
        id = receivedID;
        name = (EditText)myView.findViewById(R.id.editText8);
        age = (EditText)myView.findViewById(R.id.editText9);       //  referencing edit text
        height = (EditText)myView.findViewById(R.id.editText10);
        weight = (EditText)myView.findViewById(R.id.editText11);
        password = (EditText)myView.findViewById(R.id.editText12);


        myView.findViewById(R.id.button7).setOnClickListener(new View.OnClickListener(){   // update name
            public void onClick(View v) {
                String Name = name.getText().toString();
                updateName un = new updateName();
                un.execute(Name, id);
            }
        });

        myView.findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Age = age.getText().toString();
                updateAge ua = new updateAge();
                ua.execute(Age, id);
            }
        });

        myView.findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Height = height.getText().toString();
                updateHeight uh = new updateHeight();
                uh.execute(Height, id);
            }
        });

        myView.findViewById(R.id.button9).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Weight = weight.getText().toString();
                updateWeight uh = new updateWeight();
                uh.execute(Weight, id);
            }
        });

        myView.findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Password = password.getText().toString();
                updatePassword uh = new updatePassword();
                uh.execute(Password, id);
            }
        });

        myView.findViewById(R.id.button11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = name.getText().toString();
                String Age = age.getText().toString();
                String Height = height.getText().toString();
                String Weight = weight.getText().toString();
                String Password = password.getText().toString();
                updateAll uall = new updateAll();
                uall.execute(Name, Age, Height, Weight, Password, id);
            }
        });


        return myView;
    }

    class updateName extends AsyncTask<String, Void, String> {             // send data to server

        String myUrl;



        protected void onPreExecute() {
            myUrl ="http://bdpricelist.com/patient/updateName.php";
        }


        protected String doInBackground(String... args) {
            String name, id;
            String result = null;
            name = args[0];                                                                       // update Name
            id = args[1];
            try{
                URL url = new URL(myUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_to_send = URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
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
            Toast.makeText(getActivity().getApplicationContext(), "Name is updated", Toast.LENGTH_SHORT).show();
        }
    }

    class updateAge extends AsyncTask<String, Void, String> {             // send data to server

        String myUrl;



        protected void onPreExecute() {
            myUrl ="http://bdpricelist.com/patient/updateAge.php";                                   // update age
        }


        protected String doInBackground(String... args) {
            String age, id;
            String result = null;
            age = args[0];
            id = args[1];
            try{
                URL url = new URL(myUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_to_send = URLEncoder.encode("age","UTF-8")+"="+URLEncoder.encode(age,"UTF-8")+"&"+
                        URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
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
            Toast.makeText(getActivity().getApplicationContext(), "Age is updated", Toast.LENGTH_LONG).show();
        }
    }

    class updateHeight extends AsyncTask<String, Void, String> {             // send data to server

        String myUrl;


                                                                                                        // update height
        protected void onPreExecute() {
            myUrl ="http://bdpricelist.com/patient/updateHeight.php";
        }


        protected String doInBackground(String... args) {
            String height, id;
            String result = null;
            height = args[0];
            id = args[1];
            try{
                URL url = new URL(myUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_to_send = URLEncoder.encode("height","UTF-8")+"="+URLEncoder.encode(height,"UTF-8")+"&"+
                        URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
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
            Toast.makeText(getActivity().getApplicationContext(), "Height is updated", Toast.LENGTH_LONG).show();
        }
    }

    class updateWeight extends AsyncTask<String, Void, String> {             // send data to server

        String myUrl;


        // update height
        protected void onPreExecute() {
            myUrl ="http://bdpricelist.com/patient/updateWeight.php";
        }


        protected String doInBackground(String... args) {
            String weight, id;
            String result = null;                                                                      // update Weight
            weight = args[0];
            id = args[1];
            try{
                URL url = new URL(myUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_to_send = URLEncoder.encode("weight","UTF-8")+"="+URLEncoder.encode(weight,"UTF-8")+"&"+
                        URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
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
            Toast.makeText(getActivity().getApplicationContext(), "Weight is updated", Toast.LENGTH_LONG).show();
        }
    }

    class updatePassword extends AsyncTask<String, Void, String> {             // send data to server

        String myUrl;


        // update height
        protected void onPreExecute() {
            myUrl ="http://bdpricelist.com/patient/updatePassword.php";
        }


        protected String doInBackground(String... args) {
            String password, id;
            String result = null;
            password = args[0];
            id = args[1];
            try{
                URL url = new URL(myUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_to_send = URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+
                        URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
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
            Toast.makeText(getActivity().getApplicationContext(), "Password is updated", Toast.LENGTH_LONG).show();
        }
    }

    class updateAll extends AsyncTask<String, Void, String> {             // send data to server

        String myUrl;


        // update height
        protected void onPreExecute() {
            myUrl ="http://bdpricelist.com/patient/updateAll.php";
        }


        protected String doInBackground(String... args) {
            String name, age, height, weight, password, id;
            String result = null;
            name = args[0];
            age = args[1];
            height = args[2];
            weight = args[3];
            password = args[4];
            id = args[5];
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
                        URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
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
            Toast.makeText(getActivity().getApplicationContext(), "All details updated", Toast.LENGTH_LONG).show();
        }
    }

}
