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
public class Patient {
    private int id;  // إضافة حقل ID
    private String name;
    private String address;
    private String medicalHistory;
    
    
    Patient (){
        
    }

    // Constructor with id
    public Patient(int id, String name, String address, String medicalHistory) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.medicalHistory = medicalHistory;
    }

    // Constructor without id (يتم استخدامه عند إضافة مريض جديد)
    public Patient(String name, String address, String medicalHistory) {
        this.name = name;
        this.address = address;
        this.medicalHistory = medicalHistory;
    }

    // Getter and Setter methods for id, name, address, medicalHistory
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    @Override
    public String toString() {
        return name;  // عند عرض المرضى في JComboBox، سيتم عرض الاسم فقط
    }
}


