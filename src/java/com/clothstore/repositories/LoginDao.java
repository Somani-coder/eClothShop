/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clothstore.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author LIPSITApublic static boolean validate(String name,String pass){
 */
public class LoginDao {

//    public static void main(String args[])
//    {
//        boolean status = validate("somani@gmail.com", "admin123");
//    }
    public static boolean validate(String name, String pass) {
        boolean status = false;
        try {
            String url = "jdbc:mysql://localhost:3306/somani?useSSL=false";
            String user = "root";
            String password = "Jayjagannath@1991!";
            String query = "Select * from students";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = con.prepareStatement(
                    "select * from user where name=? and userPass=?");
            ps.setString(1, name);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            status = rs.next();

        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static String registerNewUser(String email, String pwd) {
        String regStatus = "";
        try {
            String url = "jdbc:mysql://localhost:3306/somani?useSSL=false";
            String user = "root";
            String password = "Jayjagannath@1991!";
           
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = con.prepareStatement(
                    "insert into user values(?,?)") ;
            ps.setString(2, email);
            ps.setString(4, pwd);

            ResultSet rs = ps.executeQuery();
            //status = rs.next();

        } catch (Exception e) {
            System.out.println(e);
        }

        return regStatus;
    }

}
