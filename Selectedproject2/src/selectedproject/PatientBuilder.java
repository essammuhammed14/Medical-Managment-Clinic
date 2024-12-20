/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectedproject;

/**
 *
 * @author Gaming3
 */
public class PatientBuilder {
    private String name;
    private String address;
    private String medicalHistory;

    public PatientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PatientBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public PatientBuilder setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
        return this;
    }

    public Patient build() {
        Patient patient = new Patient();
        patient.setName(this.name);
        patient.setAddress(this.address);
        patient.setMedicalHistory(this.medicalHistory);
        return patient;
    }
}

