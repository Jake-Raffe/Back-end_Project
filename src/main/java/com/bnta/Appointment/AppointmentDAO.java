package com.bnta.Appointment;

import java.util.List;

public interface AppointmentDAO {
    int bookAppointment(Appointment appointment);
    int deleteAppointment(Integer appointment);
    int updateAppointment(Appointment appointment);
    List<Appointment> viewAppointment();
    Appointment selectAppointmentById(Integer id);


}
