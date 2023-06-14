/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoand.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import khoand.registration.RegistrationCreateError;
import khoand.registration.RegistrationDAO;

/**
 *
 * @author KHOA
 */
@WebServlet(name = "UpdateController", urlPatterns = {"/UpdateController"})
public class UpdateController extends HttpServlet {
private final String ERRORS_PAGE= "errors.html";
 private final String RESULT_PAGE="search.jsp";
 RegistrationCreateError errors = new RegistrationCreateError();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOExcep  tion if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          response.setContentType("text/html;charset=UTF-8");
        //String urlRewriting =ERRORS_PAGE;
        String lastSearchValue= request.getParameter("lastSearchValue");
        String urlRewriting ="SeachController"
                       +"?"
                       +"txtseach="+lastSearchValue;
        String username= request.getParameter("txtUsername");
        String nameuser= request.getParameter("nameuser");
        String password= request.getParameter("txtPassword");
        String password1= request.getParameter("txtPassword1");
        String passuser= request.getParameter("passuser");
        String role=request.getParameter("roleuser");
         String errorNullPassword="You must are admin, you only change your passsword";
        boolean admin= Boolean.parseBoolean(request.getParameter("chkAdmin"));
        
        
        try {
         
            
            if(password!=null&&password!=""){
                    RegistrationDAO dao = new RegistrationDAO();
                if(role.equals("true")){
                 if(nameuser.equals(username)){
                     
                  boolean result = dao.updateAcount(username, password, admin);
            if(result){
               //call Search function again by using url rewriting
               //request.setAttribute("SEARCH_RESULT", result);
               urlRewriting = "logOutController"
                       +"?"
                       +"txtseach="+lastSearchValue;
            }
                 
                 }else{
                     
                  boolean result = dao.updateAcount(username, password, admin);
            if(result){
               //call Search function again by using url rewriting
               //request.setAttribute("SEARCH_RESULT", result);
               urlRewriting = "SeachController"
                       +"?"
                       +"txtseach="+lastSearchValue;
            }
                 
                 
                 }
           
                }else{
                 if(nameuser.equals(username)){
                     if(admin){
                            urlRewriting = "SeachController"
                       +"?"
                       +"txtseach="+lastSearchValue+"&errorAdmin="+errorNullPassword;
                     }else{
           boolean result = dao.updateAcount(username, password, false);
                        urlRewriting = "logOutController"
                       +"?"
                       +"txtseach="+lastSearchValue;
      
               //call Search function again by using url rewriting
               //request.setAttribute("SEARCH_RESULT", result);
           
                     }
                  
         
                 }else{
                
              
              urlRewriting = "SeachController"
                       +"?"
                       +"txtseach="+lastSearchValue
                     +"&errorAdmin="+errorNullPassword;
                 }
          
                }
          
            
            }else{
             //   errors.setPasswordNull("Password don may emtry");
            // request.setAttribute("CREATE_ERRORS", errors);
             String errorNullPassword1="Password don may emtry";
              urlRewriting = "SeachController"
                       +"?"
                       +"txtseach="+lastSearchValue
                      +"&error="+errorNullPassword1;
            }//end delete action is successfu;;;u
            
            
            
        } catch (SQLException ex) {
                log("Erroes occur in processs...", ex.getCause());
             } catch (NamingException ex){
                   log("Erroes occur in processs...", ex.getCause());
             }finally{
                   response.sendRedirect(urlRewriting);
                     
                   
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
