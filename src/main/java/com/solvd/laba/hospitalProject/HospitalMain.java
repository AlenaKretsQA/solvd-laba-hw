package com.solvd.laba.hospitalProject;

import com.solvd.laba.hospitalProject.enums.*;
import com.solvd.laba.hospitalProject.departments.*;
import com.solvd.laba.hospitalProject.exceptions.InvalidPersonException;
import com.solvd.laba.hospitalProject.exceptions.LaboratoryInvalidEquipmentNameException;
import com.solvd.laba.hospitalProject.exceptions.LaboratoryInvalidQuantityEquipmentException;
import com.solvd.laba.hospitalProject.interfaces.ChangeName;
import com.solvd.laba.hospitalProject.people.employees.Employee;
import com.solvd.laba.hospitalProject.people.employees.Nurse;
import com.solvd.laba.hospitalProject.people.patients.Patient;
import com.solvd.laba.hospitalProject.utils.PatientReader;
import com.solvd.laba.hospitalProject.utils.PatientWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.laba.hospitalProject.people.employees.Administrator;
import com.solvd.laba.hospitalProject.people.employees.Doctor;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class HospitalMain {

    public static final Logger LOGGER = LogManager.getLogger(HospitalMain.class);

    // Function interface for Predicate
    private static final Predicate<Integer> isPositiveNumber = num -> num > 0;

    public static void main(String[] args) throws InvalidPersonException {

        LOGGER.info("Welcome to Hospital application!");

        // Create employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Administrator("John", "Doe", "123-456-7890", 50000, 35, "MBA", true));
        employees.add(new Doctor("Jane", "Smith", "987-654-3210", 80000, 40, "Cardiology", "Active"));
        employees.add(new Nurse("Alice", "Johnson", "111-222-3333", 30000, 25, 1, "Pediatrics", true));


        // Create patients
        Set<Patient> patients = new HashSet<>();
        Patient newPatient = new Patient("Tom", "Jones", "555-123-4567", DiseaseType.DIABETES);
        patients.add(newPatient);
        PatientWriter.patientWrite(newPatient);

        newPatient = new Patient("Mark", "Ivanoff", "123-123-4567", DiseaseType.ALLERGIES);
        patients.add(newPatient);
        PatientWriter.patientWrite(newPatient);

        newPatient = new Patient("Alice", "Petroff", "888-100-4567", DiseaseType.HEART_DISEASE);
        patients.add(newPatient);
        PatientWriter.patientWrite(newPatient);


        // Create Cafeterias
        List<Cafeteria> cafeterias = new ArrayList<>();
        cafeterias.add(new Cafeteria("Main_Cafe", LocalDate.now(), SeatingCapacityLevel.SMALL));
        cafeterias.add(new Cafeteria("Small_Cafe", LocalDate.now(), SeatingCapacityLevel.MEDIUM));

        // Create Pharmacies
        List<Pharmacy> pharmacies = new ArrayList<>();
        pharmacies.add(new Pharmacy("Main_Pharmacy", LocalDate.now()));

        // Create Labs
        List<Laboratory> laboratories = new ArrayList<>();
        laboratories.add(new Laboratory("Lab1", LocalDate.now(), LaboratoryType.CLINICAL));
        Laboratory laboratory = new Laboratory("Lab2", LocalDate.now(), LaboratoryType.CLINICAL);
        laboratories.add(laboratory);

        // create medical dep - Cardiology
        MedicalDepartment cardiology = new MedicalDepartment("Cardiology", LocalDate.now(), true);
        cardiology.setPatients(patients);

        // Create departments
        List<MedicalDepartment> medicalDepartments = new ArrayList<>();
        medicalDepartments.add(cardiology);


        // Create a hospital
        Hospital hospital = new Hospital("General Hospital", "City Center", medicalDepartments,
                cafeterias, pharmacies, laboratories, patients, employees);

        // Example 1: Print information about the hospital
        hospital.displayInfo();  // Assuming the Hospital class implements the Displayable interface
        System.out.println(hospital);

        // Example 2: Print the inventory of the main pharmacy
        hospital.getPharmacies().get(0).addPharmInventory("Aspirin", 10);
        hospital.getPharmacies().get(0).addPharmInventory("Nurofen", 11);
        hospital.getPharmacies().get(0).addPharmInventory("Advil", 40);

        LOGGER.info("Our updates Hospital :" + hospital);
        LOGGER.info("Main Pharmacy inventory: " + hospital.getPharmacies().get(0).getInventory());

        try (Scanner scanner = new Scanner(System.in)) {
            int action;
            do {

                System.out.println("HOSPITAL ACTION:");
                System.out.println("1. Cafeteria ");
                System.out.println("2. Pharmacy ");
                System.out.println("3. Laboratory ");
                System.out.println("4. Medical Department ");
                System.out.println("5. Patients ");
                System.out.println("6. Employees ");
                System.out.println("7. Hospital Info print");
                System.out.println("0. EXIT");
                System.out.print("ENTER YOUR ACTION --> ");

                action = scanner.nextInt();

                if (action == 1) { // CAFETERIA

                    Predicate<Integer> isCafeActionValid = act -> act >= 1 && act <= 5;
                    Predicate<Cafeteria> cafeExists = Objects::nonNull;

                    Supplier<LocalDate> getCurrentDate = LocalDate::now;

                    Function<String, Cafeteria> findCafeteria = cafeName -> hospital.getCafeterias().stream()
                            .filter(each -> each.getName().equals(cafeName))
                            .findFirst()
                            .orElse(null);

                    Consumer<Cafeteria> addCafeteria = newCafe -> hospital.addCafeteria(newCafe);


                    System.out.println("1. Open New one");
                    System.out.println("2. Rename");
                    System.out.println("3. Change the Capacity");
                    System.out.println("4. Clean");
                    System.out.println("5. Print Cafeteria List");
                    System.out.print("Choose Cafeteria Action: ");
                    int actionCafe = scanner.nextInt();

                    if (isCafeActionValid.test(actionCafe)) {
                        System.out.print("Enter the Cafeteria Name: ");
                        String cafeName = scanner.next();

                        Cafeteria currentCafe = findCafeteria.apply(cafeName);

                        if ((actionCafe >= 2 && !cafeExists.test(currentCafe)) || currentCafe == null) {
                            LOGGER.info("No this Cafeteria Name: " + cafeName + " in the list of departments. Nothing changed");
                            break;
                        }

                        switch (actionCafe) {
                            case 1:
                                System.out.print("Enter the Capacity (number of seats): ");
                                int capacity = scanner.nextInt();
                                Cafeteria newCafe = new Cafeteria(cafeName, getCurrentDate.get(), SeatingCapacityLevel.SMALL);
                                addCafeteria.accept(newCafe);
                                break;
                            case 2:
                                System.out.print("Enter the new Name: ");
                                String newName = scanner.next();
                                currentCafe.setName(newName);
                                LOGGER.info("Cafeteria Name: " + cafeName + " has been changed to " + newName);
                                break;
                            case 3:
                                System.out.print("Enter the new Capacity: ");
                                capacity = scanner.nextInt();
                                currentCafe.setSeatingCapacityLevel(SeatingCapacityLevel.MEDIUM);
                                break;
                            case 4:
                                LOGGER.info("Cafeteria Name: " + cafeName + " has been cleaned ");
                                currentCafe.setCleanProgress(CleanProgress.COMPLETED);
                                break;
                            case 5:
                                System.out.print("Cafeteria List: " + hospital.getCafeterias());
                                break;
                        }
                    }
                } else if (action == 2) {  // PHARMACY
                    System.out.println("1. Open New one");
                    System.out.println("2. Rename");
                    System.out.println("3. Add Inventory");
                    System.out.println("4. Remove Inventory");
                    System.out.println("5. Print Pharmacy List");
                    System.out.print("Choose Pharmacy Action: ");
                    int actionPharmacy = scanner.nextInt();

                    if (actionPharmacy >= 1 && actionPharmacy <= 4) {
                        System.out.print("Enter the Pharmacy Name: ");
                        String pharmName = scanner.next();

                        Pharmacy currentPharm = hospital.getPharmacies().stream()
                                .filter(each -> each.getName().equals(pharmName))
                                .findFirst()
                                .orElse(null);

                        if (actionPharmacy >= 2 && actionPharmacy <= 4 && currentPharm == null) {
                            LOGGER.info("No this Pharmacy Name: " + pharmName + " in the list of departments. Nothing changed");
                            break;
                        }

                        switch (actionPharmacy) {
                            case 1:
                                LocalDate now = LocalDate.now();
                                Pharmacy newPharma = new Pharmacy(pharmName, now);
                                hospital.addPharmacy(newPharma);
                                break;
                            case 2:
                                System.out.print("Enter the new Name: ");
                                String newName = scanner.next();
                                ChangeName changeName = Pharmacy::setName;                  // use Function interface
                                changeName.changeName(currentPharm, "New Name");
                                LOGGER.info("Pharmacy Name: " + pharmName + " has been changed to " + newName);
                                break;
                            case 3:
                                System.out.print("Enter the new Pharmacy Inventory: ");
                                String newInvent = scanner.next();
                                System.out.print("Enter the Inventory Quantity: ");
                                int newQuant = scanner.nextInt();
                                currentPharm.addPharmInventory(newInvent, newQuant);
                                break;
                            case 4:
                                System.out.print("Enter the name Inventory to remove: ");
                                String remInvent = scanner.next();
                                System.out.print("Enter the Inventory Quantity: ");
                                int remQuant = scanner.nextInt();
                                currentPharm.removePharmInventory(remInvent, remQuant);
                                break;
                            case 5:
                                System.out.println("Pharmacy List: " + hospital.getPharmacies());
                                break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + actionPharmacy);
                        }
                    }
                } else if (action == 3) {  // LABORATORY
                    System.out.println("1. Add new equipment");
                    System.out.println("2. Remove equipment");
                    System.out.println("3. Change lab type");
                    System.out.println("4. List of equipment");
                    System.out.print("Choose Lab Action: ");
                    int actionLab = scanner.nextInt();

                    switch (actionLab) {
                        case 1:
                            System.out.print("Enter Lab name: ");
                            String labName = scanner.next();
                            System.out.print("Enter equipment name: ");
                            String equipName = scanner.next();
                            System.out.print("Enter equipment quantity: ");
                            int quant = scanner.nextInt();

                            laboratories.stream()
                                    .filter(each -> each.getName().equals(labName))
                                    .findFirst()
                                    .ifPresentOrElse(
                                            lab -> {
                                                try {
                                                    lab.addNewLabEquipment(equipName, quant);
                                                    LOGGER.info("New equipment added: " + equipName + " - Quantity: " + quant);
                                                } catch (Exception e) {
                                                    LOGGER.error("An unexpected error occurred during adding new equipment: " + e.getMessage());
                                                }
                                            },
                                            () -> LOGGER.error("No Lab name found")
                                    );
                                break;
                        case 2:
                            System.out.print("Enter Lab name: ");
                            labName = scanner.next();
                            System.out.print("Enter equipment name: ");
                            equipName = scanner.next();
                            System.out.print("Enter equipment quantity to remove: ");
                            quant = scanner.nextInt();

                            laboratories.stream()
                                    .filter(each -> each.getName().equals(labName))
                                    .findFirst()
                                    .ifPresentOrElse(
                                            lab -> {
                                                Map<String, Integer> equipment = lab.getEquipment();
                                                if (equipment.containsKey(equipName)) {
                                                    try {
                                                        lab.removeLabEquipment(equipName, quant);
                                                        LOGGER.info("Equipment removed: " + equipName + " - Quantity: " + quant);
                                                    } catch (LaboratoryInvalidQuantityEquipmentException e) {
                                                        LOGGER.error("Invalid quantity for equipment removal: " + e.getMessage());
                                                    } catch (LaboratoryInvalidEquipmentNameException e) {
                                                        LOGGER.error("Invalid equipment name for removal: " + e.getMessage());
                                                    } catch (Exception e) {
                                                        LOGGER.error("An unexpected error occurred during equipment removal: " + e.getMessage());
                                                    }
                                                }  else {
                                                    LOGGER.error("Equipment not found in the lab: " + equipName);
                                                }
                                            },
                                            () -> LOGGER.error("No Lab name found")
                                    );
                            break;
                        case 3:
                            System.out.print("Enter Lab name: ");
                            labName = scanner.next();
                            System.out.print("Enter new Lab type: ");
                            String newLabTypeString = scanner.next();

                            LaboratoryType newLabType = LaboratoryType.valueOf(newLabTypeString.toUpperCase());

                            laboratories.stream()
                                    .filter(each -> each.getName().equals(labName))
                                    .findFirst()
                                    .ifPresentOrElse(
                                            lab -> lab.changeLabType(newLabType),
                                            () -> LOGGER.error("No Lab name found")
                                    );
                            break;
                        case 4:
                            System.out.print("Enter Lab name: ");
                            labName = scanner.next();

                            laboratories.stream()
                                    .filter(each -> each.getName().equals(labName))
                                    .findFirst()
                                    .ifPresentOrElse(
                                            lab -> {
                                                Map<String, Integer> equipment = lab.getEquipment();
                                                if (equipment.isEmpty()) {
                                                    LOGGER.info("No equipment found for this lab. Adding new entry.");

                                                    // Add new entry to the equipment map
                                                    System.out.print("Enter new equipment name: ");
                                                    String newEquipName = scanner.next();
                                                    System.out.print("Enter quantity for the new equipment: ");
                                                    int newEquipQuantity = scanner.nextInt();

                                                    lab.addNewLabEquipment(newEquipName, newEquipQuantity);

                                                    LOGGER.info("New equipment added: " + newEquipName + " - Quantity: " + newEquipQuantity);
                                                } else {
                                                    System.out.println("Equipment list for " + lab.getName() + ":");
                                                    equipment.forEach((key, value) -> System.out.println(key + " - Quantity: " + value));
                                                }
                                            },
                                            () -> LOGGER.error("No Lab name found")
                                    );
                            break;
                    }
                } else if (action == 4) {  // Medical Department
                    System.out.println("1. Add New Medical Department");
                    System.out.println("2. List of Medical Departments");
                    System.out.print("Choose Medical Department Action: ");
                    int actionMedicalDepartment = scanner.nextInt();

                    switch (actionMedicalDepartment) {
                        case 1:
                            System.out.print("Enter Medical Department Name: ");
                            String departmentName = scanner.next();
                            System.out.print("Does it have Emergency Services? (true/false): ");
                            boolean emergencyServices = scanner.nextBoolean();

                            MedicalDepartment newDepartment = new MedicalDepartment(departmentName, LocalDate.now(), emergencyServices);
                            hospital.addMedicalDepartment(newDepartment);
                            LOGGER.info("New Medical Department added: " + newDepartment);
                            break;

                        case 2:
                            System.out.println("List of Medical Departments: " + hospital.getMedicalDepartments());
                            break;

                        default:
                            LOGGER.error("Invalid action for Medical Department.");
                            break;
                    }

                } else if (action == 5) {  // Patients
                    System.out.println("1. Create the new patient");
                    System.out.println("2. Discharge the patient");
                    System.out.println("3. Patient disease info");
                    System.out.println("4. Change the patient disease");
                    System.out.println("5. Move the patient to other department");
                    System.out.println("6. List of the patients");
                    System.out.println("7. Read patient info");
                    System.out.print("Choose Patient Action: ");
                    int actionPatient = scanner.nextInt();

                    String fName, lName, phone, disease;
                    switch (actionPatient) {
                        case 1:
                            System.out.print("Enter the First name: ");
                            fName = scanner.next();
                            System.out.print("Enter the Last name: ");
                            lName = scanner.next();
                            System.out.print("Enter the Phone number: ");
                            phone = scanner.next();
                            System.out.print("Enter the disease: ");
                            disease = scanner.next();
                            newPatient = new Patient(fName, lName, phone, DiseaseType.HEART_DISEASE);
                            System.out.print("Enter the medical department: ");
                            String departName = scanner.next();

                            Patient finalNewPatient = newPatient;
                            boolean depExist = hospital.getMedicalDepartments().stream()
                                    .filter(dep -> dep.getName().equals(departName))
                                    .peek(dep -> dep.addPatient(finalNewPatient))
                                    .findFirst()
                                    .isPresent();

                            if (!depExist) {
                                LOGGER.error("Have no this department " + departName + " in the hospital");
                                throw new RuntimeException("No such department in the Hospital");
                            }
                            break;
                        case 2:
                            System.out.print("Enter the First name: ");
                            fName = scanner.next();
                            System.out.print("Enter the Last name: ");
                            lName = scanner.next();

                            hospital.getMedicalDepartments().stream()
                                    .flatMap(dep -> dep.getPatients().stream())
                                    .filter(patient -> patient.getLastName().equals(lName) && patient.getFirstName().equals(fName))
                                    .findFirst()
                                    .ifPresent(removedPatient -> {
                                        Optional<MedicalDepartment> first = Optional.empty();
                                        for (MedicalDepartment dep : hospital.getMedicalDepartments()) {
                                            if (dep.removePatient(removedPatient)) {
                                                first = Optional.of(dep);
                                                break;
                                            }
                                        }
                                        hospital.getPatients().remove(removedPatient);
                                    });
                            break;
                        case 3:
                            System.out.print("Enter the First name: ");
                            fName = scanner.next();
                            System.out.print("Enter the Last name: ");
                            lName = scanner.next();

                            hospital.getMedicalDepartments().stream()
                                    .flatMap(dep -> dep.getPatients().stream())
                                    .filter(each -> each.getLastName().equals(lName) && each.getFirstName().equals(fName))
                                    .findFirst()
                                    .ifPresentOrElse(
                                            desPatient -> System.out.println("Patient Disease: " + desPatient.getDisease()),
                                            () -> LOGGER.error("No such a patient in the hospital")
                                    );
                            break;
                        case 4:
                            System.out.print("Enter the First name: ");
                            fName = scanner.next();
                            System.out.print("Enter the Last name: ");
                            lName = scanner.next();

                            boolean patientExistDes = hospital.getMedicalDepartments().stream()
                                    .flatMap(dep -> dep.getPatients().stream())
                                    .anyMatch(each -> each.getLastName().equals(lName) && each.getFirstName().equals(fName));

                            if (patientExistDes) {
                                System.out.print("Enter the new Disease: ");
                                String newDisease = scanner.next();

                                hospital.getMedicalDepartments().stream()
                                        .flatMap(dep -> dep.getPatients().stream())
                                        .filter(each -> each.getLastName().equals(lName) && each.getFirstName().equals(fName))
                                        .forEach(changePatient -> changePatient.setDisease(DiseaseType.CANCER));
                            } else {
                                LOGGER.error("No such a patient in the hospital");
                            }
                            break;
                        case 6:
                            System.out.println("Patient list: " + hospital.getPatients());
                            break;
                        case 7:
                            System.out.print("Enter the First name: ");
                            fName = scanner.next();
                            System.out.print("Enter the Last name: ");
                            lName = scanner.next();
                            Patient patientFromFile = PatientReader.patientRead(lName + "." + fName);
                            System.out.println("Patient from File " + patientFromFile);

                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + actionPatient);
                    }

                } else if (action == 6) {  // Employee
                    System.out.println("1. Add New Employee");
                    System.out.println("2. Remove Employee");
                    System.out.println("3. Update Employee Salary");
                    System.out.println("4. Update Employee Age");
                    System.out.println("5. List of Employees older then");
                    System.out.println("6. List of Employees");
                    System.out.print("Choose Employee Action: ");
                    int actionEmployee = scanner.nextInt();

                    switch (actionEmployee) {
                        case 1:
                            System.out.println("Select Employee Type:");
                            System.out.println("1. Doctor");
                            System.out.println("2. Nurse");
                            System.out.print("Enter Employee Type: ");
                            int employeeType = scanner.nextInt();

                            System.out.print("Enter First Name: ");
                            String empFirstName = scanner.next();
                            System.out.print("Enter Last Name: ");
                            String empLastName = scanner.next();
                            System.out.print("Enter Phone Number: ");
                            String empPhoneNumber = scanner.next();
                            System.out.print("Enter Salary: ");
                            int empSalary = scanner.nextInt();
                            System.out.print("Enter Age: ");
                            int empAge = scanner.nextInt();

                            Employee newEmployee = null;

                            if (employeeType == 1) { // Doctor
                                System.out.print("Enter Specialization: ");
                                String specialization = scanner.next();
                                System.out.print("Enter Doctor Status: ");
                                String doctorStatus = scanner.next();

                                newEmployee = new Doctor(empFirstName, empLastName, empPhoneNumber, empSalary, empAge, specialization, doctorStatus);
                            } else if (employeeType == 2) { // Nurse
                                System.out.print("Enter Shift: ");
                                int shift = scanner.nextInt();
                                System.out.print("Enter Category: ");
                                String category = scanner.next();
                                System.out.print("Is Certified (true/false): ");
                                boolean isCertified = scanner.nextBoolean();

                                newEmployee = new Nurse(empFirstName, empLastName, empPhoneNumber, empSalary, empAge, shift, category, isCertified);
                            }

                            if (newEmployee != null) {
                                hospital.addEmployee(newEmployee);
                                LOGGER.info("New employee added: " + newEmployee);
                            } else {
                                LOGGER.error("Invalid employee type selected.");
                            }
                            break;

                        case 2:
                            System.out.print("Enter the First name: ");
                            String fNameToRemove = scanner.next();
                            System.out.print("Enter the Last name: ");
                            String lNameToRemove = scanner.next();

                            Employee removedEmployee = hospital.getEmployees().stream()
                                    .filter(emp -> emp.getFirstName().equals(fNameToRemove) && emp.getLastName().equals(lNameToRemove))
                                    .findFirst()
                                    .orElse(null);

                            if (removedEmployee != null) {
                                hospital.removeEmployee(removedEmployee);
                                LOGGER.info("Employee removed: " + removedEmployee);
                            } else {
                                LOGGER.error("No such employee in the hospital.");
                            }
                            break;

                        case 3:
                            System.out.print("Enter the Last Name of the Employee to update salary: ");
                            String updateSalaryLastName = scanner.next();
                            System.out.print("Enter the new Salary: ");
                            int newSalary = scanner.nextInt();
                            System.out.print("Enter the Salary Type (HOURLY, MONTHLY, or ANNUAL): ");
                            String salaryTypeStr = scanner.next();
                            SalaryType salaryType;

                            try {
                                salaryType = SalaryType.valueOf(salaryTypeStr.toUpperCase());
                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid Salary Type. Please enter HOURLY, MONTHLY, or ANNUAL.");
                                break;
                            }

                            hospital.getEmployees().stream()
                                    .filter(emp -> emp.getLastName().equals(updateSalaryLastName))
                                    .findFirst()
                                    .ifPresent(emp -> {
                                        emp.setSalaryType(salaryType);
                                        emp.setSalary(newSalary);
                                        LOGGER.info("Salary updated for employee: " + emp);
                                    });
                            break;

                        case 4:
                            System.out.print("Enter the Last Name of the Employee to update age: ");
                            String updateAgeLastName = scanner.next();
                            System.out.print("Enter the new Age: ");
                            int newAge = scanner.nextInt();

                            hospital.getEmployees().stream()
                                    .filter(emp -> emp.getLastName().equals(updateAgeLastName))
                                    .findFirst()
                                    .ifPresent(emp -> {
                                        emp.setAge(newAge);
                                        LOGGER.info("Age updated for employee: " + emp);
                                    });
                            break;

                        case 5:
                            System.out.print("Enter the from age: ");
                            int fromAge = scanner.nextInt();
                            System.out.print("Enter the to age: ");
                            int toAge = scanner.nextInt();

                            Function<Employee, Boolean> isAgeBetween = emp -> {
                                int age = emp.getAge();
                                return age >= fromAge && age <= toAge;
                            };

                            // Using Java Streams to print information about employees
                            employees.forEach(emp -> {
                                boolean isWithinRange = isAgeBetween.apply(emp);
                                System.out.println(emp.getFirstName() + " " + emp.getLastName() +
                                        " - Is age between 30 and 60? " + isWithinRange);
                            });
                            break;
                        case 6:
                            System.out.println("List of Employees: " + hospital.getEmployees());
                            break;
                    }

                } else if (action == 7) {  // HOSPITAL INFO
                    System.out.println(hospital);
                }

            } while (action != 0);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
