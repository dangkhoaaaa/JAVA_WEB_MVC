<%-- 
    Document   : onlineShopping
    Created on : 01-03-2023, 19:55:59
    Author     : KHOA
--%>


<%@page import="khoand.cart.CartObjectForStore"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map"%>
<%@page import="khoand.cart.CartObject"%>
<%@page import="khoand.tblProduct.TblProductDAO"%>
<%@page import="java.util.List"%>
<%@page import="khoand.tblProduct.TblProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Store</title>
    </head>
    <body>
        <h1>Store!</h1>
        <c:set var="result" value="${requestScope.LIST_PRODUCT}"/>
         <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
        <c:if test="${not empty result}">

            <c:if test="${not empty errors.usernameisExit}">
                  <font color="red" >
                  ${errors.passwordNull}
                  </font><br/>
                  
              </c:if>                       
                                   
        <table border="1">
            <thead>
                <tr>
                    <th>NO.</th>
                    <th>SKU</th>
                    <th>Name</th>
                    <th>Discription</th>
                    <th>Price</th>
                    <th>Add</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                                  <c:forEach var="dto" items="${result}" varStatus="counter">
                         
                     
                         
                                   <tr>
                                  <td>
                                     ${counter.count}
                                   .</td>
                                  <td> 
                                      ${dto.getSku()} 
                                    
                                  </td>
                                  <td> 
                                      ${dto.getName()} 
                                    
                                  </td>
                                  <td>
                                      ${dto.getDiscription()} 
                                      
                                  </td>
                                  <td> 
                                      ${dto.getPrice()}
                                  </td>
                                  
                                  <td>
                                       <form action="addToCartStore">
                                      <input type="submit" name="btAction" value="Add to cart"/>
                                      <input type="hidden" name="SkuValue" 
                                             value="${dto.getSku()}"/>
                                      </form>
                                  </td>
                                     <td>
                                       <form action="removeToCartStore">
                                      <input type="submit" name="btAction" value="Delete to cart"/>
                                      <input type="hidden" name="SkuValue" 
                                             value="${dto.getSku()}"/>
                                      </form>
                                  </td>
                                 
                              </tr>
                                
                          
                          </c:forEach>
            </tbody>
        </table>
                            </c:if>
        
                      <br/>
                       <h1>Your Cart include</h1>
                      <br/>
                      <c:if test="${empty result}">
                          
                      
                      </c:if>
            <c:set var="resultStore" value="${requestScope.CARTSTOREVIEW}"/>
            <c:if test="${not empty resultStore}">
                  
                       
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>SKU</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Prices</th>
                            <th>ADD</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="map" items="${resultStore}" varStatus="counter">
                     
        
                         <tr>
                            <td>
                            ${counter.count}
                            .</td>
                            <td>
                            ${map.getSku()}
                            
                            </td>
                            <td>
                                 ${map.getName()}
                            
                            </td>
                            <td>
                                <font color="blue">
                                 ${map.getQuantity()}</font>
                            </td>
                            <td>
                       
                               ${map.getPrice()*map.getQuantity()}
                            </td>
                            <td>
                                       <form action="addToCartStore">
                                      <input type="submit" name="btAction" value="Add to cart"/>
                                      <input type="hidden" name="SkuValue" 
                                             value="${map.getSku()}"/>
                                      </form>
                                  </td>
                                  
                                  <td>
                                       <form action="removeToCartStore">
                                      <input type="submit" name="btAction" value="Delete to cart"/>
                                      <input type="hidden" name="SkuValue" 
                                             value="${map.getSku()}"/>
                                      </form>
                                  </td>
                        </tr>
                        
                        
                    
                        </c:forEach>    
                    </tbody>
                </table>
               
                
                
                
              <br/>
               <font color="red">
           <!-- <h2> Your Total Price <font color="yellow"></font></h2> -->
             </font>  
          
                     <c:set var="check" value="${sessionScope.CHECK}"/>
                      <c:set var="Cusname" value="${param.Cusname}"/>
                      <c:set var="CusAdd" value="${param.CusAdd}"/>
             <form action="checkOutServlet">
                             
                                  NAME *<input type="text" value="${Cusname}" name="Cusname"/>
                                  
                                  <br/>
                                  ADDRESS *<input type="text" value="${CusAdd}" name="CusAdd"/>
                                  <br/>
                                 
                                  <input type="submit" value="Check Out" name="btAction"/> <c:if test="${not empty check}"><font color="red">  dont may Name or Address are emtry </font> </c:if>
                 <br/>
                                  <c:set var="result_order" value="${requestScope.ORDER_PRODUCT}"/>
                 <c:if test="${not empty result_order}">
                           <br/>
                         <font color="red">
            <h2>Your order will be delivered</h2> 
             </font> <br/>
                          <table border="1">
                              <thead>
                                  <tr>
                                      <th>ID BIll</th>
                                      <th>Your Name</th>
                                      <th>Your Address</th>
                                      <th>Date</th>
                                      <th>Total Price</th>
                                  </tr>
                              </thead>
                              <tbody>
                                  <tr>
                                      <td>${result_order.id}</td>
                                      <td>${result_order.name}</td>
                                      <td>${result_order.address}</td>
                                      <td>${result_order.date}</td>
                                      <td>${result_order.total}</td>
                                      
                                  </tr>
                                  
                              </tbody>
                          </table>

                          
                      </c:if>
             
             </form>   
             
                 
                 
         </c:if>
             <c:if test="${empty resultStore}">
<font color="red">
            <h2> No cart is existed!!!</h2> 
             </font> 
             </c:if>

       <a href="loginPage"> Login</a><br/> 
             
                      
                      
    </body>
</html>
<%--
    <body>
        <h1>Store!</h1>
        <c:set var="result" value="${requestScope.LIST_PRODUCT}"/>
        <c:if test="${not empty result}">
            <%   
                 
      //                            List<TblProductDTO> result = (List<TblProductDTO>)
       //                           request.getAttribute("LIST_PRODUCT");
                                   
                                  
        //                           if(result != null){     
                                  %>
                                  
                                   
        <table border="1">
            <thead>
                <tr>
                    <th>NO.</th>
                    <th>SKU</th>
                    <th>Name</th>
                    <th>Discription</th>
                    <th>Price</th>
                    <th>Add</th>
                    <th>Delete</th>
                </tr>
            </thead>
            <tbody>
                                  <c:forEach var="dto" items="${result}" varStatus="counter">
                              <%   
                                 
                                   //int count =0;
                                   //for(TblProductDTO dto : result){
                                       
                                   %>
                     
                         
                                   <tr>
                                  <td>
                                     <%= ++count %> 
                                   .</td>
                                  <td> 
                                      <%= dto.getSku()%> 
                                    
                                  </td>
                                  <td> 
                                      <%= dto.getName()%> 
                                    
                                  </td>
                                  <td>
                                      <%= dto.getDiscription()%> 
                                      
                                  </td>
                                  <td> 
                                      <%= dto.getPrice() %> 
                                  </td>
                                  
                                  <td>
                                       <form action="DispathServerlet">
                                      <input type="submit" name="btAction" value="Add to cart"/>
                                      <input type="hidden" name="SkuValue" 
                                             value="<%= dto.getSku() %>"/>
                                      </form>
                                  </td>
                                     <td>
                                       <form action="DispathServerlet">
                                      <input type="submit" name="btAction" value="Delete to cart"/>
                                      <input type="hidden" name="SkuValue" 
                                             value="<%= dto.getSku() %>"/>
                                      </form>
                                  </td>
                                 
                              </tr>
                                
                              <%
                                   
                            //       }//end traverse DTO to show data
                              
                              
                              %>
                         
            </tbody>
        </table>
                            </c:if>
                               <%
                  }
                      %>
                      <c:if test="${empty result}">
                      
                       <h1>Your Cart include</h1>
                      </c:if>
        <%
            //1. Custom goes to his/her cart place // toi cho lay gio
            if(session != null){
            //2 Customer takes his/her cart
            CartObjectForStore cart = (CartObjectForStore)session.getAttribute("CARTSTORE");
            if(cart != null){
            //3.Customer takes items form cart
              Map<String,TblProductDTO> items = cart.getItems();
              if(items != null){
                //4. show items
                %>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>SKU</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Prices</th>
                            <th>ADD</th>
                            <th>DElete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%  int count=0;
                            float total=0;
                           
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
                                 <%= //items.get(key).getQuantity()  
                                     key%>
                            
                            </td>
                            <td>
                       
                                 <%= //items.get(key).getQuantity()  
                                     key%>
                            </td>
                            <td>
                       
                                <%= //items.get(key).getQuantity()*items.get(key).getPrice() 
                                          key
                                %>
                                
                                <% // total+=items.get(key).getQuantity()*items.get(key).getPrice();
                                    
                                %>
                            </td>
                            <td>
                                       <form action="DispathServerlet">
                                      <input type="submit" name="btAction" value="Add to cart"/>
                                      <input type="hidden" name="SkuValue" 
                                             value="<%= items.get(key).getSku() %>"/>
                                      </form>
                                  </td>
                                  
                                  <td>
                                       <form action="DispathServerlet">
                                      <input type="submit" name="btAction" value="Delete to cart"/>
                                      <input type="hidden" name="SkuValue" 
                                             value="<%= items.get(key).getSku() %>"/>
                                      </form>
                                  </td>
                        </tr>
                        
                        
                        <%  }
                                // return;
                            // travaerse Map
                            %>
                            
                    </tbody>
                </table>
  
                
                
                
                <%
                   %>
               <font color="red">
            <h2> Your Total Price <font color="blue"> <%=total %> </font></h2> 
             </font>  
             
             <form action="DispathServerlet">
                             
                                  NAME *<input type="text" value="" name="Cusname"/><br/>
                                  ADDRESS *<input type="text" value="" name="CusAdd"/><br/>
                                  <input type="submit" value="Check Out" name="btAction"/>
                 </form>
    
    <% 
                  
              }else{%>
<font color="red">
            <h2> No cart is existed!!!</h2> 
             </font> 

<%}//items have values
            }else{%>
<font color="red">
            <h2> No cart is existed!!!</h2> 
             </font> 

<%}

//cart has existed
            
            
            
}//session has existed - cart place had not existed
        
          
        
        %>
        
                         
                      
                      
    </body>
</html>
*/--%>