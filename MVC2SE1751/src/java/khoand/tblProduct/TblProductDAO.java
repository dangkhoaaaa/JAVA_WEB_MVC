/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoand.tblProduct;

import khoand.registration.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.naming.NamingException;
import khoand.util.DBhelp;

/**
 *
 * @author KHOA
 */
public class TblProductDAO {
      private List<TblProductDTO> acountList;
      private List<TblProductDTO> cartList;

    public List<TblProductDTO> getAcountList() {
        return acountList;
    }
     public List<TblProductDTO> getCartList() {
        return cartList;
    }
    public TblProductDTO viewCart(String sku,int qualiti,List<TblProductDTO> acountList1){
        int id=Integer.parseInt(sku);
        String name;
                 String Discription;
                 float Prices;
                 int quantity;
                 int Sku;
                 Boolean statu;
       
        if(acountList1!=null){
        for(TblProductDTO o : acountList1){
            if(o.getSku()==id){
               name=o.getName();
               Discription=o.getDiscription();
               Prices=o.getPrice();
              
               statu=o.isStatus();
                TblProductDTO dto=new TblProductDTO(id, name, Discription, qualiti, Prices, statu);
                 if(this.cartList==null){
                     this.cartList= new ArrayList<>();
                 }// end account List had Not existed
                this.cartList.add(dto);
                return dto;
                
            }
        }}
        return null;
    
    }
    
  
    
    public void viewProduct()
        throws SQLException, NamingException{
         Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
      
        try {
             // connect Db
             con = DBhelp.mameconect();
         if(con!= null){    
        // 2 write SQL commamnd
        String sql ="Select * "
                + "From TblProduct Where Status ='true' "
                ;
        //3 create Statement Obejct
           stm=con.prepareCall(sql);
           
        //4 Execute Statement to get result
        rs = stm.executeQuery();
        //5 Process result 
             while (rs.next()) {                 
                 String name = rs.getString("name");
                 String Discription = rs.getString("Discription");
                 float Prices = rs.getInt("Prices");
                 int quantity = rs.getInt("quantity");
                 int Sku = rs.getInt("sku");
                 Boolean status = rs.getBoolean("status");
                 
                 TblProductDTO dto = new TblProductDTO(Sku, name, Discription, quantity, Prices, status);
             
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
    
   
}
