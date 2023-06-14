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
 * @author LIPSITA
 */
public class RegisterDao {
    public static boolean Register(String name, String email, String pass){
        boolean registrationStatus=false;
        
    try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/somani",
                    "root", "Jayjagannath@1991!");

            PreparedStatement ps = con.prepareStatement(
                    "insert into user where id=? name=? and userPass=?");
            ps.setString(1, name);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            status = rs.next();

        } catch (Exception e) {
            System.out.println(e);
        }
}
}
