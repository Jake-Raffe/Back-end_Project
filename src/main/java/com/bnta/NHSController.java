package com.bnta;

import com.bnta.patient.PatientService;
import com.bnta.exceptionCatchers.PatientNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

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
        return patientService.getAllPatients();
    }

    // Get patient by ID
    @GetMapping(path = "patients/{id}")
    public List<Patient> getPatients(@PathVariable("id") Integer patientId){
            return patientService.getAllPatients(patientId);
    }

    // Update patient details
    @PutMapping("/patients/{id}")
    public void updateCarById(@RequestBody Patient patient, @PathVariable("id") Integer id) {
            patientService.updatePatient(id, patient);
    }

    // Delete patient by ID
    @DeleteMapping("/patients/{id}")
    public void deletePatient(@PathVariable("id") Integer id) {
            patientService.deletePatientById(id);
    }
}
