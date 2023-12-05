package com.solvd.laba.hospitalProject.people.employees;

import com.solvd.laba.hospitalProject.Hospital;
import com.solvd.laba.hospitalProject.exceptions.InvalidPersonException;
import com.solvd.laba.hospitalProject.interfaces.IPerformTask;
import com.solvd.laba.hospitalProject.interfaces.Identifiable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Nurse extends Employee implements IPerformTask, Identifiable {

    public static final Logger LOGGER = LogManager.getLogger(Hospital.class);
    private int shift;
    private String category;
    private Boolean isCertified;

    public Nurse(String firstName, String lastName, String phoneNumber, Integer salary, Integer age, int shift, String category, Boolean isCertified) throws InvalidPersonException {
        super(firstName, lastName, phoneNumber, salary, age);
        this.shift = shift;
        this.category = category;
        this.isCertified = isCertified;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getCertified() {
        return isCertified;
    }

    public void setCertified(Boolean certified) {
        isCertified = certified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Nurse nurse = (Nurse) o;
        return shift == nurse.shift && Objects.equals(category, nurse.category) && Objects.equals(isCertified, nurse.isCertified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shift, category, isCertified);
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "shift=" + shift +
                ", category='" + category + '\'' +
                ", isCertified=" + isCertified +
                ", salary=" + salary +
                ", age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public void work() {
        LOGGER.info("Can work as a nurse.");
    }

    @Override
    public void performTask() {
        LOGGER.info("Nurse is performing a medical task.");
    }

    @Override
    public boolean completedTask() {
        LOGGER.info("Nurse has completed the medical task.");
        return true;
    }

    @Override
    public String getId() {
        return generateID(this);
    }

    @Override
    public String generateID(Employee employee) {
        return "N" +
                employee.getFirstName().substring(0, 1) +
                employee.getLastName().substring(0, 1) +
                employee.getPhoneNumber().hashCode();
    }
}
