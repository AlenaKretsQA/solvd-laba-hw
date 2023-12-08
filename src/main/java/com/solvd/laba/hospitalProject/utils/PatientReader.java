package com.solvd.laba.hospitalProject.utils;

import com.solvd.laba.hospitalProject.enums.DiseaseType;
import com.solvd.laba.hospitalProject.exceptions.InvalidPersonException;
import com.solvd.laba.hospitalProject.people.patients.Patient;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PatientReader {

    public static final Logger LOGGER = LogManager.getLogger(PatientReader.class);
    public static final String path = "src/main/resources/patient_card";
    public static Patient patientRead(String patientName) {

        Path fileName = Paths.get(path + patientName + ".txt");
        Patient readPatient = null;

        try (BufferedReader reader = Files.newBufferedReader(fileName)) {
            // Read lines from the file
            List<String> infoList = Files.readAllLines(fileName);

            if (infoList.size() != 4) {
                String errorMessage = "File structure is not correct for patient: " + patientName;
                LOGGER.error(errorMessage);
                throw new RuntimeException(errorMessage);
            }

            //StringUtils
            String firstName = StringUtils.trimToEmpty(infoList.get(0));
            String lastName = StringUtils.trimToEmpty(infoList.get(1));
            String phoneNumber = StringUtils.trimToEmpty(infoList.get(2));
            String disease = StringUtils.trimToEmpty(infoList.get(3));

            readPatient = new Patient(firstName, lastName, phoneNumber, DiseaseType.DIABETES);
        } catch (FileNotFoundException e) {
            String errorMessage = "File not found for patient: " + patientName;
            LOGGER.error(errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        } catch (IOException e) {
            String errorMessage = "Error reading file for patient: " + patientName;
            LOGGER.error(errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        } catch (InvalidPersonException e) {
            String errorMessage = "Invalid person information for patient: " + patientName;
            LOGGER.error(errorMessage, e);
            throw new RuntimeException(errorMessage, e);
        }
        return readPatient;
    }
}
