/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoand.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khoand.util.MyApplicationConstants;

/**
 *
 * @author KHOA
 */
@WebServlet(name = "DispathServerlet", urlPatterns = {"/DispathServerlet"})
public class DispathServerlet extends HttpServlet {
   // private final String LOGIN_PAGE ="login.html";
     private final String LOGIN_PAGE ="loginPage";
    private final String LOGIN_CONTROLLER ="LoginServlet";
    private final String SEARCH_CONTROL = "SeachPage";
     private final String DETELE_CONTROL = "DetelePage";
     private final String UPDATE_CONTROL = "UpdatePage";
    private final String TRIGGER_APP_CONTROLLER = "TriggerAppServle";
    private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartServlet";
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String LOGOUT_PAGE ="LogoutPage";
    private final String STORE_ADD_TO_CART_PAGE ="AddToCartStore";
     private final String REMOVE_ITEM_FROM_CART_CONTROLLER ="RemoveItemToCartServlet";
      private final String REMOVE_ITEM_TO_CART_PAGE_CONTROLLER ="RemoveToCartStore";
      private final String VIEW_SHOPING_JSP ="onlineShopping.jsp";
        private final String CREATE_NEW_ACCOUNT ="CreateNewAcountServlet";
         private final String CHECK_OUT ="CheckOutServlet";
    
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
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGIN_PAGE);
        // which button did user click? 
        String button = request.getParameter("btAction");
        try  {
            if(button==null){
                //transfer to Login page
                url=TRIGGER_APP_CONTROLLER;
            }else if(button.equals("Login")){
                url=siteMaps.getProperty(MyApplicationConstants.DispatchFeature.LOGIN_CONTROLLER);
            } else if(button.equals("Search")){
                 url=siteMaps.getProperty(MyApplicationConstants.LoginFeature.SEARCH_ACTION);
            }else if(button.equals("Delete")){
                url=DETELE_CONTROL;
            }else if(button.equals("Update")){
               url=UPDATE_CONTROL;
            }else if(button.equals("Add Book to Your Cart")){
                url=ADD_ITEM_TO_CART_CONTROLLER;
            }else if(button.equals("View Your Cart")){
                url=VIEW_CART_PAGE;
            }else if(button.equals("Logout")){
                url=LOGOUT_PAGE;
            }else if(button.equals("Add to cart")){
               url=STORE_ADD_TO_CART_PAGE;
            }else if(button.equals("Remove Selected Items")){
               url=REMOVE_ITEM_FROM_CART_CONTROLLER;
            }else if(button.equals("Delete to cart")){
               url=REMOVE_ITEM_TO_CART_PAGE_CONTROLLER;
            }else if(button.equals("View shopping jsp")){
               url=VIEW_SHOPING_JSP;
            }else if(button.equals("Check Out")){
               url=CHECK_OUT;
            }else if(button.equals("Create new acount")){
               url=CREATE_NEW_ACCOUNT;
            }
             
        }finally{
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
