/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectedproject;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Gaming3
 */
// البروكسي للتحكم في الوصول إلى سجلات المرضى
// تعريف واجهة الوصول للسجل الطبي
// تنفيذ البروكسي للوصول إلى السجل
public class ProxyPatientRecord implements PatientRecordAccess {
    private Patient patient;
    private String userRole;

    public ProxyPatientRecord(Patient patient, String userRole) {
        this.patient = patient;
        this.userRole = userRole;
    }

    public void accessPatientRecord(Component dialog) {
       // التحقق من دور المستخدم
if ("Doctor".equalsIgnoreCase(userRole)) {
    // السماح بالوصول إلى السجل الطبي
    PatientRecordAccess proxy = new ProxyPatientRecord(patient, userRole);
    proxy.accessPatientRecord();
} else {
    JOptionPane.showMessageDialog(dialog, "You do not have permission to view this record.");
}

    }

    @Override
    public void accessPatientRecord() {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}



