package com.solvd.laba.hospitalProject.departments;

import com.solvd.laba.hospitalProject.enums.LaboratoryType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.laba.hospitalProject.exceptions.LaboratoryInvalidEquipmentNameException;
import com.solvd.laba.hospitalProject.exceptions.LaboratoryInvalidQuantityEquipmentException;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.time.LocalDate;
import java.util.*;

public class Laboratory extends Department {

    public static final Logger LOGGER = LogManager.getLogger(Laboratory.class);

    private LaboratoryType type;  // clinical, commercial, hematology, microbiology, etc.
    private Map<String, Integer> equipment;

    public Laboratory(String name, LocalDate establishedDate, LaboratoryType type) {
        super(name, establishedDate);
        this.type = type;
        this.equipment = new HashMap<>();
    }

    public void changeLabType(LaboratoryType newType) {
        this.type = newType;
    }

    public LaboratoryType getType() {
        return type;
    }

    public void setType(LaboratoryType type) {
        this.type = type;
    }

    public Map<String, Integer> getEquipment() {
        return equipment;
    }

    public void setEquipment(Map<String, Integer> equipment) {
        this.equipment = equipment;
    }

    public void addNewLabEquipment(String name, int quantity) {
        equipment.put(name, quantity);
        LOGGER.info("Equipment " + name + " added in quantity of " + quantity);
    }

    public void removeLabEquipment(String name, int quantity) throws LaboratoryInvalidQuantityEquipmentException, LaboratoryInvalidEquipmentNameException {
        if (!equipment.containsKey(name)) {
            throw new LaboratoryInvalidEquipmentNameException("Wrong equipment name");
        }

        int equipNowNumber = equipment.get(name);
        if (equipNowNumber > quantity) {
            equipment.put(name, equipNowNumber - quantity);
        } else {
            throw new LaboratoryInvalidQuantityEquipmentException("Not this number of equipment");
        }
    }

    @Override
    public void performGeneralDuties() {
        LOGGER.info("Laboratory is conducting experiments and research.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Laboratory that = (Laboratory) o;
        return Objects.equals(type, that.type) && Objects.equals(equipment, that.equipment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, equipment);
    }

    @Override
    public String toString() {
        return "Laboratory{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", equipment=" + equipment +
                '}';
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        return 0;
    }

    @Override
    public void displayInfo() {
        LOGGER.info("Displaying information about the laboratory.");
    }

    @Override
    public void showDetails() {
        LOGGER.info("Showing details of the laboratory.");
    }

    @Override
    public void printInfo() {
        LOGGER.info("Printing information about the laboratory.");
    }
}
