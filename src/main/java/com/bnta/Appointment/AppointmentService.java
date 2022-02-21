package com.bnta.Appointment;
import com.bnta.doctor.Doctor;
import com.bnta.exceptionCatchers.IllegalStateException;
import com.bnta.patient.Patient;
import org.springframework.beans.factory.annotation.Qualifier;
import com.bnta.Appointment.AppointmentDAO;
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

    public AppointmentService(@Qualifier("postgres") AppointmentDAO appointmentDAO) {this.appointmentDAO = appointmentDAO;
    }

    public void addAppointment(Appointment appointment){
        if(appointmentDAO.bookAppointment(appointment)!=1){
            throw new IllegalStateException("Could not book new appointment");
        }
        int result = appointmentDAO.bookAppointment(appointment);

    }


}



