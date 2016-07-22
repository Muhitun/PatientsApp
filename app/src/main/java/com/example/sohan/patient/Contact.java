package com.example.sohan.patient;

import android.widget.Toast;

/**
 * Created by Sohan on 6/9/2016.
 */
public class Contact {                                        // Contact class for history
    private String Name,Age,Height,Weight,Symptom,Treatment,dName;
    private String patientName, patientSymptom, doctorTreatment, doctorName;

    public Contact(String PName, String pAge, String pHeight, String pWeight, String pSymptom, String Treatment, String dName){
        this.setName(PName);
        this.setAge(pAge);
        this.setHeight(pHeight);
        this.setWeight(pWeight);
        this.setSymptom(pSymptom);
        this.setdoctorName(dName);
        this.setTreatment(Treatment);
    }

    public Contact(String pName, String symptom, String treatment, String Doctor){
        patientName = pName;
        patientSymptom = symptom;
        doctorTreatment = treatment;
        doctorName = Doctor;
    }


    public void setName(String nName) {
        Name = nName;
    }

    public String getName() {
        return Name;
    }

    public void setAge(String nApp) {
        Age = nApp;
    }

    public String getAge() {
        return Age;
    }

    public void setHeight(String room) {
        Height = room;
    }

    public String getHeight() {
        return Height;
    }

    public void setWeight(String room) {
        Weight = room;
    }

    public String getWeight() {
        return Weight;
    }

    public void setSymptom(String room) {
        Symptom = room;
    }

    public String getSymptom() {
        return Symptom;
    }

    public void setdoctorName(String room) {
        dName = room;
    }

    public String getdoctorName() {
        return dName;
    }

    public void setTreatment(String room) {
        Treatment = room;
    }

    public String getTreatment() {
        return Treatment;
    }


//------------------------------------For notification------------------------------>>


    public String getPatientName(){
        return patientName;
    }

    public String getPatientSymptom(){
        return patientSymptom;
    }

    public String getDoctorTreatment(){
        return doctorTreatment;
    }

    public String getDoctorName(){
        return doctorName;
    }

}
