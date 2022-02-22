package com.bnta.Appointment;

import com.bnta.DAOs.AppointmentRowMapper;
import com.bnta.DAOs.PatientRowMapper;
import com.bnta.patient.Patient;
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

@Repository("sql")

public class AppointmentDBAccess implements AppointmentDAO{

    private JdbcTemplate jdbcTemplate;

    public AppointmentDBAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int bookAppointment(Appointment appointment) {
        String insertSql =
                """
                INSERT INTO appointments(patient_id, doctor_id, date_and_time)
                VALUES(?,?,?)
                """;

        int result = jdbcTemplate.update(
                insertSql,
                appointment.getPatientNhsId(),
                appointment.getDoctorId(),
                appointment.getAppointmentTime()
                );
        return result;
    }



    @Override
    public int deleteAppointment(Appointment appointment) {
        return jdbcTemplate.update(
                """
                        DELETE FROM appointments WHERE id=?
                        """,
                appointment.getAppointmentId()
        );
    }


    @Override
    public int updateAppointment(Appointment update, Integer id) {
        return jdbcTemplate.update(
                """
                        UPDATE patients
                        SET (name, email_address, phone_number, blood_type) = (?, ?, ?, ?)
                        WHERE id = ?
                        """,
                update.getPatientNhsId(),
                update.getDoctorId(),
                update.getAppointmentTime()

        );
    }



    @Override
    public List<Appointment> viewAppointment() {
        String sql = """
                SELECT appointments(nhs_id, doctor_id, Local_Date_Time)
                """;
        return jdbcTemplate.query(sql, new AppointmentRowMapper());
    }


    @Override
    public Appointment selectAppointmentById(Integer id) {
        String sql = """
                SELECT appointments(nhs_id, doctor_id, Local_Date_Time)
                FROM patients 
                WHERE id = ?
                """;
        List<Appointment> appointments = jdbcTemplate.query(sql, new AppointmentRowMapper(), id);
        return appointments.stream().findFirst().orElse(null);

    }
}





