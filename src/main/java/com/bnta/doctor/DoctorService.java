package com.bnta.doctor;

import com.bnta.exception.AppointmentNotFoundException;
import com.bnta.exception.DoctorNotFoundException;
import com.bnta.exception.IllegalStateException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


// We only want this class to add a pre-set list of doctors. Users should not be able to change this list.
@Service
public class DoctorService {

    private DoctorDAO doctorDAO;

    public DoctorService(@Qualifier("doctorrepo") DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    public boolean doesDoctorWithIdExist(Integer doctorId) {
        return doctorDAO
                .getAllDoctors()
                .stream()
                .anyMatch(p -> Objects.equals(p.getDoctorId(), doctorId));
    }

    public int addDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor cannot be null");
        }
        else if (doctor.getDoctorName() == null ||
                doctor.getRoomName() == null) {
                throw new IllegalStateException("Doctor cannot have empty fields");
        }
        boolean exists = doesDoctorWithIdExist(doctor.getDoctorId());
        if (exists) {
            throw new IllegalStateException("Could not add, as doctor already exists");
        }
        int result = doctorDAO.addDoctor(doctor);
         if (result != 1) {
            throw new IllegalStateException("Could not register new doctor.");
        } else {
            return 1;
        }
    }

    public Doctor selectDoctorById(Integer id) {

        if (id == null || id <= 0) {
            throw new DoctorNotFoundException("Invalid Doctor ID please try again");
        }
        Doctor output = doctorDAO.selectDoctorById(id);
        if (output == null) {
            throw new AppointmentNotFoundException("Doctor with id " + id + " not found");
        }
        return output;

    }

    public int deleteDoctorById(Integer id) {
        //check if doctor Id exists
        selectDoctorById(id);

        if (doctorDAO.deleteDoctorById(id) != 1){
            throw new DoctorNotFoundException(
                    "Sorry " + id + " could not be found");

        }
        // otherwise delete appointment
        return doctorDAO.deleteDoctorById(id);

    }


    public List<Doctor> getAllDoctors() {
            return doctorDAO.getAllDoctors();

    }

    public int updateDoctorById (Integer id, Doctor update) {
        selectDoctorById(id);
        int output = doctorDAO.updateDoctorById(id, update);
        if (output != 1) {
            throw new IllegalStateException("Could not update doctor.");



//            }
//         (EmptyResultDataAccessException e) {
//            throw new DoctorNotFoundException("Doctor with id " + id + " not found");
//        }
        }
        return output;
    }

    public void addPresetDoctors() {
        doctorDAO.addPresetDoctors();
    }

    public void deleteAllDoctors() {
        doctorDAO.deleteAllDoctors();}
}
