package com.bnta.appointment;
import com.bnta.exception.AppointmentNotFoundException;
import com.bnta.exception.IllegalStateException;
import com.bnta.exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
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


    private void checkBookAppointmentProperties(Appointment appointment) {
        if(appointment.getAppointmentTime() == null) {
            throw new InvalidRequestException("Appointment time cannot be null");
        }
        if(appointment.getAppointmentDate() == null) {
            throw new InvalidRequestException("Appointment date cannot be null");
        }
        if(appointment.getDoctorId() <= 0) {
            throw new InvalidRequestException("Doctor ID cannot be less than or equal to 0");
        }
        if(appointment.getPatientNhsId() <= 0) {
            throw new InvalidRequestException("Patient ID cannot be less than or equal to 0");
        }
    }

    public int bookAppointment(Appointment appointment) {
        //check if all value inputs are correct
        checkBookAppointmentProperties(appointment);

        //check if appointment already exists or not



        //Should be a specific value for when booking an important, maybe the method returns the number 1
        // for a completed booking on the system
        if (appointmentDAO.bookAppointment(appointment) != 1) {
            //if it doesn't equal to one throw an exception, but keep the user in the loop to re-add the booking
            throw new IllegalStateException("Could not book new appointment");
        }
        //check if local date and local time is the in the database and if the same return error
        //cannot book appointment at the same time with the same doctor for 2 pateints
        //however local date and time both cant be the same





        else {
            return 1;
        }
    }


    public Appointment selectAppointmentById(Integer id) {
//        try {
//            //invalid id - has to be int if anything else return error message
//            //if id is null or 0 less than 0
//            //if appointment is left empty (not filled by admin)
//          Appointment output = appointmentDAO.selectAppointmentById(id);
//
////            if (id == null || id <= 0) {
////                throw new AppointmentNotFoundException("Invalid Appointment ID please try again");
////            }
//            return output; //return appointmentId if it is valid
//
//        //if it goes through database but did not find the id
//        } catch (EmptyResultDataAccessException e) {
//            throw new AppointmentNotFoundException("Appointment with id " + id + " not found");
//        }
        if (id == null || id <= 0) {
            throw new AppointmentNotFoundException("Invalid Appointment ID please try again");
        }
        Appointment output = appointmentDAO.selectAppointmentById(id);
        if (output == null) {
            throw new AppointmentNotFoundException("Appointment with id " + id + " not found");
        }
        return output;
    }




    public int deleteAppointmentById(Integer id) {
        //check if appointment Id exists, so check if null
        if (appointmentDAO.selectAppointmentById(id) == null){
            throw new AppointmentNotFoundException(
                    "Sorry " + id + " could not be found");
        }
        // otherwise delete appointment
        return appointmentDAO.deleteAppointmentById(id);
    }


    public List<Appointment> viewAllAppointments() {

        // appointment can not be found
        List<Appointment> output = appointmentDAO.viewAllAppointments();
        if (output == null) {
            throw new AppointmentNotFoundException("No appointments found.");
        }

        return output;
    }




        public int updateAppointment (Integer id, Appointment update){

            //check if id exists
            selectAppointmentById(id);

                int output = appointmentDAO.updateAppointment(id, update);
                if (output != 1) {
                    throw new IllegalStateException("Could not update appointment.");
                }

            //  return appointmentDAO.deleteAppointmentById(id);
            return output;
        }





    public List<Appointment> getAppointmentByPatientBloodType(String bloodType) {

            List<Appointment> output = appointmentDAO.selectAppointmentByPatientBloodType(bloodType);
            if (output == null) {
                throw new AppointmentNotFoundException("Appointment with this bloodtype not found");
            }
            return output;

    }
    }






