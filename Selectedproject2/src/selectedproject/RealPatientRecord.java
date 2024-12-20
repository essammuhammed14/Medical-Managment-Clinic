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
// الفئة الحقيقية التي تحتوي على البيانات الحقيقية
public class RealPatientRecord implements PatientRecordAccess {
    private Patient patient;

    public RealPatientRecord(Patient patient) {
        this.patient = patient;
    }

    @Override
    public void accessPatientRecord() {
        // هنا يتم عرض أو تعديل بيانات المريض الحقيقية
        System.out.println("Accessing patient record for: " + patient.getName());
        System.out.println("Address: " + patient.getAddress());
        System.out.println("Medical History: " + patient.getMedicalHistory());
    }
}

