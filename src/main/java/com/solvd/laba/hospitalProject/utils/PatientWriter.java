package com.solvd.laba.hospitalProject.utils;

import com.solvd.laba.hospitalProject.people.patients.Patient;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PatientWriter {

    public static final Logger LOGGER = LogManager.getLogger(PatientWriter.class);
    private static Path fileName;
    public static final String path = "src/main/resources/patient_card";

    public static void patientWrite(Patient patient) {

        fileName = Paths.get(path + patient.getLastName() + "." + patient.getFirstName() + ".txt");

        List<String> infoList = new ArrayList<>();
        infoList.add(StringUtils.capitalize(patient.getFirstName()));
        infoList.add(StringUtils.capitalize(patient.getLastName()));
        infoList.add(StringUtils.defaultString(patient.getPhoneNumber()));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName.toFile()))) {
            for (String line : infoList) {
                writer.write(line);
                writer.newLine();
            }
            LOGGER.info("Patient write successful");
        } catch (IOException e) {
            String errorMessage = "Error writing patient information for: " + patient.getLastName() + ", " + patient.getFirstName();
            LOGGER.error(errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        }
    }
}

