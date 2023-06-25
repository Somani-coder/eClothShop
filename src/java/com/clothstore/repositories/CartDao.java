/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clothstore.repositories;

import com.clothstore.model.Product;
import com.clothstore.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LIPSITA
 */
public class CartDao {

    public static boolean Register(List<Product> products) {
        boolean cartStatus = false;

        try {
            String url = "jdbc:mysql://localhost:3306/somani?useSSL=false";
            String user = "root";
            String password = "Jayjagannath@1991!";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = con.prepareStatement(
                    "insert into carttable values(?,?,?,?,?,?,?)");
            for (Product prdts : products) {
                ps.setInt(1, prdts.getProductId());
                ps.setString(2, prdts.getDescription());
                ps.setInt(3, prdts.getPrice());
                ps.setInt(4, prdts.getQuantity());
                ps.setString(5, prdts.getSize());
                ps.setString(6, prdts.getSource());
                ps.setString(7,prdts.getBuytype());
                ps.addBatch();
            }

            ps.executeBatch();
            cartStatus = true;
            // status = rs.next();

        } catch (Exception e) {
            System.out.println(e);
        }
        return cartStatus;
    }

   

}
