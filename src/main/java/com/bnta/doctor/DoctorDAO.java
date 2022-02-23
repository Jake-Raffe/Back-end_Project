package com.bnta.doctor;

import com.bnta.appointment.Appointment;

import java.util.Arrays;
import java.util.List;

public interface DoctorDAO {

    List<Doctor> getAllDoctors();
    int addDoctor(Doctor doctor);
    int updateDoctorById(Doctor doctor, Integer id);
    Doctor selectDoctorById(Integer id);
    int deleteDoctorById(Doctor doctor);
    int addPresetDoctors();

}
