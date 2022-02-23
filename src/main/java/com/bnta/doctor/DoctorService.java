package com.bnta.doctor;

import com.bnta.exception.DoctorNotFound;
import com.bnta.patient.PatientDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


// We only want this class to add a pre-set list of doctors. Users should not be able to change this list.
@Service
public class DoctorService {

    private DoctorDAO doctorDAO;

    public DoctorService(@Qualifier("doctorrepo") DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    public int addDoctor(Doctor doctor) {
        //returns the number 1 for successfully added doctor
        int result = doctorDAO.addDoctor(doctor);
        if (result != 1) {
            throw new IllegalStateException("Could not add new doctor");
        }
        else {
            return 1;
        }
    }

    public Doctor selectDoctorById(Integer id) {
        try {
            Doctor output = doctorDAO.selectDoctorById(id);
            if (output == null) {
                throw new DoctorNotFound("Doctor not found");
            }
            return output;

        } catch (EmptyResultDataAccessException e) {
            throw new DoctorNotFound("Doctor with id " + id + " not found");
        }
    }

    public void deleteDoctorById(Integer id) {
        //check if doctor id exists, so check if null
        if (doctorDAO.selectDoctorById(id) == null) {
            throw new DoctorNotFound(
                    "Sorry" + id + " could not be found");
        }
        doctorDAO.deleteDoctorById(doctorDAO.selectDoctorById(id));
    }
    public List<Doctor> getAllDoctors() {
        try {
            return doctorDAO.getAllDoctors();
        } catch (EmptyResultDataAccessException e) {
            throw new DoctorNotFound("No doctor found.");
        }
    }

    public void updateDoctorById (Integer id, Doctor update) {
        try {
            int output = doctorDAO.updateDoctorById(update, id);
            if (output != 1) {
                throw new IllegalStateException("Could not update doctor.");
            }
        } catch (EmptyResultDataAccessException e) {
            throw new DoctorNotFound("Doctor with id " + id + " not found");
        }
    }

    public void addPresetDoctors() {
        doctorDAO.addPresetDoctors();
    }
}
