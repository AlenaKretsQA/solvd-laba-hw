package com.solvd.laba.hospitalProject.departments;

import com.solvd.laba.hospitalProject.interfaces.Printable;
import com.solvd.laba.hospitalProject.people.employees.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.laba.hospitalProject.interfaces.Displayable;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Department implements Displayable, Printable {

    public static final Logger LOGGER = LogManager.getLogger(Department.class);
    protected String name;
    protected LocalDate establishedDate;
    protected Set<Employee> employees;

    public Department(String name, LocalDate establishedDate) {
        this.name = name;
        this.establishedDate = establishedDate;
        this.employees = new HashSet<>();
    }

    public Department(String name, LocalDate establishedDate, Set<Employee> employees) {
        this.name = name;
        this.establishedDate = establishedDate;
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(LocalDate establishedDate) {
        this.establishedDate = establishedDate;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee newEmployee) {
        employees.add(newEmployee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    // Abstract method for performing general duties in each department
    public abstract void performGeneralDuties();

    public abstract int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name) && Objects.equals(establishedDate, that.establishedDate) && Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, establishedDate, employees);
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", establishedDate=" + establishedDate +
                ", employees=" + employees +
                '}';
    }

    @Override
    public void displayInfo() {
        System.out.println("Department Information:");
        System.out.println("Established Date: " + getEstablishedDate());
    }

    @Override
    public void printInfo() {
        System.out.println("Printing Department Information:");
        System.out.println("Established Date: " + getEstablishedDate());
    }

    @Override
    public void printDetails() {
        System.out.println("Department:");
        System.out.println("Name: " + getName());
        System.out.println("Established Date: " + getEstablishedDate());
    }

    @Override
    public void showDetails() {
        System.out.println("Department Details:");
        System.out.println("Name: " + getName());
        System.out.println("Established Date: " + getEstablishedDate());
        System.out.println("Employees: " + getEmployees());
    }
}
