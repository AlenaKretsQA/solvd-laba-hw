package com.solvd.laba.hospitalProject.people.employees;

import com.solvd.laba.hospitalProject.Hospital;
import com.solvd.laba.hospitalProject.exceptions.InvalidPersonException;
import com.solvd.laba.hospitalProject.interfaces.IPerformTask;
import com.solvd.laba.hospitalProject.interfaces.Identifiable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Administrator extends Employee implements IPerformTask, Identifiable {

    public static final Logger LOGGER = LogManager.getLogger(Hospital.class);
    protected String degree;
    protected boolean hasManagementTraining;

    public Administrator(String firstName, String lastName, String phoneNumber, Integer salary, Integer age, String degree, boolean hasManagementTraining) throws InvalidPersonException {
        super(firstName, lastName, phoneNumber, salary, age);
        this.degree = degree;
        this.hasManagementTraining = hasManagementTraining;
    }

    public Administrator(String firstName, String lastName, String phoneNumber, boolean hasManagementTraining) throws InvalidPersonException {
        super(firstName, lastName, phoneNumber);
        this.hasManagementTraining = hasManagementTraining;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public boolean isHasManagementTraining() {
        return hasManagementTraining;
    }

    public void setHasManagementTraining(boolean hasManagementTraining) {
        this.hasManagementTraining = hasManagementTraining;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Administrator that = (Administrator) o;
        return hasManagementTraining == that.hasManagementTraining && Objects.equals(degree, that.degree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), degree, hasManagementTraining);
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "degree='" + degree + '\'' +
                ", hasManagementTraining=" + hasManagementTraining +
                ", salary=" + salary +
                ", age=" + age +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public void work() {
        LOGGER.info("Can work as administrator.");
    }

    @Override
    public void performTask() {
        LOGGER.info("Administrator is performing an administrative task.");
    }

    @Override
    public boolean completedTask() {
        LOGGER.info("Administrator has completed the task.");
        return true;
    }

    @Override
    public String getId() {
        return generateID(this);
    }

    @Override
    public String generateID(Employee employee) {
        return "A" +
                employee.getFirstName().substring(0, 1) +
                employee.getLastName().substring(0, 1) +
                employee.getPhoneNumber().hashCode();
    }
}
