/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clothstore.controller;

import com.clothstore.model.OrderDetails;
import com.clothstore.model.Product;
import com.clothstore.model.Response;
import com.clothstore.repositories.CartDao;
import com.clothstore.repositories.OrderDao;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author LIPSITA
 */
public class OrderController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          
        }
         BufferedReader br
                = new BufferedReader(new InputStreamReader(request.getInputStream()));

        String json = "";
        if (br != null) {
            json = br.readLine();
            System.out.println(json);
        }

        // 2. initiate jackson mapper
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.readTree(json);
        String command = jsonNode.get("action").asText();
        ArrayNode products = (ArrayNode)jsonNode.get("products");
        
       // String jsonStr = products.asText();
        Gson gson = new Gson();
        OrderDetails order[] = gson.fromJson(products.toString(), OrderDetails[].class);

        boolean status = OrderDao.placeOrder(Arrays.asList(order));
        // 3. Convert received JSON to Article
        //Product product = mapper.readValue(json, Product.class);
                   // (products, Product.class);

        // 4. Set response type to JSON
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
                JSONObject obj = new JSONObject();
        if(status == true)
        {
            Response res = new Response();
                    res.setMsg("Added to order successfully..");
                    res.setStatus("success");
                    res.setStatusCode(200);
                    obj.put("message", res.getMsg());
                    writer.append(obj.toString());
                    writer.close();
                    mapper.writeValue(response.getOutputStream(), res);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
   
}
