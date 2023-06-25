/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clothstore.repositories;

import com.clothstore.model.OrderDetails;
import com.clothstore.model.Product;
import com.clothstore.model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author LIPSITA
 */
public class OrderDao {

    public static boolean placeOrder(List<OrderDetails> orders) {
        boolean orderStatus = false;
        boolean removeFromCartStatus = false;

        try {
            String url = "jdbc:mysql://localhost:3306/somani?useSSL=false";
            String user = "root";
            String password = "Jayjagannath@1991!";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);

            PreparedStatement ps = con.prepareStatement(
                    "insert into ordertable values(?,?,?,?,?,?,?)");
            Random rd = new Random();
            int max = 99999;
            int min = 10000;
            int orderId = rd.nextInt(max - min) + min;
            for (OrderDetails prdts : orders) {
                prdts.setOrderId(orderId);
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
            orderStatus = true;
            Statement stmt = con.createStatement();
            //Query to delete all records in a table
            String query = "Truncate table carttable";
            //Executing the query
            stmt.execute(query);
            removeFromCartStatus=true;

        } catch (Exception e) {
            System.out.println(e);
        }
        return orderStatus;
    }

    public static List<OrderDetails> fetchOrderList(User usr) {
         List<OrderDetails> orderProducts = new ArrayList<>();
        try {
            String url = "jdbc:mysql://localhost:3306/somani?useSSL=false";
            String user = "root";
            String password = "Jayjagannath@1991!";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement(
                    "select * from ordertable");
           // ps.setString(7, usr.getuName());

            ResultSet rs = ps.executeQuery();
           while(rs.next())
           {
               OrderDetails product = new OrderDetails();
               
               product.setOrderId(rs.getInt(1));
               product.setProductName(rs.getString(2));
               product.setPrice(rs.getInt(3));
               product.setAddress(rs.getString(4));
               product.setPaymentMethod(rs.getString(5));
               product.setSource(rs.getString(6));
               product.setQuantity(rs.getInt(7));
               
               orderProducts.add(product);
           }
            System.out.println();
        } catch (Exception e) {

        }
        return orderProducts;
    }

    }


