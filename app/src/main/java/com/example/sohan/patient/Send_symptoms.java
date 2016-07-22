package com.example.sohan.patient;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
public class Send_symptoms extends Fragment {

    View myView;
    EditText symptom;
    Button submit;
    String id;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.send_symptoms, container, false);
        SignIn sn = new SignIn();
        String received=sn.getID();
        id = received;
        symptom = (EditText)myView.findViewById(R.id.editText15);
        submit = (Button)myView.findViewById(R.id.button6);
        myView.findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Symptom = symptom.getText().toString();
                sendSymptoms sn = new sendSymptoms();
                sn.execute(Symptom, id);
            }
        });
        return myView;
    }

    class sendSymptoms extends AsyncTask<String, Void, String> {             // send data to server

        String myUrl;



        protected void onPreExecute() {
            myUrl ="http://bdpricelist.com/patient/sendSymptom.php";
        }


        protected String doInBackground(String... args) {
            String symptom, id;
            String result = null;
            symptom = args[0];
            id = args[1];
            try{
                URL url = new URL(myUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_to_send = URLEncoder.encode("symptom","UTF-8")+"="+URLEncoder.encode(symptom,"UTF-8")+"&"+
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
            Toast.makeText(getActivity().getApplicationContext(), "Symptom is added", Toast.LENGTH_LONG).show();
        }
    }

}
