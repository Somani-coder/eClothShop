/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clothstore.repositories;

import com.clothstore.model.OrderDetails;
import com.clothstore.model.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

/**
 *
 * @author LIPSITA
 */
public class OrderDao {

    public static boolean placeOrder(List<OrderDetails> orders) {
        boolean cartStatus = false;

        try {
            String url = "jdbc:mysql://localhost:3306/somani?useSSL=false";
            String user = "root";
            String password = "Jayjagannath@1991!";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = con.prepareStatement(
                    "insert into ordertable values(?,?,?,?,?,?,?)");
            for (OrderDetails prdts : orders) {
                ps.setInt(1, prdts.getOrderId());
                ps.setString(2, prdts.getProductName());
                ps.setInt(3, prdts.getPrice());
                ps.setString(4, prdts.getAddress());
                ps.setString(5, prdts.getPaymentMethod());
                ps.setInt(7, prdts.getQuantity());
                ps.setString(6, prdts.getSource());
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
