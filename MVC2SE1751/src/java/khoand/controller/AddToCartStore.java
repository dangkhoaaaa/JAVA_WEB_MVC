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
import khoand.cart.CartObject;
import khoand.cart.CartObjectForStore;
import khoand.registration.RegistrationDTO;
import khoand.tblProduct.TblProductDAO;
import khoand.tblProduct.TblProductDTO;
import khoand.util.MyApplicationConstants;

/**
 *
 * @author KHOA
 */
@WebServlet(name = "AddToCartStore", urlPatterns = {"/AddToCartStore"})
public class AddToCartStore extends HttpServlet {
    private final String SHOPPING_PAGE = "LoadViewOnlineShopping";
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
        String url = MyApplicationConstants.StoreFeature.LOAD_VIEW_STORE;
        try  {
           //1. Cusstomr goes to cart place - tao session cope
           HttpSession session = request.getSession(true);// chac chan phai co gio//chon true thi k can check session null
           //2. Customer takes a cart
            CartObjectForStore cart =(CartObjectForStore) session.getAttribute("CARTSTORE"); // CART thich dat thi dat
            if(cart==null){
            cart= new CartObjectForStore();
            }// end cart has NOT existed
            //3.Customer drops item into cart
            String item= request.getParameter("SkuValue");
           
            cart.addItemToCart(item, 1);
            
            session.setAttribute("CARTSTORE", cart);
             //4. Customer continously goes shopping
              TblProductDAO dao2 = new TblProductDAO();// dao2 la cai khung
                //4.2
                dao2.viewProduct();
                //4.3 store result to scope(if any)
                List<TblProductDTO> result1 = dao2.getAcountList();
                TblProductDAO dao = new TblProductDAO();//dao 1 la san pham tu khung va map
                
                List<TblProductDTO> result = new ArrayList<>();
                 Map<String,Integer> items = cart.getItems();
                 
                 for(String key: items.keySet()){
                 result.add(dao.viewCart(key, items.get(key), result1));
                 }
             
             
        
             
            
             request.setAttribute("CARTSTOREVIEW", result);
        }catch(NamingException ex){
             log("Erroes occur in processs...", ex.getCause());
        }catch(SQLException ex){
             log("Erroes occur in processs...", ex.getCause());
        }finally{
            //dung gi cung duoc
           //response.sendRedirect(url);
           RequestDispatcher rd = request.getRequestDispatcher(url);
             rd.forward(request, response);
           //6 refresh - call view cart again
         
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
