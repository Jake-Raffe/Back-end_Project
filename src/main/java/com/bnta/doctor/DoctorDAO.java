package com.bnta.doctor;

import java.util.List;

public interface DoctorDAO {

    List<Doctor> getAllDoctors();

    int addDoctor(Doctor doctor);

    int updateDoctorById(Doctor doctor, Integer id);

    Doctor selectDoctorById(Integer id);

    int deleteDoctorById(Doctor doctor);

    int addPresetDoctors();

    int deleteAllDoctors();
}
