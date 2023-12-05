package com.solvd.laba.hospitalProject.departments;

import com.solvd.laba.hospitalProject.enums.CleanProgress;
import com.solvd.laba.hospitalProject.enums.SeatingCapacityLevel;
import com.solvd.laba.hospitalProject.people.employees.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class Cafeteria extends Department {

    public static final Logger LOGGER = LogManager.getLogger(Cafeteria.class);

    private SeatingCapacityLevel seatingCapacityLevel;
    private CleanProgress cleanProgress;

    public Cafeteria(String name, LocalDate establishedDate, Set<Employee> employees, SeatingCapacityLevel seatingCapacityLevel) {
        super(name, establishedDate, employees);
        this.seatingCapacityLevel = seatingCapacityLevel;
        LOGGER.info("Created a new Cafeteria");
    }

    public Cafeteria(String name, LocalDate establishedDate, SeatingCapacityLevel seatingCapacityLevel) {
        super(name, establishedDate);
        this.seatingCapacityLevel = seatingCapacityLevel;
        LOGGER.info("Created a new Cafeteria");
    }

    public SeatingCapacityLevel getSeatingCapacityLevel() {
        return seatingCapacityLevel;
    }

    public void setSeatingCapacityLevel(SeatingCapacityLevel seatingCapacityLevel) {
        LOGGER.info("Set seating capacity level to " + seatingCapacityLevel.getLevelDescription());
        this.seatingCapacityLevel = seatingCapacityLevel;
    }

    public CleanProgress getCleanProgress() {
        return cleanProgress;
    }

    public void setCleanProgress(CleanProgress cleanProgress) {
        LOGGER.info("Set cleanliness progress to " + cleanProgress.getProgressMessage());
        this.cleanProgress = cleanProgress;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void performGeneralDuties() {
        LOGGER.info("Cafeteria is preparing and serving meals.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cafeteria cafeteria = (Cafeteria) o;
        return seatingCapacityLevel == cafeteria.seatingCapacityLevel && cleanProgress == cafeteria.cleanProgress;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), seatingCapacityLevel, cleanProgress);
    }

    @Override
    public String toString() {
        return "Cafeteria{" +
                "seatingCapacityLevel=" + seatingCapacityLevel +
                ", cleanProgress=" + cleanProgress +
                '}';
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        return 0;
    }

    @Override
    public void displayInfo() {
        LOGGER.info("Displaying information about the hospital cafeteria.");
    }

    @Override
    public void showDetails() {
        LOGGER.info("Showing details of the cafeteria.");
    }

    @Override
    public void printInfo() {
        LOGGER.info("Printing information about the cafeteria.");
    }
}
