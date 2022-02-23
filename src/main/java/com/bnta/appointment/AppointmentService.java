package com.bnta.appointment;
import com.bnta.exception.AppointmentNotFoundException;
import com.bnta.exception.IllegalStateException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    /* What we want this service to do;
    1- to add a appointment
    check if patient exists- selectpPatientById(Integer id)


    1 - Add Patient appointments.
    Get Patient information, and Create a booking for them, initiate a for loop by patientId and if empty create an 'instance' to add Doctor, patient and, appointment room together, return the information.
    We need to

    2- Cancel Patient appointments.
    initiate a for each loop for the appointments list, byPatientId =

    3 - Update Patient appointments.
    4 - View Patient appointments.
    5 - Remove Patient appointments.
     */
    private AppointmentDAO appointmentDAO;

    public AppointmentService(@Qualifier("appointmentrepo") AppointmentDAO appointmentDAO) {
        this.appointmentDAO = appointmentDAO;
    }

    public int bookAppointment(Appointment appointment) {
        //Should be a specific value for when booking an important, maybe the method returns the number 1 for a completed booking on the system
        if (appointmentDAO.bookAppointment(appointment) != 1) {
            //if it doesn't equal to one throw an exception, but keep the user in the loop to re-add the booking
            throw new IllegalStateException("Could not book new appointment");
        } else {
            return 1;
        }
    }


    public Appointment selectAppointmentById(Integer id) {
        try {
            Appointment output = appointmentDAO.selectAppointmentById(id);
            if (output == null) {
                throw new AppointmentNotFoundException("Appointment not found");
            }
            return output;

        } catch (EmptyResultDataAccessException e) {
            throw new AppointmentNotFoundException("Appointment with id " + id + " not found");
        }

    }

    public int deleteAppointmentById(Integer id) {
        //check if appointment Id exists, so check if null
        if (appointmentDAO.selectAppointmentById(id) == null) {
            throw new AppointmentNotFoundException(
                    "Sorry " + id + " could not be found");
        }
        // otherwise delete appointment
        return appointmentDAO.deleteAppointmentById(id);
    }


    public List<Appointment> viewAllAppointments() {

        // Try and catch method incase, appointment can not be found
        try {
            return appointmentDAO.viewAllAppointments();
        } catch (EmptyResultDataAccessException e) {
            throw new AppointmentNotFoundException("No appointments found.");
        }
    }

        public void updateAppointment (Appointment update, Integer id){
            try {
                int output = appointmentDAO.updateAppointment(update, id);
                if (output != 1) {
                    throw new IllegalStateException("Could not update appointment.");
                }
            } catch (EmptyResultDataAccessException e) {
                throw new AppointmentNotFoundException("Appointment with id " + id + " not found");
            }
        }

    public List<Appointment> getAppointmentByPatientBloodType(String bloodType) {
        try {
            List<Appointment> output = appointmentDAO.selectAppointmentByPatientBloodType(bloodType);
            if (output == null) {
                throw new AppointmentNotFoundException("Appointment with this bloodtype not found");
            }
            return output;

        } catch (EmptyResultDataAccessException e) {
            throw new AppointmentNotFoundException("Appointment with patient bloodtype " + bloodType + " not found");
        }

    }
    }






