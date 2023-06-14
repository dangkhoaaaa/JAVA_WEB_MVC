/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoand.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khoand.registration.RegistrationDAO;
import khoand.registration.RegistrationDTO;
import khoand.util.MyApplicationConstants;

/**
 *
 * @author KHOA
 */
@WebServlet(name = "TriggerAppServle", urlPatterns = {"/TriggerAppServle"})
public class TriggerAppServle extends HttpServlet {
   private final String LOGIN_PAGE = "login.html";
    private final String SEARCH_PAGE = "search.jsp";
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       // String url= LOGIN_PAGE;
         String url=MyApplicationConstants.DispatchFeature.LOGIN_PAGE;
        try  {
             //1 get cookies from req
          
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
             //2. get last cookies
             Cookie lastCookie = cookies[cookies.length - 1];
             //3. get username and password from name and  value
             String username= lastCookie.getName();
             String password = lastCookie.getValue();
             //4. call Model to check authenticaation
             RegistrationDAO dao = new RegistrationDAO();
             RegistrationDTO result = dao.checkLogin(username, password);
             if(result!= null){
                   //url = SEARCH_PAGE;
                   url=MyApplicationConstants.LoginFeature.SEARCH_ACTION;
             }// end username and password are correctly
             
            }//end cookies had existed
        } catch (SQLException ex) {
           log("Erroes occur in processs...", ex.getCause());
       } catch (NamingException ex) {
           log("Erroes occur in processs...", ex.getCause());
       }finally{
            response.sendRedirect(url);
            
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
        processRequest(request, response);
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
        processRequest(request, response);
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
