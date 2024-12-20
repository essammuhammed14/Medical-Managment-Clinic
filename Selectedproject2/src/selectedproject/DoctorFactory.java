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
public class DoctorFactory {
    public Doctor createDoctor(String specialty) {
        switch (specialty) {
            case "Cardiologist":
                return new Cardiologist();  // إنشاء طبيب قلب
            case "Neurologist":
                return new Neurologist();  // إنشاء طبيب أعصاب
            case "General Practitioner":
                return new GeneralPractitioner();  // إنشاء طبيب عام
            default:
                return null;  // التخصص غير معروف
        }
    }
}

