/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoand.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoand.cart.CartObject;

/**
 *
 * @author KHOA
 */
@WebServlet(name = "RemoveItemToCartServlet", urlPatterns = {"/RemoveItemToCartServlet"})
public class RemoveItemToCartServlet extends HttpServlet {

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
        try  {
           //1 Customer goes to his/het cart place // tai vi giao dien van con nhung sesssion van con
           HttpSession session = request.getSession(false);
           if(session!=null){
               //2 Customer takes his/her cart 
               CartObject cart =(CartObject)session.getAttribute("CART");
               if(cart!=null){
               //3 Customer takes items 
                   Map<String, Integer> items= cart.getItems();
                   if(items!= null){
                   //4 Customer choese deleting items
                   String[] removedItems = request.getParameterValues("chkItem");
                   if(removedItems!=null){ // check may dua ko chon gi ma bam remove
                   //5 Customer remove each items cart
                   for(String item: removedItems){
                       cart.removeItemFromCart(item, 1);
                       }//end traverse each item
                   session.setAttribute("CART", cart);
                   }//end removeItems had choiced
                   }//end items have existed
               
               
               }//end if cart has existed
           }//session has existed
        }finally{
            //6 refresh - call view cart again
            String urlRewrString ="viewCart"
                    +"?btAction=View Your Cart";
            response.sendRedirect(urlRewrString);// boi vi cho an toan , het session ma truyen thang la an lol
            
            // do trung btaction
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
