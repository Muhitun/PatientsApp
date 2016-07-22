package com.example.sohan.patient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sohan on 6/24/2016.
 */
public class myContactAdapter extends ArrayAdapter{

    List list = new ArrayList();
    View row;
    ContactHolder contactHolder;
    public myContactAdapter(Context context, int resource) {
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
            row = layoutInflater.inflate(R.layout.appointment,parent,false);
            contactHolder = new ContactHolder();
            contactHolder.doctorName =(TextView) row.findViewById(R.id.textView6);
            contactHolder.appointmentTime =(TextView) row.findViewById(R.id.textView7);
            contactHolder.roomNumber =(TextView) row.findViewById(R.id.textView8);
            row.setTag(contactHolder);
        }
        else{

            contactHolder = (ContactHolder)row.getTag();
        }



        Contacts contacts = (Contacts)this.getItem(position);
        contactHolder.doctorName.setText("Name: "+contacts.getDoctorName());
        contactHolder.appointmentTime.setText("Visiting hours: "+contacts.getAppointment());
        contactHolder.roomNumber.setText("Room number: "+contacts.getRoomNumber());
        return row;



    }

    static class ContactHolder{
        TextView doctorName;
        TextView appointmentTime;
        TextView roomNumber;
    }

}
