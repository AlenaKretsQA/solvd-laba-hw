package com.solvd.laba.hospitalProject.utils;

import com.solvd.laba.hospitalProject.Hospital;
import com.solvd.laba.hospitalProject.people.employees.Employee;
import com.solvd.laba.hospitalProject.departments.MedicalDepartment;
import com.solvd.laba.hospitalProject.people.patients.Patient;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class HospitalUtility {

    // Utility method to print information about the hospital
    public static void printHospitalInfo(Hospital hospital) {
        System.out.println("Hospital Information:");
        System.out.println("Name: " + hospital.getName());
        System.out.println("Location: " + hospital.getLocation());
        System.out.println("Medical Departments: " + Arrays.toString(new List[]{hospital.getMedicalDepartments()}));
        System.out.println("Cafeterias: " + Arrays.toString(new List[]{hospital.getCafeterias()}));
        System.out.println("Pharmacies: " + Arrays.toString(new List[]{hospital.getPharmacies()}));
        System.out.println("Laboratories: " + Arrays.toString(new List[]{hospital.getLaboratories()}));
        System.out.println("Patients: " + Arrays.toString(new Set[]{hospital.getPatients()}));
        System.out.println("Employees: " + Arrays.toString(new List[]{hospital.getEmployees()}));
    }

    // Utility method to add a new patient to a specific medical department
    public static void addPatientToMedicalDepartment(Hospital hospital, String departmentName, Patient patient) {
        MedicalDepartment[] medicalDepartments = hospital.getMedicalDepartments().toArray(new MedicalDepartment[0]);
        for (MedicalDepartment department : medicalDepartments) {
            if (department.getName().equals(departmentName)) {
                department.addPatient(patient);
                System.out.println("Patient added to " + departmentName + " department.");
                return;
            }
        }
        System.out.println("Medical department with name " + departmentName + " not found.");
    }
    public static void printEmployeeList(Hospital hospital) {
        List<Employee> employees;
        employees = null;
        Arrays.sort(new List[]{hospital.getEmployees()});
        employees.forEach(employee -> System.out.println(employee.getLastName()));
    }
}