package com.bnta.Appointment;

import java.util.List;

public interface AppointmentDAO {
    int bookAppointment(Appointment appointment);
    int deleteAppointment(Appointment appointment);
    int updateAppointment(Appointment update, Integer id);
    List<Appointment> viewAppointment();
    Appointment selectAppointmentById(Integer id);


}
