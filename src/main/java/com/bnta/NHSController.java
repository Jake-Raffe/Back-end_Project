package com.bnta;

import com.bnta.Appointment.Appointment;
import com.bnta.Appointment.AppointmentService;
import com.bnta.patient.Patient;
import com.bnta.patient.PatientService;
import com.bnta.exceptionCatchers.PatientNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NHSController {

    private PatientService patientService;
    private AppointmentService appointmentService;

    public NHSController (PatientService patientService, AppointmentService appointmentService) {
        this.patientService = patientService;
        this.appointmentService = appointmentService;
    }

// Patient options:
    // Add patient
    @PostMapping(path = "patients")
    public void addPatient(@RequestBody Patient patient){
        patientService.addNewPatient(patient);
    }

    // Get all patients
    @GetMapping(path = "patients")
    public List<Patient> getPatients(){
        return patientService.findAllPatients();
    }

    // Get patient by ID
    @GetMapping(path = "patients/{id}")
    public Patient getPatients(@PathVariable("id") Integer patientId){
            return patientService.findPatientById(patientId);
    }

    // Update patient details
    @PutMapping("/patients/{id}")
    public void updatePatientById(@RequestBody Patient patient, @PathVariable("id") Integer id) {
            patientService.updatePatient(id, patient);
    }

    // Delete patient by ID
    @DeleteMapping("/patients/{id}")
    public void deletePatient(@PathVariable("id") Integer id) {
            patientService.deletePatientById(id);
    }

    //Appointment Options

    // Add appointment
    @PostMapping(path = "appointments")
    public void addAppointment(@RequestBody Appointment appointment){appointmentService.addAppointment(appointment);}

    // Get all appointments by id
    @GetMapping(path = "appointments/{id}")
    public Appointment getAppointments (@PathVariable("id") Integer appointmentId) {
        //wait for service methods
        return appointmentService.

        // Get all appointments
        @GetMapping(path = "appointments")
        public List<Appointment> getAppointment() return appointmentService.

        //Update Appointments
        @PutMapping("/appointments/{id}")

        //Delete Appointments by ID

        @DeleteMapping("/patients/{id}")





    }



}
