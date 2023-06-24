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
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LIPSITA
 */
public class FetchCartDetailsDao {

    public static List<Product> fetchProductList(User usr) {
        List<Product> cartProducts = new ArrayList<>();
        try {
            String url = "jdbc:mysql://localhost:3306/somani?useSSL=false";
            String user = "root";
            String password = "Jayjagannath@1991!";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement ps = con.prepareStatement(
                    "select * from carttable");
           // ps.setString(7, usr.getuName());

            ResultSet rs = ps.executeQuery();
           while(rs.next())
           {
               Product product = new Product();
               
               product.setProductId(rs.getInt(1));
               product.setDescription(rs.getString(2));
               product.setPrice(rs.getInt(3));
               product.setQuantity(rs.getInt(4));
               product.setSize(rs.getString(5));
               product.setSource(rs.getString(6));
               
               cartProducts.add(product);
           }
            System.out.println();
        } catch (Exception e) {

        }
        return cartProducts;
    }

}
