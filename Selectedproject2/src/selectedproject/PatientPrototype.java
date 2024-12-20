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
// Prototype pattern for duplicating medical report
public class PatientPrototype implements Cloneable {
    private String name;
    private String address;
    private String medicalHistory;

    // Getters and Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    // Override the clone method to create a copy of the object
    @Override
    public PatientPrototype clone() throws CloneNotSupportedException {
        return (PatientPrototype) super.clone();
}
}
