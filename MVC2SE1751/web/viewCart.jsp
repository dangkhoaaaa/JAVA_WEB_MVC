<%-- 
    Document   : viewCart
    Created on : 28-02-2023, 00:19:37
    Author     : KHOA
--%>

<%@page import="java.util.Map"%>
<%@page import="khoand.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Your Cart include</h1>
        <%
            //1. Custom goes to his/her cart place // toi cho lay gio
            if(session != null){
            //2 Customer takes his/her cart
            CartObject cart = (CartObject)session.getAttribute("CART");
            if(cart != null){
            //3.Customer takes items form cart
              Map<String,Integer> items = cart.getItems();
              if(items != null){
                //4. show items
                %>
                <form action="removeController">
                
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  int count=0;
                            for(String key: items.keySet()){
                            %>
                        
                         <tr>
                            <td>
                            <%= ++count %>
                            .</td>
                            <td>
                            <%= key %>
                            
                            </td>
                            <td>
                       
                                <%= items.get(key)    %>
                            </td>
                            <td>
                
                                <input type="checkbox" name="chkItem"
                                       value="<%= key    %>"/>
                            </td>
                        </tr>
                      
                        
                        <%
                                // return;
                            }// travaerse Map
                            %>
                              <tr>
                            <td colspan="3">
                                <a href="onlineShopping.html">Add more Book to Cart</a>
                            </td>
                            <td>
                                <input type="submit" value="Remove Selected Items" name="btAction"/>
                            </td>
                          
                        </tr>
                    </tbody>
                </table>
                  </form>
                              <form action="DispathServerlet">
                             
                                  NAME<input type="text" value="" name="Cusname"/><br/>
                                  ADDRESS<input type="text" value="" name="CusAdd"/><br/>
                                  <input type="submit" value="Check Out" name="btAction"/>
                 </form>
                
                <%
                    
                   return;
              }//items have values
            }

//cart has existed
            
            
            
}//session has existed - cart place had not existed
        
          
        
        %>
        
        <h2> No cart is existed!!!</h2>
    </body>
</html>
