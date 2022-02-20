package com.bnta.DAOs;

import com.bnta.patient.Patient;
import com.bnta.patient.Patient.BloodType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postgres")
public class PatientDBAccess {

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
                SELECT name, email_address, phone_number, blood_type
                FROM patients 
                WHERE id = ?
                """;
        List<Patient> patients = jdbcTemplate.query(sql, new PatientRowMapper(), id);
        return patients.stream().findFirst().orElse(null);
    }

}
