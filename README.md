# online-attendance-systemjava
simple attendance system using java, SQL , JDBC
# Online Attendance System (Java + MySQL)

## 📌 Project Overview
This is a beginner-level project made using:
- Java
- JDBC
- MySQL (Database)

## ✨ Features
- Add Student
- Mark Attendance (Present/Absent)
- View Attendance Records

## 🛠 Setup Instructions
1. Create the database in MySQL:
   ```sql
   CREATE DATABASE attendance_db;
   USE attendance_db;

   CREATE TABLE students (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100) NOT NULL
   );

   CREATE TABLE attendance (
       id INT AUTO_INCREMENT PRIMARY KEY,
       student_id INT,
       date DATE,
       status VARCHAR(10),
       FOREIGN KEY (student_id) REFERENCES students(id)
   );
