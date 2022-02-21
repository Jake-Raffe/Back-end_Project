package com.bnta.Appointment;

import com.bnta.patient.Patient;
import com.bnta.doctor.Doctor;

import javax.swing.*;
import java.util.List;

public interface AppointmentDAO {
    int bookAppointment(Appointment appointment);
    int deleteAppointment(Appointment appointment);
    int updateAppointment(Appointment update, Integer id);
    List<Appointment> viewAppointment();
    Appointment selectAppointmentById(Integer id);


}
