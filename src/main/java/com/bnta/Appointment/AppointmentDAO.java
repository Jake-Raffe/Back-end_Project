package com.bnta.Appointment;

import com.bnta.patient.Patient;
import com.bnta.doctor.Doctor;

import javax.swing.*;
import java.util.List;

public interface AppointmentDAO {
    int bookAppointment(Patient patient, Doctor doctor);
    int cancelAppointment(Patient patient, Doctor doctor);
    int updateAppointment(Patient patient, Doctor doctor);
    int viewAppointment(Patient patient, Doctor doctor);


}
