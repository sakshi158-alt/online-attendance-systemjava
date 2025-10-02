import java.sql.*;
import java.util.Scanner;

public class AttendanceSystem {
    static final String URL = "jdbc:mysql://localhost:3306/attendance_db";
    static final String USER = "root"; // your MySQL username
    static final String PASS = "root"; // your MySQL password

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("✅ Connected to Database!");

            while (true) {
                System.out.println("\n=== Attendance System ===");
                System.out.println("1. Add Student");
                System.out.println("2. Mark Attendance");
                System.out.println("3. View Attendance");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter student name: ");
                        String name = sc.nextLine();
                        addStudent(conn, name);
                        break;
                    case 2:
                        System.out.print("Enter student ID: ");
                        int sid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter status (Present/Absent): ");
                        String status = sc.nextLine();
                        markAttendance(conn, sid, status);
                        break;
                    case 3:
                        viewAttendance(conn);
                        break;
                    case 4:
                        System.out.println("👋 Exiting...");
                        return;
                    default:
                        System.out.println("❌ Invalid choice!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addStudent(Connection conn, String name) throws SQLException {
        String sql = "INSERT INTO students (name) VALUES (?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.executeUpdate();
        System.out.println("✅ Student added successfully!");
    }

    public static void markAttendance(Connection conn, int studentId, String status) throws SQLException {
        String sql = "INSERT INTO attendance (student_id, date, status) VALUES (?, CURDATE(), ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, studentId);
        stmt.setString(2, status);
        stmt.executeUpdate();
        System.out.println("✅ Attendance marked!");
    }

    public static void viewAttendance(Connection conn) throws SQLException {
        String sql = "SELECT s.id, s.name, a.date, a.status FROM students s " +
                "JOIN attendance a ON s.id = a.student_id ORDER BY a.date DESC";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\n--- Attendance Records ---");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") +
                    " | Name: " + rs.getString("name") +
                    " | Date: " + rs.getDate("date") +
                    " | Status: " + rs.getString("status"));
        }
    }
}
