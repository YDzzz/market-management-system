package init;

import Mode.LoadingGUI;
import Mode.MyFrame;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;


public class Init {
    static {
        InputStream inputStream =  Init.class.getClassLoader().getResourceAsStream("schema.sql");
        assert inputStream != null;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String url = "jdbc:h2:E:\\OneDrive\\JET BRAINS\\Java\\maven_test\\inventory";
        String userName = "sa";
        String password = "123456";

        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(url,userName,password);
                while(bufferedReader.ready()){
                    StringBuilder str = new StringBuilder();
                    str.append(bufferedReader.readLine());
                    if (str.toString().equals("\n"))
                        continue;
                    while(!str.toString().contains(";")) {
                        str.append(bufferedReader.readLine());
                    }
                    pstmt = conn.prepareStatement(str.toString());
                    pstmt.executeUpdate();
                }
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                    System.out.println("Table Create Success");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }

        MyFrame frame = new MyFrame("LoadingGUI");
        frame.setContentPane(new LoadingGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
