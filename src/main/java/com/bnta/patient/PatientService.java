package com.bnta.patient;

import com.bnta.exceptionCatchers.PatientNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;

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
        Patient output = patientDAO.selectPatientById(id);
        if (output == null){
            throw new PatientNotFoundException("Patient not found");
        }
        return output;
    }

    public List<Patient> findAllPatients(){
        return patientDAO.selectAllPatients();
    }

    public void updatePatient(Integer id, Patient update){
        int result = patientDAO.updatePatient(id, update);
        if (result != 1){
            throw new IllegalStateException("Could not update car");
        }
    }

    public void deletePatientById(Integer id){
        Patient delPatient = patientDAO.selectPatientById(id);
        int result = patientDAO.deletePatient(delPatient);
        if (result != 1){
            throw new IllegalStateException("Could not delete patient");
        }
    }
}

