import com.opencsv.CSVReader;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String driver = "org.sqlite.JDBC";

        String file = "C:/Program Files/Java/jdk1.8.0_181/bin/ms3.csv";

        Class.forName(driver);
        String dbName = "cp2.db";
        String dbUrl = "jdbc:sqlite:" + dbName;
        Connection conn = DriverManager.getConnection(dbUrl);
        //create table
        Statement st = conn.createStatement();
        // st.executeUpdate("CREATE table employee1 (A varchar(115), B varchar(115),C varchar(100), D varchar(110), K varchar(355), F varchar(355), G varchar(50), H varchar(60), I varchar(61), J varchar(150))");
        PreparedStatement pstmt = null;

        String fileName = "C:/Program Files/Java/jdk1.8.0_181/bin/ms3.csv";
        String sql = "INSERT INTO EMPLOYEE1(A,B,C,D,E,F,G,H,I,J) VALUES(?,?,?,?,?,?,?,?,?,?)";
        pstmt = conn.prepareStatement(sql);
        try {

            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(fileName);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] s;
int counter=0;
int valid=0;
int invalid=0;
            // we are going to read data line by line
            while ((s = csvReader.readNext()) != null) {


                if(countColumn(s)){

valid++;

                 /*   System.out.print(s[0]+"\t");

                    System.out.print(s[1]+"\t");
                    System.out.print(s[2]+"\t");
                    System.out.print(s[3]+"\t");
                    System.out.print(s[4]+"\t");
                    System.out.print(s[5]+"\t");
                    System.out.print(s[6]+"\t");
                    System.out.print(s[7]+"\t");
                    System.out.print(s[8]+"\t");

                    System.out.println(s[9]+"\t");*/
                    //System.out.println();

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
                    //pstmt.executeUpdate();
                    counter++;
                    pstmt.addBatch();

                    if(counter%100==0)
                    {
                        pstmt.executeBatch();

                        // System.out.print("count:"+counter);
                         System.out.println("count:"+counter);
                    }
                }
                    else
                {
                    invalid++;
                    System.out.println("counter:"+counter+"\tNULL found");
                }


            }



            } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }





        String query = "SELECT  * from EMPLOYEE1";
        int count=0;
        ResultSet rs = null;
        try {
            rs = st.executeQuery(query);
            while(rs.next()) {
                //  int id = rs.getInt(1);
                String name = rs.getString(1);
                String lname=rs.getString(2);
                System.out.println( "counter:"+(count++) +"\tname: "+ name +"\t lname:"+lname);
                //  System.out.print("lname:"+lname);
            }
            //delete
            //   st.executeUpdate("DELETE from village");
        } finally {
            rs.close();
            pstmt.close();
            st.close();

        }





    }


    public static boolean countColumn(String []record)
    {
        for(String data:record)
        {
            data=data.trim();
            if(data.equals(""))
            {
                return false;
            }
        }
        return true;
    }
    }



