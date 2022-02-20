package com.bnta.DAOs;

import com.bnta.patient.Patient;

import java.util.List;

public interface PatientDAO {
    int insertPatient(Patient patient);
    int deletePatient(Patient patient);
    int updatePatient(Integer id, Patient update);
    List<Patient> selectAllPatients();
    Patient selectPatientById(Integer id);
}
