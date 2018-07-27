import com.opencsv.CSVReader;

import java.io.FileReader;
import java.sql.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class SQLiteJDBC {
    public SQLiteJDBC() {
    }

    public static void main (String[] args) throws Exception {
        String driver = "org.sqlite.JDBC";

        String file = "C:/Program Files/Java/jdk1.8.0_181/bin/sample.csv";

        Class.forName(driver);
        String dbName = "cp2.db";
        String dbUrl = "jdbc:sqlite:" + dbName;
        Connection conn = DriverManager.getConnection(dbUrl);
        //create table
        Statement st = conn.createStatement();
        // st.executeUpdate("CREATE table employee1 (A varchar(115), B varchar(115),C varchar(100), D varchar(110), K varchar(355), F varchar(355), G varchar(50), H varchar(60), I varchar(61), J varchar(150))");
        PreparedStatement pstmt = null;
        String s[] = new String[11];
        try {
            Scanner inputStream = new Scanner(file);

            while (inputStream.hasNext()) {
                String data = inputStream.next();
                s = data.split(",");

                String sql = "INSERT INTO EMPLOYEE1(A,B,C,D,E,F,G,H,I,J) VALUES(?,?,?,?,?,?,?,?,?,?)";


                pstmt = conn.prepareStatement(sql);
                //   s[0]="darsjamn";
                System.out.println("Size" + s.length);
                if (s.length == 10 || s.length == 11) {
                    pstmt.setString(1, s[0]);
                    pstmt.setString(2, s[1]);
                    pstmt.setString(3, s[2]);
                    pstmt.setString(4, s[3]);
                    pstmt.setString(5, s[4]);
                    pstmt.setString(6, s[5]);
                    pstmt.setString(7, s[6]);
                    pstmt.setString(8, s[7]);
                    pstmt.setString(9, s[8]);
                    pstmt.setString(10, s[9]);
                    pstmt.addBatch();
                    // pstmt.executeUpdate();
                }
            }
            pstmt.executeBatch();
        }
        finally {
    }










        }
    }
