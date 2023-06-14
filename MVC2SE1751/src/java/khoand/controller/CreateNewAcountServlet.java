/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoand.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import khoand.registration.RegistrationDTO;
import khoand.util.MyApplicationConstants;

/**
 *
 * @author KHOA
 */
@WebServlet(name = "CreateNewAcountServlet", urlPatterns = {"/CreateNewAcountServlet"})
public class CreateNewAcountServlet extends HttpServlet {
     private static final String EROR_PAGE = "createNewAccount.jsp";
     private static final String LOGIN_PAGE = "login.html";
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
        //String url = EROR_PAGE;
        String url = MyApplicationConstants.CreateAccountFeature.INSERT_ERROR_PAGE;
          String username = request.getParameter("txtUsername");
           String password = request.getParameter("txtpassword");
           String comfirm = request.getParameter("txtComfirm");
           String fullname = request.getParameter("txtFullname");
           boolean foundeError = false;
            RegistrationCreateError errors = new RegistrationCreateError();
        try{
         //1. check user, validation
            if(username.trim().length() <6||
                    username.trim().length()>20){
             foundeError =true;
             errors.setUsernameLengthError("Username is required input form 6 to 20 charaacters");
            }
             if(password.trim().length() <6||
                    password.trim().length()>30){
             foundeError =true;
             errors.setPasswordLengthError("password is required input form 6 to 30 charaacters");
            } 
             
             if(!comfirm.trim().equals(password.trim())){
            foundeError =true;
             errors.setConfirmNotMatchedError("Confirm must match passord");
            }
             if(fullname.trim().length() <2||
                    fullname.trim().length()>50){
             foundeError =true;
             errors.setFullnameLengthError("lastname is required input form 2 to 50 charaacters");
            }
             //2 process
             //2.1 if errors occur, display to user & log
             //2.2 otherwise, call model
             if(foundeError){
                 request.setAttribute("CREATE_ERRORS", errors);
                 
             }else{// no errors
                 RegistrationDAO dao = new RegistrationDAO();
                 RegistrationDTO dto = new RegistrationDTO(username, password, fullname, foundeError);
                 boolean result = dao.createAccount(dto);
                 if(result){
                    url = MyApplicationConstants.DispatchFeature.LOGIN_PAGE;
                 }// create action is succesfully
             }}catch(SQLException ex){
           // ex.printStackTrace();
           String msg = ex.getMessage();
        log("CreateNewAccountServlet _ SQL"+ msg);
            if(msg.contains("duplicate")){
                  errors.setUsernameisExit(username+"is existed");
                  request.setAttribute("CREATE_ERRORS", errors);
            }
        }catch(NamingException ex){
             log("CreateNewAccountServlet _ Naming"+ ex.getMessage());
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
