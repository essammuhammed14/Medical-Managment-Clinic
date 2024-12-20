/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectedproject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Gaming3
 */



public class PatientDatabaseManager extends DatabaseManager{

    private static PatientDatabaseManager instance;

    private PatientDatabaseManager() {
    }

    // ضمان الوصول المتسلسل للـ Singleton
    public static synchronized PatientDatabaseManager getInstance() {
        if (instance == null) {
            instance = new PatientDatabaseManager();
        }
        return instance;
    }

    // إضافة مريض إلى قاعدة البيانات
    public static void addPatient(Patient patient) {
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "INSERT INTO patients (name, address, medical_history) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, patient.getName());
            stmt.setString(2, patient.getAddress());
            stmt.setString(3, patient.getMedicalHistory());
            stmt.executeUpdate();  // تنفيذ الاستعلام لإدخال المريض في قاعدة البيانات

            // استرجاع الـ ID الذي تم إنشاؤه تلقائيًا
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                patient.setId(rs.getInt(1));  // تعيين الـ ID للمريض بعد إضافته
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // استرجاع قائمة المرضى من قاعدة البيانات
    public static List<Patient> getPatients() {
        List<Patient> patients = new ArrayList<>();
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "SELECT * FROM patients";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");  // استرجاع الـ ID
                String name = rs.getString("name");
                String address = rs.getString("address");
                String medical_history = rs.getString("medical_history");
                Patient patient = new Patient(name, address, medical_history);
                patient.setId(id);  // تعيين الـ ID للمريض
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    // البحث عن مريض باستخدام الاسم
   // البحث عن المريض باستخدام اسمه
// البحث عن المريض باستخدام اسمه
private static Patient findPatientByName(String name) {
    System.out.println("Searching for patient: " + name);  // تتبع اسم المريض
    for (Patient patient : PatientDatabaseManager.getPatients()) {
        System.out.println("Checking patient: " + patient.getName());  // تتبع المرضى الذين يتم فحصهم
        if (patient.getName().equalsIgnoreCase(name.trim())) {
            return patient;
        }
    }
    System.out.println("Patient not found.");
    return null;  // مريض غير موجود
}
}



