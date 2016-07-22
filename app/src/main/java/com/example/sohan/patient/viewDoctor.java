package com.example.sohan.patient;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.List;

public class viewDoctor extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    myContactAdapter MyContactAdapter;
    ListView listView;
    String jsonResult;
    JSONObject jsonObject;
    JSONArray jsonArray;
    String selectedDepartment;
    String JSON_String;
    TextView display;
    String displaymessage;
    List<Contacts> newList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doctor);
        display = (TextView)findViewById(R.id.textView4);
        Bundle bundle = getIntent().getExtras();
        displaymessage = bundle.getString(Doctors_layout.Message);
        display.setText(displaymessage);
        MyContactAdapter = new myContactAdapter(getApplicationContext(), R.layout.appointment);
        spinner = (Spinner)findViewById(R.id.spinner2);
        listView = (ListView)findViewById(R.id.listView2);
        listView.setAdapter(MyContactAdapter);
        spinner.setOnItemSelectedListener(this);
        addSpinnerItem();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextSize(21);
        if (position == 0) {
            nothing();
        } else {
            selectedDepartment = parent.getItemAtPosition(position).toString();
            retrieveInfo ri = new retrieveInfo();
            Toast.makeText(getApplicationContext(), displaymessage+selectedDepartment, Toast.LENGTH_SHORT).show();
            ri.execute(displaymessage,selectedDepartment);
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
            myUrl ="http://bdpricelist.com/patient/retrieveAppointment.php";    // change php script
        }


        protected String doInBackground(String... args) {
            String hospitalName, department;
            String result = null;
            hospitalName = args[0];
            department = args[1];
            JSONArray jsonArray = null;
            try{
                URL url = new URL(myUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                System.out.print(hospitalName+" "+department);
                String data_to_send = URLEncoder.encode("hospitalName", "UTF-8")+"="+URLEncoder.encode(hospitalName,"UTF-8")+"&"+
                        URLEncoder.encode("department", "UTF-8")+"="+URLEncoder.encode(department,"UTF-8");
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
            jsonArray = jsonObject.getJSONArray("appointment");
            int count = 0;
            String name,visitingHours,roomNumber;
            newList = new ArrayList<>();
            while (count < jsonArray.length()) {
                JSONObject jo = jsonArray.getJSONObject(count);
                name = jo.getString("DoctorName");                          // data's are send to store in and print in listview
                visitingHours = jo.getString("VisitingHours");
                roomNumber = jo.getString("RoomNumber");
                contacts = new Contacts(name,visitingHours,roomNumber);
                newList.add(contacts);                                   // data are stored in the newlist array
                count++;
            }

            MyContactAdapter.add(newList);                               // the newlist array are send to add in the listview

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSpinnerItem(){
        if(displaymessage.equals("United Hospital")){
            List<String> deparment = new ArrayList<String>();
            deparment.add("Choose a department");
            deparment.add("Cardiology");
            deparment.add("Gynaecology");
            ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_xml ,deparment);
            aAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
            spinner.setAdapter(aAdapter);
        }else if(displaymessage.equals("Apollo Hospital")){
            List<String> deparment = new ArrayList<String>();
            deparment.add("Choose a department");
            deparment.add("ENT");
            ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_xml ,deparment);
            aAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
            spinner.setAdapter(aAdapter);
        }else if(displaymessage.equals("Lab Aid")){
            List<String> deparment = new ArrayList<String>();
            deparment.add("Choose a department");
            deparment.add("Orthopaedics");
            ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_xml ,deparment);
            aAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
            spinner.setAdapter(aAdapter);
        }else if(displaymessage.equals("Metropolitan")){
            List<String> deparment = new ArrayList<String>();
            deparment.add("Choose a department");
            deparment.add("Neurology");
            ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_xml ,deparment);
            aAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
            spinner.setAdapter(aAdapter);
        }else if(displaymessage.equals("CSCR")){
            List<String> deparment = new ArrayList<String>();
            deparment.add("Choose a department");
            deparment.add("Cardiology");
            ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_xml ,deparment);
            aAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
            spinner.setAdapter(aAdapter);
        }else if(displaymessage.equals("Chevron")){
            List<String> deparment = new ArrayList<String>();
            deparment.add("Choose a department");
            deparment.add("Orthodontics");
            ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_xml ,deparment);
            aAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
            spinner.setAdapter(aAdapter);
        }else if(displaymessage.equals("Metropolitan")){
            List<String> deparment = new ArrayList<String>();
            deparment.add("Choose a department");
            deparment.add("Neurology");
            ArrayAdapter<String> aAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_xml ,deparment);
            aAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
            spinner.setAdapter(aAdapter);
        }
    }
}
