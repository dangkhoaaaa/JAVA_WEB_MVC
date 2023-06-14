/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoand.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import khoand.util.DBhelp;

/**
 *
 * @author KHOA
 */
public class RegistrationDAO implements Serializable{
    
    
   // public boolean checkLogin(String username , String password) 
      //  throws SQLException, NamingException{
     public RegistrationDTO checkLogin(String username , String password) 
        throws SQLException, NamingException{
      
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO result = null;
        try {
             // connect Db
             con = DBhelp.mameconect();
         if(con!= null){    
        // 2 write SQL commamnd
        String sql ="Select username, lastname, isAdmin "
                + "From Registration "
                +"Where username = ? And password = ?";
        //3 create Statement Obejct
        stm = con.prepareStatement(sql);
        stm.setString(1, username);
        stm.setString(2, password);
        
        //4 Execute Statement to get result
        rs = stm.executeQuery();
        //5 Process result 
         if(rs.next()){
             String fullName= rs.getString("lastname");
             boolean role = rs.getBoolean("isAdmin");
             
             result = new RegistrationDTO(username, null, fullName, role);
         }//user has existed
         }
        } finally{
            if( rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
            
        }
      return result;
    }
       
    private List<RegistrationDTO> acountList;

    public List<RegistrationDTO> getAcountList() {
        return acountList;
    }
    
    public void searchLastname(String keyword)
        throws SQLException, NamingException{
         Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
      
        try {
             // connect Db
             con = DBhelp.mameconect();
         if(con!= null){    
        // 2 write SQL commamnd
        String sql ="Select username, password, lastname, isAdmin "
                + "From Registration "
                +"Where lastname Like ?";
        //3 create Statement Obejct
           stm=con.prepareCall(sql);
           stm.setString(1, "%"+ keyword+"%");
        //4 Execute Statement to get result
        rs = stm.executeQuery();
        //5 Process result 
             while (rs.next()) {                 
                 String username = rs.getString("username");
                 String password = rs.getString("password");
                 String lastname = rs.getString("lastname");
                 boolean role = rs.getBoolean("isAdmin");
                 
                 RegistrationDTO dto = new RegistrationDTO(
                         username, password, lastname, role);
                 if(this.acountList==null){
                     this.acountList= new ArrayList<>();
                 }// end account List had Not existed
                 this.acountList.add(dto);
             }//end rs has not rached EOF
         }//end connect has existed
        } finally{
            if( rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
            
        }
      
    }
    
    public boolean deleteAcount(String pk)
        throws SQLException, NamingException{
         Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
      
        try {
             // connect Db
             con = DBhelp.mameconect();
         if(con!= null){    
        // 2 write SQL commamnd
           String sql= "Delete "
                   + "From Registration "
                   +"Where username = ?";
        //3 create Statement Obejct
           stm=con.prepareStatement(sql);
           stm.setString(1, pk);
        //4 Execute Statement to get result
       int effectRows = stm.executeUpdate();
        //5 Process result 
            if(effectRows >0){
                result = true;
            }
             //end rs has not rached EOF
         }//end connect has existed
        } finally{
         
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
            
        }
        return result;
    }
    
    public boolean updateAcount(String username,String password, boolean admin)
        throws SQLException, NamingException{
         Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
      
        try {
             // connect Db
             con = DBhelp.mameconect();
         if(con!= null){    
           // 2 write SQL commamnd
           String sql= "Update Registration "
                   + "Set password = ?,isAdmin = ? "
                   +"Where username = ?";
        //3 create Statement Obejct
           stm=con.prepareStatement(sql);
           stm.setString(1, password);
           stm.setBoolean(2, admin);
           stm.setString(3, username);
          
        //4 Execute Statement to get result
       int effectRows = stm.executeUpdate();
        //5 Process result 
            if(effectRows >0){
                result = true;
            }
             //end rs has not rached EOF
         }//end connect has existed
        } finally{
         
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
            
        }
        return result;
    }

    public  boolean createAccount(RegistrationDTO dto) throws SQLException, NamingException{
    Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
      
        try {
             // connect Db
             con = DBhelp.mameconect();
         if(con!= null){    
          // 2 write SQL commamnd
           String sql= "Insert Into Registration "
                   + "(username, password, lastname, isAdmin"
                   +") Values("
                   +"?, ?, ?, ?"
                   +")";
        //3 create Statement Obejct
           stm=con.prepareStatement(sql);
           stm.setString(1, dto.getUsername());
           stm.setString(2, dto.getPassword());
           stm.setString(3, dto.getLastname());
           stm.setBoolean(4, dto.isRole());
           
          
        //4 Execute Statement to get result
       int effectRows = stm.executeUpdate();
        //5 Process result 
            if(effectRows >0){
                result = true;
            }
             //end rs has not rached EOF
         }//end connect has existed
        } finally{
         
            if(stm != null){
                stm.close();
            }
            if(con != null){
                con.close();
            }
            
        }
        return result;
    
    }
    }

