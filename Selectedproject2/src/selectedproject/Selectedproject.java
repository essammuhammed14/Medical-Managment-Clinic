/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectedproject;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Gaming3
 */
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Selectedproject {
    private static PatientDatabaseManager patientDatabaseManager;
    private static AppointmentScheduler appointmentScheduler;

    public static void main(String[] args) {
        // تهيئة قواعد البيانات
        patientDatabaseManager = PatientDatabaseManager.getInstance();
        appointmentScheduler = AppointmentScheduler.getInstance();

        // إعداد نافذة الواجهة
        final JFrame frame = new JFrame("Medical Clinic Management");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // لوحة التحكم الرئيسية
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        // إضافة زر لإضافة مريض جديد
        JButton addPatientButton = new JButton("Add Patient");
        panel.add(addPatientButton);

        // إضافة زر لإضافة موعد
        JButton addAppointmentButton = new JButton("Schedule Appointment");
        panel.add(addAppointmentButton);

        // إضافة زر لعرض سجلات المرضى
        JButton viewPatientRecordButton = new JButton("View Patient Record");
        panel.add(viewPatientRecordButton);

        // إضافة اللوحة إلى الإطار
        frame.add(panel, BorderLayout.CENTER);

        // زر لإغلاق التطبيق
        JButton closeButton = new JButton("Exit");
        frame.add(closeButton, BorderLayout.SOUTH);

        // التعامل مع أحداث الأزرار
        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddPatientDialog(frame);
            }
        });

        addAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showScheduleAppointmentDialog(frame);
            }
        });

        viewPatientRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showViewPatientRecordDialog(frame);
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // إغلاق التطبيق
            }
        });

        frame.setVisible(true);
    }

    // نافذة لإضافة مريض
   private static void showAddPatientDialog(JFrame parentFrame) {
    final JDialog dialog = new JDialog(parentFrame, "Add Patient", true);
    dialog.setLayout(new GridLayout(4, 2));
    dialog.setSize(400, 200);

    final JTextField nameField = new JTextField();
    final JTextField addressField = new JTextField();
    final JTextField medicalHistoryField = new JTextField();

    dialog.add(new JLabel("Patient Name:"));
    dialog.add(nameField);
    dialog.add(new JLabel("Address:"));
    dialog.add(addressField);
    dialog.add(new JLabel("Medical History:"));
    dialog.add(medicalHistoryField);

    JButton addButton = new JButton("Add Patient");
    dialog.add(addButton);

    addButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String address = addressField.getText();
            String medicalHistory = medicalHistoryField.getText();

            // إضافة المريض إلى قاعدة البيانات
            Patient newPatient = new Patient(name, address, medicalHistory);
            patientDatabaseManager.addPatient(newPatient);

            JOptionPane.showMessageDialog(dialog, "Patient added successfully!");

            dialog.dispose();
        }
    });

    dialog.setVisible(true);
}



    // نافذة لجدولة المواعيد
  private static void showScheduleAppointmentDialog(JFrame parentFrame) {
    final JDialog dialog = new JDialog(parentFrame, "Schedule Appointment", true);
    dialog.setLayout(new GridLayout(5, 2)); // إضافة صف إضافي لعرض تخصص الطبيب
    dialog.setSize(400, 250);

    // إضافة قائمة من المرضى
    final JComboBox<Patient> patientComboBox = new JComboBox<>();
    for (Patient patient : PatientDatabaseManager.getInstance().getPatients()) {
        patientComboBox.addItem(patient);  // إضافة كل مريض إلى القائمة
    }

    // إضافة قائمة من تخصصات الأطباء
    final JComboBox<String> doctorSpecialtyComboBox = new JComboBox<>();
    doctorSpecialtyComboBox.addItem("Cardiologist");
    doctorSpecialtyComboBox.addItem("Neurologist");
    doctorSpecialtyComboBox.addItem("General Practitioner");

    final JTextField timeSlotField = new JTextField();

    dialog.add(new JLabel("Select Patient:"));
    dialog.add(patientComboBox);
    dialog.add(new JLabel("Select Doctor Specialty:"));
    dialog.add(doctorSpecialtyComboBox);
    dialog.add(new JLabel("Time Slot:"));
    dialog.add(timeSlotField);

    // زر الجدولة
    JButton scheduleButton = new JButton("Schedule Appointment");
    dialog.add(scheduleButton);

    scheduleButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // الحصول على المريض من القائمة المنسدلة
            Patient selectedPatient = (Patient) patientComboBox.getSelectedItem();
            String selectedSpecialty = (String) doctorSpecialtyComboBox.getSelectedItem();
            String timeSlot = timeSlotField.getText().trim();


            if (selectedPatient != null && selectedSpecialty != null) {
                // استخدام الـ فاكتوري لإنشاء الطبيب حسب التخصص
                DoctorFactory doctorFactory = new DoctorFactory();
                Doctor doctor = doctorFactory.createDoctor(selectedSpecialty);

                if (doctor != null) {
                    // جدولة الموعد
                    boolean isScheduled = AppointmentScheduler.getInstance().scheduleAppointment(selectedPatient, doctor, timeSlot);
                    if (isScheduled) {
                        JOptionPane.showMessageDialog(dialog, "Appointment scheduled successfully!");
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Time slot is already taken!");
                    }
                } else {
                    JOptionPane.showMessageDialog(dialog, "Invalid doctor specialty!");
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Please select a patient and doctor specialty!");
            }

            dialog.dispose();
        }
    });

    dialog.setVisible(true);
}

    

    // نافذة لعرض السجلات الطبية
   private static void showViewPatientRecordDialog(JFrame parentFrame) {
    final JDialog dialog = new JDialog(parentFrame, "View Patient Record", true);
    dialog.setLayout(new GridLayout(3, 2));
    dialog.setSize(400, 200);

    // حقل إدخال الاسم
    final JTextField patientNameField = new JTextField();
    final JTextField userRoleField = new JTextField();  // إدخال دور المستخدم (Doctor / Non-Doctor)

    dialog.add(new JLabel("Patient Name:"));
    dialog.add(patientNameField);
    dialog.add(new JLabel("Role (Doctor / Non-Doctor):"));
    dialog.add(userRoleField);

    // زر العرض
    JButton viewButton = new JButton("View Record");
    dialog.add(viewButton);

    viewButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String patientName = patientNameField.getText();
            String userRole = userRoleField.getText();

            try {
                // البحث عن المريض
                Patient patient = findPatientByName(patientName);

                if (patient != null) {
                    // التحقق من دور المستخدم قبل السماح بالوصول للسجل
                    if ("Doctor".equalsIgnoreCase(userRole)) {
                        // استخدام البروكسي للوصول إلى السجل الطبي
                        PatientRecordAccess proxy = new ProxyPatientRecord(patient, userRole);
                        proxy.accessPatientRecord();  // الوصول إلى السجل الطبي
                    } else {
                        JOptionPane.showMessageDialog(dialog, "You do not have permission to view this record.");
                    }
                } else {
                    JOptionPane.showMessageDialog(dialog, "Patient not found!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(dialog, "An error occurred while searching for the patient.");
            }

            dialog.dispose();
        }
    });

    dialog.setVisible(true);
}


    // البحث عن المريض باستخدام اسمه
   // البحث عن المريض باستخدام اسمه
private static Patient findPatientByName(String name) {
    System.out.println("Searching for patient: " + name);  // تتبع اسم المريض
    for (Patient patient : patientDatabaseManager.getPatients()) {
        System.out.println("Checking patient: " + patient.getName());  // تتبع المرضى الذين يتم فحصهم
        if (patient.getName().equalsIgnoreCase(name.trim())) {
            return patient;
        }
    }
    System.out.println("Patient not found.");
    return null;  // مريض غير موجود
}
}
