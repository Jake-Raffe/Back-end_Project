package com.bnta.appointment;

import java.util.List;

public interface AppointmentDAO {
    int bookAppointment(Appointment appointment);
    int deleteAppointmentById(Integer id);
    int updateAppointment(Appointment update, Integer id);
    List<Appointment> viewAllAppointments();
    Appointment selectAppointmentById(Integer id);


}
