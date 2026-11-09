import java.sql.*;
import java.util.Scanner;

/*
 * Simple SQL Table Setup:
 * 
 * CREATE DATABASE IF NOT EXISTS prakhar;
 * USE prakhar;
 * 
 * CREATE TABLE IF NOT EXISTS student (
 *     id INT PRIMARY KEY,
 *     name VARCHAR(50) NOT NULL,
 *     course VARCHAR(50) NOT NULL,
 *     email VARCHAR(50) NOT NULL
 * );
 */

public class StudentJDBC {

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/prakhar?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "bvicam";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    // Helper: Initialize table if not exists
    private static void initializeDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS student (" +
                     "id INT PRIMARY KEY, " +
                     "name VARCHAR(50) NOT NULL, " +
                     "course VARCHAR(50) NOT NULL, " +
                     "email VARCHAR(50) NOT NULL)";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Database notice: " + e.getMessage());
        }
    }

    // a) Add a record using PreparedStatement
    public static void addRecord(Scanner sc) {
        System.out.println("\n--- Add Student Record ---");
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Course: ");
        String course = sc.nextLine().trim();
        System.out.print("Enter Email: ");
        String email = sc.nextLine().trim();

        String insertQuery = "INSERT INTO student (id, name, course, email) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, course);
            pstmt.setString(4, email);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println(">> Student record added successfully!\n");
            }
        } catch (SQLException e) {
            System.err.println("Error adding record: " + e.getMessage() + "\n");
        }
    }

    // b) Search for a record using PreparedStatement
    public static void searchRecord(Scanner sc) {
        System.out.println("\n--- Search Student Record ---");
        System.out.print("Enter Student ID to search: ");
        int id = sc.nextInt();

        String searchQuery = "SELECT id, name, course, email FROM student WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\n========== STUDENT DETAILS FOUND ==========");
                    System.out.println("ID     : " + rs.getInt("id"));
                    System.out.println("Name   : " + rs.getString("name"));
                    System.out.println("Course : " + rs.getString("course"));
                    System.out.println("Email  : " + rs.getString("email"));
                    System.out.println("===========================================\n");
                } else {
                    System.out.println(">> No student record found with ID: " + id + "\n");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error searching record: " + e.getMessage() + "\n");
        }
    }

    // c) Modify an existing record using PreparedStatement
    public static void modifyRecord(Scanner sc) {
        System.out.println("\n--- Modify Student Record ---");
        System.out.print("Enter Student ID to modify: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline

        // Check if student exists
        String checkQuery = "SELECT id FROM student WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {

            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();
            if (!rs.next()) {
                System.out.println(">> Student with ID " + id + " does not exist.\n");
                return;
            }
        } catch (SQLException e) {
            System.err.println("Error checking record: " + e.getMessage() + "\n");
            return;
        }

        System.out.print("Enter New Name: ");
        String newName = sc.nextLine().trim();
        System.out.print("Enter New Course: ");
        String newCourse = sc.nextLine().trim();
        System.out.print("Enter New Email: ");
        String newEmail = sc.nextLine().trim();

        String updateQuery = "UPDATE student SET name = ?, course = ?, email = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {

            pstmt.setString(1, newName);
            pstmt.setString(2, newCourse);
            pstmt.setString(3, newEmail);
            pstmt.setInt(4, id);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println(">> Student record updated successfully!\n");
            }
        } catch (SQLException e) {
            System.err.println("Error modifying record: " + e.getMessage() + "\n");
        }
    }

    // Display all records
    public static void displayAllRecords() {
        String query = "SELECT id, name, course, email FROM student";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n===================== ALL STUDENTS =====================");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("ID: %-5d | Name: %-15s | Course: %-10s | Email: %s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getString("email"));
            }
            if (!found) {
                System.out.println("No records found in table.");
            }
            System.out.println("========================================================\n");
        } catch (SQLException e) {
            System.err.println("Error fetching records: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        initializeDatabase();

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("==================================================");
            System.out.println("         STUDENT DATABASE MANAGEMENT (JDBC)");
            System.out.println("==================================================");
            System.out.println("1. Add a Record (PreparedStatement)");
            System.out.println("2. Search for a Record (PreparedStatement)");
            System.out.println("3. Modify an Existing Record (PreparedStatement)");
            System.out.println("4. Display All Records");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            int choice;
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number.\n");
                sc.next();
                continue;
            }

            switch (choice) {
                case 1:
                    addRecord(sc);
                    break;
                case 2:
                    searchRecord(sc);
                    break;
                case 3:
                    modifyRecord(sc);
                    break;
                case 4:
                    displayAllRecords();
                    break;
                case 5:
                    System.out.println("Exiting Application. Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please choose between 1 and 5.\n");
            }
        }
    }
}
