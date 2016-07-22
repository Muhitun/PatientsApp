package com.example.sohan.patient;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sohan on 6/9/2016.
 */
public class Contacts {
    private String MedicalName;
    private String doctorName;
    private String appointment;
    private String roomNumber;

    public Contacts(String mName){
        this.setMedicalName(mName);
    }

    public Contacts(String dName, String dAppointment, String roomN){
        this.setDoctorName(dName);
        this.setAppointment(dAppointment);
        this.setRoomNumber(roomN);
    }
    public void setMedicalName(String n) {
         MedicalName = n;
    }

    public String getMedicalName() {
        return MedicalName;
    }

    public void setDoctorName(String nName) {
        doctorName = nName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setAppointment(String nApp) {
        appointment = nApp;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setRoomNumber(String room) {
        roomNumber = room;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}
