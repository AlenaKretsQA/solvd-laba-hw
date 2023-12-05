package com.solvd.laba.hospitalProject.people.employees;

import com.solvd.laba.hospitalProject.enums.SalaryType;
import com.solvd.laba.hospitalProject.people.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.laba.hospitalProject.exceptions.InvalidPersonException;

import java.util.Objects;

public abstract class Employee extends Person {

    public static final Logger LOGGER = LogManager.getLogger(Employee.class);

    protected Integer salary;
    protected SalaryType salaryType;
    protected Integer age;

    public Employee(String firstName, String lastName, String phoneNumber) throws InvalidPersonException {
        super(firstName, lastName, phoneNumber);
    }

    public Employee(String firstName, String lastName, String phoneNumber, Integer salary, Integer age) throws InvalidPersonException {
        this(firstName, lastName, phoneNumber);
        this.salary = salary;
        this.age = age;
        this.salaryType = salaryType;
    }
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public SalaryType getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(SalaryType salaryType) {
        this.salaryType = salaryType;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(salary, employee.salary) && Objects.equals(age, employee.age);
    }

    @Override
    public String toString() {
        return "Employee{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                "salaryType=" + salaryType +
                ", salary=" + salary +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary, age);
    }

    public abstract void work();
}
