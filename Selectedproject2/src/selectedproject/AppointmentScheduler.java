/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selectedproject;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Gaming3
 */


public class AppointmentScheduler {
    private static AppointmentScheduler instance;
    private List<Appointment> appointments;

    private AppointmentScheduler() {
        appointments = new ArrayList<>();
    }

    // ضمان الوصول المتسلسل للـ Singleton
    public static synchronized AppointmentScheduler getInstance() {
        if (instance == null) {
            instance = new AppointmentScheduler();
        }
        return instance;
    }


public boolean scheduleAppointment(Patient patient, Doctor doctor, String timeSlot) {
    System.out.println("Checking time slot: " + timeSlot);  // سجل الوقت الذي يتم التحقق منه
    for (Appointment appointment : appointments) {
        System.out.println("Existing appointment time: " + appointment.getTimeSlot());  // سجل المواعيد الموجودة
        if (appointment.getTimeSlot().equals(timeSlot)) {
            System.out.println("Time slot is already taken.");
            return false;  // الوقت محجوز
        }
    }
    
    // إذا لم يكن الوقت محجوزًا، أضف الموعد
    appointments.add(new Appointment(patient, doctor, timeSlot));
    System.out.println("Appointment scheduled at " + timeSlot);  // سجل الموعد الجديد
    return true;
}
}

