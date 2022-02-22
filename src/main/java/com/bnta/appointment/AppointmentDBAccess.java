package com.bnta.appointment;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

@Repository("appointmentrepo")

public class AppointmentDBAccess implements AppointmentDAO{

    private JdbcTemplate jdbcTemplate;

    public AppointmentDBAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int bookAppointment(Appointment appointment) {
        String insertSql =
                """
                INSERT INTO appointments(patient_id, doctor_id, appointment_date, appointment_time)
                VALUES(?,?,?,?)
                """;

        int result = jdbcTemplate.update(
                insertSql,
                appointment.getPatientNhsId(),
                appointment.getDoctorId(),
                appointment.getAppointmentDate().toString(),
                appointment.getAppointmentTime().toString()
                );
        return result;
    }

    @Override
    public List<Appointment> viewAppointment() {
        /*String sql = """
                SELECT appointments(nhs_id, doctor_id, Local_Date_Time)
                """;*/
        String sql = """
                SELECT appointment_id, patient_id, doctor_id, appointment_date, appointment_time FROM appointments
                """;
        return jdbcTemplate.query(sql, new AppointmentRowMapper());
    }


    @Override
    public Appointment selectAppointmentById(Integer id) {
        String sql = """
                SELECT appointment_id, patient_id, doctor_id, appointment_date, appointment_time FROM appointments WHERE appointment_id = ?
                """;
        List<Appointment> appointments = jdbcTemplate.query(sql, new AppointmentRowMapper(), id);
        return appointments.stream().findFirst().orElse(null);

    }

    @Override
    public int deleteAppointment(Appointment appointment) {
        return jdbcTemplate.update(
                """
                        DELETE FROM appointments WHERE appointment_id = ?
                        """,
                appointment.getAppointmentId()
        );
    }


    @Override
    public int updateAppointment(Appointment update, Integer id) {
        return jdbcTemplate.update(
                """
                        UPDATE appointments SET (patient_id, doctor_id, appointment_date, appointment_time) = (?, ?, ?, ?) WHERE appointment_id = ?
                        """,
                update.getPatientNhsId(),
                update.getDoctorId(),
                update.getAppointmentDate(),
                update.getAppointmentTime(),
                id

        );
    }


    //Extend appointment controller and patient controller
    //Join tables- e.g select all patients for one doctor
    //select all appointments for one patient
    //Add doctor controller
}





