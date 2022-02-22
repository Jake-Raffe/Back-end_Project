package com.bnta.patient;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("postgres")
public class PatientDBAccess implements PatientDAO {

    private JdbcTemplate jdbcTemplate;

    public PatientDBAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPatient(Patient patient) {
        String insertSql = """
                INSERT INTO patients(name, email_address, phone_number, blood_type)
                VALUES(?, ?, ?, ?)
                """;
        int result = jdbcTemplate.update(
                insertSql,
                patient.getPatientName(),
                patient.getPatientEmailAddress(),
                patient.getPatientPhoneNumber(),
                patient.getBloodType().name()
                );
        return result;
    }

    @Override
    public Patient selectPatientById(Integer id){
        String sql = """
                SELECT id, name, email_address, phone_number, blood_type FROM patients WHERE id = ?
                """;
        List<Patient> patients = jdbcTemplate.query(sql, new PatientRowMapper(), id);
        return patients.stream().findFirst().orElse(null);
    }

    @Override
    public List<Patient> selectAllPatients() {
        String sql = """
                SELECT id, name, email_address, phone_number, blood_type FROM patients
                """;
        return jdbcTemplate.query(sql, new PatientRowMapper());
    }

    @Override
    public int updatePatient(Integer id, Patient update) {
        return jdbcTemplate.update(
                """
                        UPDATE patients
                        SET (name, email_address, phone_number, blood_type) = (?, ?, ?, ?)
                        WHERE id = ?
                        """,
                update.getPatientName(),
                update.getPatientEmailAddress(),
                update.getPatientPhoneNumber(),
                update.getBloodType().name(),
                id
        );
    }


    @Override
    public int deletePatient(Patient patient) {
        return jdbcTemplate.update(
                """
                        DELETE FROM patients WHERE id = ?
                        """,
                patient.getPatientNhsId()
        );
    }

}
