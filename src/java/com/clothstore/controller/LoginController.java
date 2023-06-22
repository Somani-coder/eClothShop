/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clothstore.controller;

import com.clothstore.model.Response;
import com.clothstore.model.User;
import com.clothstore.repositories.LoginDao;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
public class LoginController extends HttpServlet {

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
            String command = jsonNode.get("command").asText();

            // 3. Convert received JSON to Article
            User user = mapper.readValue(json, User.class);

            // 4. Set response type to JSON
            response.setContentType("application/json");
            String un = user.getuName();
            String up = user.getuPass();
            if (command.equals("Loin")) {
                boolean statusLogin = LoginDao.validate(un, up);

                PrintWriter writer = response.getWriter();
                JSONObject obj = new JSONObject();

                response.setStatus(200);
                //System.out.println(obj.get("message"));

                if (statusLogin == true) {
                    Response res = new Response();
                    res.setMsg("Welcome ! You are logged in successfully..");
                    res.setStatus("success");
                    res.setStatusCode(200);
                    obj.put("message", res.getMsg());
                    writer.append(obj.toString());
                    writer.close();
                    //mapper.writeValue(response.getOutputStream(), res);

                } else {
                    Response res = new Response();
                    res.setMsg("Not authorized ! Please register first.");
                    res.setStatus("failed");
                    res.setStatusCode(500);
                    obj.put("message", res.getMsg());
                    writer.append(obj.toString());
                    writer.close();
                    //mapper.writeValue(response.getOutputStream(), res);
                }
            } else if (command.equals("Register")) {
                String registrationStatus = LoginDao.registerNewUser(un, up);
                PrintWriter writer = response.getWriter();
                JSONObject obj = new JSONObject();
               

                response.setStatus(200);
                if (registrationStatus.equals("Success")) {
                    Response res = new Response();
                    res.setMsg("Welcome ! You are registered successfully..");
                    res.setStatus("success");
                    res.setStatusCode(200);
                    obj.put("message", res.getMsg());
                    writer.append(obj.toString());
                    writer.close();
                }

            }

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
