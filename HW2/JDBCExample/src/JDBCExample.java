import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {
    static final String DB_URL = "jdbc:mysql://localhost/STUDENTS";
    static final String USER = "root";
    static final String PASS = "68706870";

    public static void main(String[] args) {
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            /** Create Database
             * After that change the database in DB_URL*/
//            String sql = "CREATE DATABASE STUDENTS";
//            stmt.executeUpdate(sql);
//            System.out.println("Database created successfully...");

            /**
             * Create Table
             * */
//            String stuTable = "CREATE TABLE students " +
//                    "(id INTEGER not NULL, " +
//                    " name VARCHAR(255), " +
//                    " grade VARCHAR(255), " +
//                    " PRIMARY KEY ( id ))";
//
//            stmt.executeUpdate(stuTable);
//            System.out.println("Created table successfully");


            /** Insert Queries
             * */
//            String stuInsert = "INSERT INTO students VALUES (1, 'Alex', 1)";
//            stmt.executeUpdate(stuInsert);
//            String stuInsert1 = "INSERT INTO students VALUES (2, 'Bob', 1)";
//            stmt.executeUpdate(stuInsert1);
//            String stuInsert2 = "INSERT INTO students VALUES (3, 'Cyndy', 2)";
//            stmt.executeUpdate(stuInsert2);
//            System.out.println("rows created");

            /** Update Query
             * */
            String stuUpdate = "UPDATE students " +
                    "SET grade = 4 where name = 'Alex'";
            stmt.executeUpdate(stuUpdate);
            System.out.println("Updated successfully");

            /** Delete Query
             * */
//            String delTable = "DROP TABLE students";
//            stmt.executeUpdate(delTable);
//            System.out.println("Table deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
