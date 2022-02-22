package com.bnta.appointment;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentRowMapper implements RowMapper<Appointment> {


    @Override
    public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Appointment(
                rs.getInt("id"),
                rs.getInt("nhs_id"),
                rs.getInt("doctor_id"),
                LocalDate.parse(rs.getString("Local_date")),
                LocalTime.parse(rs.getString("Local_time"))

        );
}}