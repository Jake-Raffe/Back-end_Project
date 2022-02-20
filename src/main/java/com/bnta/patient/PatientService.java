package com.bnta.patient;

import com.bnta.exceptionCatchers.PatientNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;

public class PatientService {

    private PatientDAO patientDAO;

    public PatientService(@Qualifier("postgres") PatientDAO patientDAO){
        this.patientDAO = patientDAO;
    }

    public void addNewPatient(Patient patient){
        int result = patientDAO.insertPatient(patient);
        if (result != 1){
            throw new IllegalStateException("Could not register new patient");
        }
    }

    public Patient findPatientById(Integer id){
        try {
            Patient output = patientDAO.selectPatientById(id);
            if (output == null){
                throw new PatientNotFoundException("Patient not found");
            }
            return output;
        } catch (EmptyResultDataAccessException e) {
            throw new PatientNotFoundException("Patient with ID '" + patientId + "' not found.");
        }
    }

    public List<Patient> findAllPatients(){
        try {
            return patientDAO.selectAllPatients();
        } catch (EmptyResultDataAccessException e) {
            throw new PatientNotFoundException("Patient with ID '" + patientId + "' not found.");
        }
    }

    public void updatePatient(Integer id, Patient update){
        try {
            int result = patientDAO.updatePatient(id, update);
            if (result != 1) {
                throw new IllegalStateException("Could not update car");
            }
        } catch (EmptyResultDataAccessException e) {
        throw new PatientNotFoundException("Patient with ID '" + patientId + "' not found.");
        }
    }

    public void deletePatientById(Integer id){
        try {
            Patient delPatient = patientDAO.selectPatientById(id);
            int result = patientDAO.deletePatient(delPatient);
            if (result != 1) {
                throw new IllegalStateException("Could not delete patient");
            }
        } catch (EmptyResultDataAccessException e) {
            throw new PatientNotFoundException("Patient with ID '" + patientId + "' not found.");
        }
}

