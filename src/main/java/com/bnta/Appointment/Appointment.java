package com.bnta.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Appointment {
    private int appointmentId;
    private int patientNhsId;
    private int doctorId;
    private LocalDateTime appointmentTime;

    public Appointment(int appointmentId, int patientNhsId, int doctorId, LocalDateTime appointmentTime) {
        this.appointmentId = appointmentId;
        this.patientNhsId = patientNhsId;
        this.doctorId = doctorId;
        this.appointmentTime = appointmentTime;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPatientNhsId() {
        return patientNhsId;
    }

    public void setPatientNhsId(int patientNhsId) {
        this.patientNhsId = patientNhsId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }


    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", patientNhsId=" + patientNhsId +
                ", doctorId=" + doctorId +
                ", appointmentTime=" + appointmentTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return appointmentId == that.appointmentId && patientNhsId == that.patientNhsId && doctorId == that.doctorId && Objects.equals(appointmentRoom, that.appointmentRoom) && Objects.equals(appointmentTime, that.appointmentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId, patientNhsId, doctorId, appointmentTime);
    }
}
