/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoand.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoand.cart.CartObject;
import khoand.util.MyApplicationConstants;

/**
 *
 * @author KHOA
 */
@WebServlet(name = "AddItemToCartServlet", urlPatterns = {"/AddItemToCartServlet"})
public class AddItemToCartServlet extends HttpServlet {
    private final String SHOPPING_PAGE = "onlineShopping.html";
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
        //String url = SHOPPING_PAGE;
        String url=MyApplicationConstants.ShoppingFeature.SHOPPING_PAGE;
        try  {
           //1. Cusstomr goes to cart place - tao session cope
           HttpSession session = request.getSession(true);// chac chan phai co gio//chon true thi k can check session null
           //2. Customer takes a cart
            CartObject cart =(CartObject) session.getAttribute("CART"); // CART thich dat thi dat
            if(cart==null){
            cart= new CartObject();
            }// end cart has NOT existed
            //3.Customer drops item into cart
            String item= request.getParameter("ddlBook");
           
            cart.addItemToCart(item, 1);
            session.setAttribute("CART", cart);
             //4. Customer continously goes shopping

        }finally{
            //dung gi cung duoc
           response.sendRedirect(url);
          //  RequestDispatcher rd = request.getRequestDispatcher(url);
          //   rd.forward(request, response);
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
