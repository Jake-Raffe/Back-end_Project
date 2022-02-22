package com.bnta.appointment;

import java.util.List;

public interface AppointmentDAO {
    int bookAppointment(Appointment appointment);
    int deleteAppointment(Appointment appointment);    //we need to fix this
    int updateAppointment(Appointment update, Integer id);
    List<Appointment> viewAppointment();
    Appointment selectAppointmentById(Integer id);


}
