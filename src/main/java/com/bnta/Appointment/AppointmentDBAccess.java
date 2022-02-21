package com.bnta.Appointment;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;


/* What we want this service to do;
    1 - Add  appointments.
    Get Patient information, and Create a booking for them, initiate a for loop by patientId and if empty create an 'instance' to add Doctor, patient and, appointment room together, return the information.
    We need to

    2- Cancel Patient appointments.
    initiate a for each loop for the appointments list, byPatientId =

    3 - Update Patient appointments.
    4 - View Patient appointments.
    5 - Remove Patient appointments.
     */

public class AppointmentDBAccess implements AppointmentDAO{

    private JdbcTemplate jdbcTemplate;

    public AppointmentDBAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int bookAppointment(Appointment appointment) {
        return 0;
    }

    @Override
    public int deleteAppointment(Integer appointment) {
        return 0;
    }

    @Override
    public int updateAppointment(Appointment appointment) {
        return 0;
    }

    @Override
    public List<Appointment> viewAppointment() {
        return null;
    }
}





