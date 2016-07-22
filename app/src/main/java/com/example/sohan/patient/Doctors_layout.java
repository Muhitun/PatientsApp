package com.example.sohan.patient;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Sohan on 5/20/2016.
 */
public class Doctors_layout extends Fragment implements AdapterView.OnItemSelectedListener{
    public final static String Message = "Sohan";
    View myView;
    Spinner spinner;
    String selectedCity;
    Context myContext;
    String jsonResult;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String JSON_String;
    ContactAdapter contactAdapter;
    ListView listView;
    List<Contacts> newList;
    Button button;
    String send;
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.doctors_directory, container, false);
        myContext = inflater.getContext();
        contactAdapter = new ContactAdapter(myContext, R.layout.row_layout);
        spinner = (Spinner)myView.findViewById(R.id.spinner);
        listView = (ListView)myView.findViewById(R.id.listView);
        listView.setAdapter(contactAdapter);
        spinner.setOnItemSelectedListener(this);
        final List<String> city = new ArrayList<String>();
        city.add("Choose a City");
        city.add("Chittagong");
        city.add("Dhaka");
        ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(myContext, R.layout.spinner_xml ,city);
        aAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinner.setAdapter(aAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    send = newList.get(position).getMedicalName();
                    Intent intent = new Intent(myContext, viewDoctor.class);
                    intent.putExtra(Message, send);
                    startActivity(intent);
            }
        });
        return myView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ((TextView) parent.getChildAt(0)).setTextSize(21);
            if (position == 0) {
                nothing();
            } else {
                selectedCity = parent.getItemAtPosition(position).toString();
                retrieveInfo ri = new retrieveInfo();
                ri.execute(selectedCity);
            }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void nothing(){
    }




    class retrieveInfo extends AsyncTask<String, Void, String> {             // send data to server

        String myUrl;


        protected void onPreExecute() {
            myUrl ="http://bdpricelist.com/patient/retrieveMedicalName.php";    // change php script
        }


        protected String doInBackground(String... args) {
            String city;
            String result = null;
            city = args[0];
            JSONArray jsonArray = null;
            try{
                URL url = new URL(myUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data_to_send = URLEncoder.encode("city", "UTF-8")+"="+URLEncoder.encode(city,"UTF-8");
                bufferedWriter.write(data_to_send);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream is = httpURLConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();


                while ((JSON_String = reader.readLine()) != null)
                {
                    sb.append(JSON_String+"\n");
                }
                reader.close();
                httpURLConnection.disconnect();
                is.close();
                return sb.toString().trim();
            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch(IOException f){
                f.printStackTrace();
            }
            return null;
        }


        protected void onPostExecute(String result) {

            jsonResult = result;
            parseJSON(jsonResult);
        }
    }


    public void parseJSON(String json){
        Contacts contacts=null;
            try {
                jsonObject = new JSONObject(json);
                jsonArray = jsonObject.getJSONArray("patient");
                int count = 0;
                String name;
                newList = new ArrayList<Contacts>();
                while (count < jsonArray.length()) {
                    JSONObject jo = jsonArray.getJSONObject(count);
                    name = jo.getString("Medical");                          // data's are send to store in and print in listview
                    contacts = new Contacts(name);
                    newList.add(contacts);                                   // data are stored in the newlist array
                    count++;
                }

                contactAdapter.add(newList);                               // the newlist array are send to add in the listview

            } catch (Exception e) {
                e.printStackTrace();
            }
    }


}
