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

    public PatientService(@Qualifier("patientrepo") PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    public int addNewPatient(Patient patient){
        if(patient == null){
            throw new IllegalArgumentException("Patient cannot be null");
        }
        if (patient.getPatientNhsId() == 0 ||
                patient.getPatientName() == null ||
                patient.getPatientEmailAddress() == null ||
                patient.getPatientPhoneNumber() == null ||
                patient.getBloodType().toString() == null) {
            throw new IllegalStateException("Patient cannot have empty fields");
        }

     //   boolean exists = doesPersonWithIdExists(person.getId());
     //   if (exists) {
        //     throw new IllegalStateException("person with id " + person.getId() + " already exists");
      //  }

     //   int result = personDAO.insertPerson(person);
      //  return result;

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

