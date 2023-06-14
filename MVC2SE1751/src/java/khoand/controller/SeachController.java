/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoand.controller;

import java.io.IOException;
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
import khoand.registration.RegistrationDAO;
import khoand.registration.RegistrationDTO;
import khoand.util.MyApplicationConstants;

/**
 *
 * @author KHOA
 */
@WebServlet(name = "SeachController", urlPatterns = {"/SeachController"})
public class SeachController extends HttpServlet {
       private final String SEARCH_PAGE ="search.html";
       private final String RESULT_PAGE="search.jsp";
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
        
        String seacrhvalue = request.getParameter("txtseach");
        //String url =RESULT_PAGE;/// error
        String url=MyApplicationConstants.LoginFeature.SEARCH_ACTION;
        try  {
            if(!seacrhvalue.trim().isEmpty()){
             //4 call model/dao
               //4.1 new RegistrationDAO object
                RegistrationDAO dao = new RegistrationDAO();
                //4.2
                dao.searchLastname(seacrhvalue);
                //4.3 store result to scope(if any)
                List<RegistrationDTO> result = dao.getAcountList();
               
                request.setAttribute("SEARCH_RESULT", result);
                url = MyApplicationConstants.LoginFeature.SEARCH_ACTION;
                //
            }//end search Value has valid value
        }catch(NamingException ex){
             log("Erroes occur in processs...", ex.getCause());
        }catch(SQLException ex){
             log("Erroes occur in processs...", ex.getCause());
        }finally{
            
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request,response);
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
