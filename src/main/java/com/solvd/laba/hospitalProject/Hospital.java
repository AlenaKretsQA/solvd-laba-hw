package com.solvd.laba.hospitalProject;

import com.solvd.laba.hospitalProject.departments.*;
import com.solvd.laba.hospitalProject.people.employees.Employee;
import com.solvd.laba.hospitalProject.interfaces.Displayable;
import com.solvd.laba.hospitalProject.interfaces.Printable;
import com.solvd.laba.hospitalProject.people.patients.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hospital implements Displayable, Printable {

    public static final Logger LOGGER = LogManager.getLogger(Hospital.class);

    private final String name;
    private final String location;

    private List<Cafeteria> cafeterias;
    private List<Pharmacy> pharmacies;
    private List<Laboratory> laboratories;
    private List<MedicalDepartment> medicalDepartments;

    private Set<Patient> patients;
    private List<Employee> employees;

    public Hospital(String name, String location, List<MedicalDepartment> medicalDepartments, List<Cafeteria> cafeterias, List<Pharmacy> pharmacies, List<Laboratory> laboratories, Set<Patient> patients, List<Employee> employees) {
        LOGGER.info("Create a new Hospital");
        this.name = name;
        this.location = location;

        this.cafeterias = cafeterias;
        this.laboratories = laboratories;
        this.pharmacies = pharmacies;
        this.medicalDepartments = medicalDepartments;

        this.patients = patients;
        this.employees = employees;
    }

    public Hospital(String name, String location) {
        this.name = name;
        this.location = location;

        this.cafeterias = new ArrayList<>();
        this.laboratories = new ArrayList<>();
        this.pharmacies = new ArrayList<>();
        this.medicalDepartments = new ArrayList<>();
        this.patients = new HashSet<>();
        this.employees = new ArrayList<>();
    }

    public List<Cafeteria> getCafeterias() {
        return cafeterias;
    }

    public List<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public List<Laboratory> getLaboratories() {
        return laboratories;
    }

    public List<MedicalDepartment> getMedicalDepartments() {
        return medicalDepartments;
    }

    public void setCafeterias(List<Cafeteria> cafeterias) {
        this.cafeterias = cafeterias;
    }

    public void setPharmacies(List<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }

    public void setLaboratories(List<Laboratory> laboratories) {
        this.laboratories = laboratories;
    }

    public void setMedicalDepartments(List<MedicalDepartment> medicalDepartments) {
        this.medicalDepartments = medicalDepartments;
    }

    public void addPharmacy(Pharmacy pharmacy) {
        this.pharmacies.add(pharmacy);
    }

    public void addLaboratory(Laboratory lab) {
        this.laboratories.add(lab);
    }

    public void addCafeteria(Cafeteria cafe) {
        this.cafeterias.add(cafe);
    }

    public void addMedicalDepartment(MedicalDepartment medDep) {
        this.medicalDepartments.add(medDep);
    }

    public void addPatient(Patient newPatient) {
        this.patients.add(newPatient);
    }

    public void dischargePatient(Patient patient) {
        this.patients.remove(patient);
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        this.employees.remove(employee);
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public void displayInfo() {
        System.out.println("Hospital Information:");
        System.out.println("Name: " + getName());
        System.out.println("Location: " + getLocation());
    }

    @Override
    public void showDetails() {
        System.out.println("Hospital Details:");
        System.out.println("Name: " + getName());
        System.out.println("Location: " + getLocation());
        System.out.println("Cafeterias: " + getCafeterias());
        System.out.println("Pharmacies: " + getPharmacies());
        System.out.println("Laboratories: " + getLaboratories());
    }

    @Override
    public void printInfo() {
        System.out.println("Printing Hospital Information:");
        System.out.println("Name: " + getName());
        System.out.println("Location: " + getLocation());
    }

    @Override
    public void printDetails() {
        System.out.println("Printing Hospital Details:");
        System.out.println("Name: " + getName());
        System.out.println("Location: " + getLocation());
        System.out.println("Cafeterias: " + getCafeterias());
        System.out.println("Pharmacies: " + getPharmacies());
        System.out.println("Laboratories: " + getLaboratories());
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", cafeterias=" + cafeterias +
                ", pharmacies=" + pharmacies +
                ", laboratories=" + laboratories +
                ", medicalDepartments=" + medicalDepartments +
                ", patients=" + patients +
                ", employees=" + employees +
                '}';
    }
}
