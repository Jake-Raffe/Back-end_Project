package com.bnta.patient;

import com.bnta.exception.IllegalStateException;
import com.bnta.exception.PatientNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private PatientDAO patientDAO;

    public PatientService(@Qualifier("postgres") PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    public int addNewPatient(Patient patient) throws IllegalStateException {
        System.out.println(patient.getBloodType());
        System.out.println(patient.getPatientEmailAddress());
        int result = patientDAO.insertPatient(patient);
        if (result != 1) {
            throw new IllegalStateException("Could not register new patient.");
        } else {
            return 1;
        }
    }

    public Patient findPatientById(Integer id) {
        try {
            Patient output = patientDAO.selectPatientById(id);
            return output;
        } catch (EmptyResultDataAccessException e) {
            throw new PatientNotFoundException("Patient with ID '" + id + "' not found.");
        }
    }

    public List<Patient> findAllPatients() {
        try {
            return patientDAO.selectAllPatients();
        } catch (EmptyResultDataAccessException e) {
            throw new PatientNotFoundException("No patients found.");
        }
    }

    public void updatePatient(Integer id, Patient update) {
        try {
            int result = patientDAO.updatePatient(id, update);
            if (result != 1) {
                throw new IllegalStateException("Could not update car.");
            }
        } catch (EmptyResultDataAccessException e) {
            throw new PatientNotFoundException("Patient with ID '" + id + "' not found.");
        }
    }

    public void deletePatientById(Integer id) {
        try {
            Patient delPatient = patientDAO.selectPatientById(id);
            int result = patientDAO.deletePatient(delPatient);
            if (result != 1) {
                throw new IllegalStateException("Could not delete patient.");
            }
        } catch (EmptyResultDataAccessException e) {
            throw new PatientNotFoundException("Patient with ID '" + id + "' not found.");
        }
    }
}

