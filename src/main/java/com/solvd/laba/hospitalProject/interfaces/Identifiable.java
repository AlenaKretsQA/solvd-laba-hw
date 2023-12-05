package com.solvd.laba.hospitalProject.interfaces;

import com.solvd.laba.hospitalProject.people.employees.Employee;

public interface Identifiable {

    String getId();

    String generateID(Employee employee);
}
