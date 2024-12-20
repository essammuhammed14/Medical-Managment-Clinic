/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectedproject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Gaming3
 */
public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/MedicalClinic"; // استبدل بعنوان قاعدة البيانات الخاصة بك
    private static final String USER = "root";  // اسم المستخدم
    private static final String PASSWORD = "123456"; // كلمة المرور

    private static Connection connection;

    // الاتصال بقاعدة البيانات
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
            }
        }
        return connection;
    }

    // إغلاق الاتصال
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
    }

    // إضافة مريض إلى قاعدة البيانات
    public static void addPatient(Patient patient) {
    String query = "INSERT INTO patients (name, address, medical_history) VALUES (?, ?, ?)";
    try (PreparedStatement ps = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        ps.setString(1, patient.getName());
        ps.setString(2, patient.getAddress());
        ps.setString(3, patient.getMedicalHistory());
        ps.executeUpdate();

        // الحصول على الـ id المولّد
        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
            int patientId = generatedKeys.getInt(1);
            patient.setId(patientId);  // تعيين الـ id للمريض
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    // استرجاع قائمة المرضى من قاعدة البيانات
    public static List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM patients";
        try (Statement stmt = getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Patient patient = new Patient(
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("medical_history")
                );
                patients.add(patient);
            }
        } catch (SQLException e) {
        }
        return patients;
    }

    // تحديث موعد في قاعدة البيانات
    public static void updateAppointment(Appointment appointment) {
        String query = "UPDATE appointments SET doctor_id = ?, time_slot = ? WHERE patient_id = ?";
        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            ps.setInt(1, appointment.getDoctor().getId());
            ps.setString(2, appointment.getTimeSlot());
            ps.setInt(3, appointment.getPatient().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    static Patient getPatientById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

   