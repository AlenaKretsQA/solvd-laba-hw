package com.solvd.laba.hospitalProject.people.employees;

import com.solvd.laba.hospitalProject.enums.SalaryType;
import com.solvd.laba.hospitalProject.exceptions.InvalidPersonException;
import com.solvd.laba.hospitalProject.interfaces.IPerformTask;
import com.solvd.laba.hospitalProject.interfaces.Identifiable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Doctor extends Employee implements IPerformTask, Identifiable {

    public static final Logger LOGGER = LogManager.getLogger(Doctor.class);
    private String specialization;
    private String doctorStatus;


    public Doctor(String firstName, String lastName, String phoneNumber, Integer salary, Integer age, SalaryType salaryType, String specialization, String doctorStatus) throws InvalidPersonException {
        super(firstName, lastName, phoneNumber, salary, age);
        this.specialization = specialization;
        this.doctorStatus = doctorStatus;
        LOGGER.info("Create a new Doctor");
    }

    public Doctor(String firstName, String lastName, String phoneNumber, Integer salary, Integer age, String specialization, String doctorStatus) throws InvalidPersonException {
        super(firstName, lastName, phoneNumber, salary, age);
        this.specialization = specialization;
        this.doctorStatus = doctorStatus;
        LOGGER.info("Create a new Doctor");
    }


    public final String getSpecialization() {
        return specialization;
    }

    public final void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDoctorStatus() {
        return doctorStatus;
    }

    public void setDoctorStatus(String doctorStatus) {
        this.doctorStatus = doctorStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(specialization, doctor.specialization) && Objects.equals(doctorStatus, doctor.doctorStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), specialization, doctorStatus);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "specialization='" + specialization + '\'' +
                ", doctorStatus='" + doctorStatus + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public void work() {
        LOGGER.info("Can work as a doctor.");
    }

    @Override
    public void performTask() {
        LOGGER.info("Doctor is performing a medical task, can do surgery.");
    }

    @Override
    public boolean completedTask() {
        LOGGER.info("Doctor has completed the medical task.");
        return true;
    }

    @Override
    public String getId() {
        return generateID(this);
    }

    @Override
    public String generateID(Employee employee) {
        return "D" +
                employee.getFirstName().substring(0, 1) +
                employee.getLastName().substring(0, 1) +
                employee.getPhoneNumber().hashCode();
    }
}
