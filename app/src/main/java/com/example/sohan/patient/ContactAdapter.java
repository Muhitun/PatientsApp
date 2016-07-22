package com.example.sohan.patient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sohan on 6/9/2016.
 */
public class ContactAdapter extends ArrayAdapter {
    List list = new ArrayList();
    View row;
    ContactHolder contactHolder;
    public ContactAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(List<Contacts> updatedList) {
        list.clear();
        list.addAll(updatedList);
        notifyDataSetChanged();
    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        row = convertView;

        if(row==null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            contactHolder = new ContactHolder();
            contactHolder.MedicalName =(TextView) row.findViewById(R.id.textView5);
            row.setTag(contactHolder);
        }
        else{

            contactHolder = (ContactHolder)row.getTag();
        }



        Contacts contacts = (Contacts)this.getItem(position);
        contactHolder.MedicalName.setText(contacts.getMedicalName());
        return row;



    }

    static class ContactHolder{
        TextView MedicalName;
    }

}
