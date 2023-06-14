/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoand.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import khoand.cart.CartObjectForStore;
import khoand.tblOrder.TblOrderDTO;
import khoand.tblOrder.TblOrderDAO;

import khoand.tblProduct.TblProductDAO;
import khoand.tblProduct.TblProductDTO;
import khoand.util.MyApplicationConstants;

/**
 *
 * @author KHOA
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {
private final String VIEW_SHOPING_JSP ="AddToCartStore";
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
       //String url=VIEW_SHOPING_JSP;
       String url = MyApplicationConstants.StoreFeature.ADD_ITEM_TO_STORE_CONTROLLER;
       String name = request.getParameter("Cusname");
       String address = request.getParameter("CusAdd");
        HttpSession session = request.getSession(true);
           session.removeAttribute("CHECK");
         
        try {
         if(name!=null&&name!=""){
         if(address!=null&&address!=""){
               
             
            
          
            CartObjectForStore cart =(CartObjectForStore) session.getAttribute("CARTSTORE");
            if(cart==null){
            cart= new CartObjectForStore();
            }
            // tao ra list tu product
              TblProductDAO dao2 = new TblProductDAO();// dao2 la cai khung
               
                dao2.viewProduct();
               
                List<TblProductDTO> result1 = dao2.getAcountList();
                //tao ra mot khung de mapping
                TblProductDAO dao = new TblProductDAO();
                // tao ra list tu map
                List<TblProductDTO> result = new ArrayList<>();
                 Map<String,Integer> items = cart.getItems();
                 // map lai voi nhau
                 for(String key: items.keySet()){
                 result.add(dao.viewCart(key, items.get(key), result1));
                 }// tao ra list của đơn hàng (result)
                 
                 //luu san pham duoi db
                 TblOrderDAO tblorderdao = new TblOrderDAO();
                 
                 TblOrderDTO tblorderdto = new TblOrderDTO();
                 
                 tblorderdto=tblorderdao.createOrder(result, name, address);
                request.setAttribute("ORDER_PRODUCT", tblorderdto);
             
             
        
             
            
             request.setAttribute("CARTSTOREVIEW", result); 
             
             
   
         }else{
         session.setAttribute("CHECK", "dont may emtry");
         
         }
         
         }else{
         session.setAttribute("CHECK", "dont may emtry");  
         }
         
        }catch(NamingException ex){
             log("Erroes occur in processs naming...", ex.getCause());
        }catch(SQLException ex){
             log("Erroes occur in processs sql...", ex.getCause());
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
