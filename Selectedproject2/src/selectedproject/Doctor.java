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
public abstract class Doctor {
    protected String specialty;  // تخصص الطبيب

    // Getter method
    public String getSpecialty() {
        return specialty;  // إرجاع التخصص
    }

    int getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
class Cardiologist extends Doctor {
    public Cardiologist() {
        this.specialty = "Cardiologist";  // تعيين التخصص
    }
}

class Neurologist extends Doctor {
    public Neurologist() {
        this.specialty = "Neurologist";  // تعيين التخصص
    }
}

class GeneralPractitioner extends Doctor {
    public GeneralPractitioner() {
        this.specialty = "General Practitioner";  // تعيين التخصص
    }
}

