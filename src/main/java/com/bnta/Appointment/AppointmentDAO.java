package com.bnta.Appointment;

import com.bnta.patient.Patient;
import com.bnta.doctor.Doctor;

import javax.swing.*;
import java.util.List;

public interface AppointmentDAO {
    int bookAppointment(Appointment appointment);
    int cancelAppointment(Appointment appointment);
    int updateAppointment(Appointment appointment);
    int viewAppointment(Appointment appointment);


}
