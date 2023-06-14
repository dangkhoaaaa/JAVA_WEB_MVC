
package khoand.tblOrder;

import khoand.registration.*;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import javax.naming.NamingException;
import khoand.tblOrder.TblOrderDTO;
import khoand.tblProduct.TblProductDTO;
import khoand.util.DBhelp;

/**
 *
 * @author KHOA
 */
public class TblOrderDAO{
    private List<TblOrderDTO> acountList;
 

    public List<TblOrderDTO> getAcountList() {
        return acountList;
    }
    
    public  TblOrderDTO createOrder(List<TblProductDTO> dto,String name,String add) throws SQLException, NamingException{
    Connection con = null;
        PreparedStatement stm = null;
        TblOrderDTO result=null;
        ResultSet rs = null;
        int size=dto.size();
        if(size<1)return null;
        float totaldetel=0;
        float totalbill=0;
        int effectRows=0;
        String sql;
        long millis=System.currentTimeMillis();   java.sql.Date date=new java.sql.Date(millis);
        //Random rd = new Random();
        int number1 =0; 
         
        try {
             // connect Db
             con = DBhelp.mameconect();
         if(con!= null){ 
             // dem so hang cua tblorder
            sql=  "SELECT id " +"  FROM TblOrder";
         stm=con.prepareStatement(sql);
         //rs=stm.executeQuery();
         //number1= rs.getInt("number1")+1;
         rs = stm.executeQuery();
         while(rs.next()){
         number1++;
         }
         //number1=rs.getMetaData().getColumnCount();
        String orderid=String.valueOf(number1+2);
         
         // tao ra row tblorder    
         sql= "Insert Into TblOrder "
                   + "(id, name, address, date, total"
                   +") Values("
                   +"?, ?, ?, ?, ?"
                   +")";  
            
           stm=con.prepareStatement(sql);
           stm.setString(1, orderid);
           stm.setString(2,name);
           stm.setString(3, add);
           stm.setDate(4,date);
           stm.setFloat(5, totalbill);
          
           effectRows = stm.executeUpdate();  
              
             
           for (int i = 0; i < size; i++){
            //tao ra row tbldetail         
           sql= "Insert Into TblDetails "
                   + "(orderid, sku, quatity, price, total"
                   +") Values("
                   +"?, ?, ?, ?, ?"
                   +")";
        
           stm=con.prepareStatement(sql);
           stm.setString(1, orderid);
           stm.setInt(2, dto.get(i).getSku());
           stm.setInt(3, dto.get(i).getQuantity());
           stm.setFloat(4, dto.get(i).getPrice());
           totaldetel=dto.get(i).getQuantity()*dto.get(i).getPrice();
           stm.setFloat(5, totaldetel);
           totalbill+=totaldetel;
           stm.executeUpdate();
           
           //update quantily product in db
           sql= "Update TblProduct "
                   + "Set Quantity = TblProduct.Quantity - ? "
                   +"Where Sku = ?";
       
           stm=con.prepareStatement(sql);
           stm.setInt(1,dto.get(i).getQuantity() );
           
           stm.setInt(2, dto.get(i).getSku() );
          stm.executeUpdate();

             }
           // update lai product
         sql= "Update TblOrder "
                   + "Set total = ? "
                   +"Where id = ?";
       
           stm=con.prepareStatement(sql);
           stm.setFloat(1, totalbill );
           
           stm.setString(2, orderid );
          
    
       effectRows = stm.executeUpdate();
         
           
           
          
        //4 Execute Statement to get result
     
        //5 Process result 
            if(effectRows >0){
                result= new TblOrderDTO(orderid, name, add, date, totalbill);
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
