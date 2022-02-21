package com.bnta.DAOs;

import com.bnta.Appointment.Appointment;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentRowMapper implements RowMapper<Appointment> {


    @Override
    public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Appointment(
                rs.getInt("id"),
                rs.getInt("nhs_id"),
                rs.getInt("doctor_id"),
                rs.getDate("Local_date_time");

    }
}
