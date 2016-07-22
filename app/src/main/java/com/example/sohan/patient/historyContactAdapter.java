package com.example.sohan.patient;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sohan on 7/14/2016.
 */
public class historyContactAdapter extends ArrayAdapter<Contact> {

    List<Contact> list = new ArrayList<Contact>();
    View row;
    ContactHolder contactHolder;
    LayoutInflater inflater;
    View v;
    private Activity activity;


    public historyContactAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(List<Contact> updatedList) {
        list.clear();
        list.addAll(updatedList);
        notifyDataSetChanged();
    }


    @Override
    public Contact getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        row = convertView;

        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.history_layout,parent,false);
            contactHolder = new ContactHolder();
            contactHolder.Name =(TextView) row.findViewById(R.id.textView8);
            contactHolder.Age =(TextView) row.findViewById(R.id.textView10);
            contactHolder.Height =(TextView) row.findViewById(R.id.textView11);
            contactHolder.Weight =(TextView) row.findViewById(R.id.textView12);
            contactHolder.Symptom =(TextView) row.findViewById(R.id.textView13);
            contactHolder.Treatment =(TextView) row.findViewById(R.id.textView14);
            contactHolder.doctorName =(TextView) row.findViewById(R.id.textView16);
           // contactHolder.button = (Button) row.findViewById(R.id.button3);

            row.setTag(contactHolder);
        }
        else{

            contactHolder = (ContactHolder)row.getTag();
        }


        Contact contacts = (Contact)this.getItem(position);
        contactHolder.Name.setText("Name: "+contacts.getName());
        contactHolder.Age.setText("Age: "+contacts.getAge());
        contactHolder.Height.setText("Height: "+contacts.getHeight());
        contactHolder.Weight.setText("Weight: "+contacts.getWeight());
        contactHolder.Symptom.setText("Symptoms: " + contacts.getSymptom());
        contactHolder.Treatment.setText("Treatment: " + contacts.getTreatment());
        contactHolder.doctorName.setText("Served by: " + contacts.getdoctorName());

        return row;

    }

    static class ContactHolder{
        TextView Name;
        TextView Age;
        TextView Height;
        TextView Weight;
        TextView Symptom;
        TextView Treatment;
        TextView doctorName;
        Button button;
    }

}
