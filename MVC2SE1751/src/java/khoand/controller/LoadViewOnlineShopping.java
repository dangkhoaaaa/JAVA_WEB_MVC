/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoand.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoand.cart.CartObject;
import khoand.registration.RegistrationDAO;
import khoand.registration.RegistrationDTO;
import khoand.tblProduct.TblProductDAO;
import khoand.tblProduct.TblProductDTO;
import khoand.util.MyApplicationConstants;

/**
 *
 * @author KHOA
 */
@WebServlet(name = "LoadViewOnlineShopping", urlPatterns = {"/LoadViewOnlineShopping"})
public class LoadViewOnlineShopping extends HttpServlet {
 private final String RESULT_PAGE="onlineShopping.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws javax.naming.NamingException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       //String url =RESULT_PAGE;
       String url = MyApplicationConstants.StoreFeature.VIEW_CART_PAGE_STORE;
        try  {
              
              
            //4 call model/dao
               //4.1 new RegistrationDAO object
                TblProductDAO dao = new TblProductDAO();
                //4.2 goi ham view
                dao.viewProduct();
                //4.3 store result to scope(if any)
                List<TblProductDTO> result = dao.getAcountList();
                request.setAttribute("LIST_PRODUCT", result);
               
            
           
        }catch(NamingException ex){
             log("Erroes occur in processs...", ex.getCause());
        }catch(SQLException ex){
             log("Erroes occur in processs...", ex.getCause());
        }finally{
    
           //response.sendRedirect(url);
           RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
