package com.bnta.patient;

import com.bnta.exception.IllegalStateException;
import com.bnta.exception.PatientNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.List;

@Service
public class PatientService {

    private PatientDAO patientDAO;

    public PatientService(@Qualifier("patientrepo") PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    public boolean doesPatientWithIdExist(Integer patientNhsId) {
        return patientDAO
                .selectAllPatients()
                .stream()
                .anyMatch(p -> Objects.equals(p.getPatientNhsId(), patientNhsId));
    }

    public int addNewPatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null");
        }
        if (patient.getPatientNhsId() == 0 ||
                patient.getPatientName() == null ||
                patient.getPatientEmailAddress() == null ||
                patient.getPatientPhoneNumber() == null ||
                patient.getBloodType() == null) {
            throw new IllegalStateException("Patient cannot have empty fields");
        }

        boolean exists = doesPatientWithIdExist(patient.getPatientNhsId());
        if (exists) {
            throw new IllegalStateException("patient with id " + patient.getPatientNhsId() + " already exists");
        }

  /*      int result = patientDAO.insertPatient(patient);
        return result;*/

        int result = patientDAO.insertPatient(patient);
        if (result != 1) {
            throw new IllegalStateException("Could not register new patient.");
        } else {
            return 1;
        }
    }

// to test ? we incorporated it to others
    public Patient findPatientById(Integer id) {
        boolean exists = doesPatientWithIdExist(id);
        if (exists) {
            try {
                return patientDAO.selectPatientById(id);
            } catch (EmptyResultDataAccessException e) {
                throw new PatientNotFoundException("Patient with ID '" + id + "' not found.");
            }
        }
        return patientDAO.selectPatientById(id);
    }
// need to test
    public List<Patient> findAllPatients() {
        try {
            return patientDAO.selectAllPatients();
        } catch (EmptyResultDataAccessException e) {
            throw new PatientNotFoundException("No patients found.");
        }
    }

    public int updatePatient(Integer id, Patient update) {
        if (update == null) {
            throw new IllegalArgumentException("Patient cannot be null");
        } else if (update.getPatientNhsId() == 0 ||
                update.getPatientName() == null ||
                update.getPatientEmailAddress() == null ||
                update.getPatientPhoneNumber() == null ||
                update.getBloodType() == null) {
            throw new IllegalStateException("Patient cannot have empty fields");
        } else {
            try {
                return patientDAO.updatePatient(id, update);

            } catch (EmptyResultDataAccessException e) {
                throw new PatientNotFoundException("Patient with ID '" + id + "' not found.");
            }
        }
    }

    public int deletePatientById(Integer id) {
        boolean exists = doesPatientWithIdExist(id);
        if (exists) {
            Patient delPatient = patientDAO.selectPatientById(id);
            return patientDAO.deletePatient(delPatient);
        } else {
            throw new PatientNotFoundException("Patient with ID '" + id + "' not found.");
        }
    }

}

