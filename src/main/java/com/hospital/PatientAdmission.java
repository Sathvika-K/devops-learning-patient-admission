package main.java.com.hospital;
import java.io.*;
import java.util.*;
class Patient {
	private int id;
	private String name;
	private int age;
	private String disease;
	public Patient(int id, String name, int age, String disease) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.disease = disease;
		}

	    @Override
	    public String toString() {
	        return id + ", " + name + ", " + age + ", " + disease;
	    }
	}

	public class PatientAdmission {
	    private static final String FILE_NAME = "patients.txt";
	    private static final Scanner scanner = new Scanner(System.in);

	    public static void main(String[] args) {
	        while (true) {
	            System.out.println("\nPatient Admission System");
	            System.out.println("1. Add Patient");
	            System.out.println("2. Display Patients");
	            System.out.println("3. Search Patient by ID");
	            System.out.println("4. Exit");
	            System.out.print("Enter your choice: ");
	            
	            int choice;
	            try {
	                choice = scanner.nextInt();
	                scanner.nextLine(); // Consume newline
	            } catch (InputMismatchException e) {
	                System.out.println(" Invalid input! Please enter a number.");
	                scanner.nextLine(); // Clear the invalid input
	                continue;
	            }

	            switch (choice) {
	                case 1:
	                    addPatient();
	                    break;
	                case 2:
	                    displayPatients();
	                    break;
	                case 3:
	                    searchPatientById();
	                    break;
	                case 4:
	                    System.out.println("Exiting...");
	                    return;
	                default:
	                    System.out.println(" Invalid choice. Try again.");
	            }
	        }
	    }

	    private static void addPatient() {
	        try (FileWriter fw = new FileWriter(FILE_NAME, true);
	             BufferedWriter bw = new BufferedWriter(fw);
	             PrintWriter out = new PrintWriter(bw)) {

	            System.out.print("Enter Patient ID: ");
	            int id = scanner.nextInt();
	            scanner.nextLine(); 

	            System.out.print("Enter Name: ");
	            String name = scanner.nextLine();

	            System.out.print("Enter Age: ");
	            int age = scanner.nextInt();
	            scanner.nextLine();

	            System.out.print("Enter Disease: ");
	            String disease = scanner.nextLine();

	            Patient patient = new Patient(id, name, age, disease);
	            out.println(patient);
	            System.out.println(" Patient added successfully!");

	        } catch (IOException e) {
	            System.out.println("Error writing to file.");
	        }
	    }

	    private static void displayPatients() {
	        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
	            String line;
	            System.out.println("\nList of Patients:");
	            while ((line = br.readLine()) != null) {
	                System.out.println(line);
	            }
	        } catch (IOException e) {
	            System.out.println("No patients found.");
	        }
	    }

	    private static void searchPatientById() {
	        System.out.print(" Enter Patient ID to search: ");
	        int searchId = scanner.nextInt();
	        scanner.nextLine();

	        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] data = line.split(", ");
	                if (Integer.parseInt(data[0]) == searchId) {
	                    System.out.println(" Patient Found: " + line);
	                    return;
	                }
	            }
	            System.out.println("Patient with ID " + searchId + " not found.");
	        } catch (IOException e) {
	            System.out.println(" Error reading file.");
	        }
	    }
	    }

