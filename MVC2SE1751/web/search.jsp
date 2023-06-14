<%-- 
    Document   : search
    Created on : 17-02-2023, 08:14:49
    Author     : KHOA
--%>

<%--<%@page import="khoand.registration.RegistrationDTO"%>
<%@page import="java.util.List"%> --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
   
        <font color="red">
        <c:set var="wel" value="${sessionScope.USER}"/> 
        <c:if test="${not empty wel}" >
                Welcome,  ${sessionScope.USER.getLastname()}                       
                                            
        </font> <br/>
         <br/>
          <c:set var="searchValue" value="${param.txtseach}"/>
         <form action="searchController" method="POST">
             Search Value <input type="text" name="txtseach" value="${searchValue}"/>
            <input type="submit" value="Search" name="btAction" />
            
            <c:set var="errorValue" value="${param.error}"/>
            <c:if test="${not empty errorValue}" >
                <br/> 
                 <font color="red">
          ${errorValue} 
             </font>
            </c:if>
             
            <c:set var="erroradminValue" value="${param.errorAdmin}"/>
            <c:if test="${not empty erroradminValue}" >
                <br/> 
                 <font color="red">
          ${erroradminValue} 
             </font>
            </c:if>
            
        </form><br/>
      
       
               <c:if test="${not empty searchValue}">
                 <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>  
                 <c:if test="${not empty result}">
                     <table border="1">
                          <thead>
                              <tr>
                                  <th>No.</th>
                                  <th>Username</th>
                                  <th>Password</th>
                                  <th>Full name</th>
                                  <th>Role</th>
                                  <th>Delete</th>
                                  <th>Update</th>
                                  
                              </tr>
                          </thead>
                         <tbody>
                             <c:forEach var="dto" items="${result}" varStatus="counter">
                             <form action="updateController">
                             
                                 <tr>
                                   <td>
                                       ${counter.count}
                                       .</td>
                                  <td>
                                  ${dto.username}
                                  <input type="hidden" name="txtUsername" 
                                             value="${dto.username}"/>
                                  </td>
                                  <td>
                                       
                                     <input type="text" name="txtPassword" 
                                             value="${dto.password}"/>  
                                     <input type="hidden" name="txtPassword1" 
                                             value="${dto.password}"/>  
                                 
                                  </td>
                                  <td>
                                  ${dto.lastname}
                                  </td>
                                  <td>
                                      <input type="checkbox" value="true" name="chkAdmin"
                                             <c:if test="${dto.role}"> 
                                                 checked="checked"
                                             
                                             </c:if>
                                            
                                             />
                                      
                                  ${dto.role}
                                  <input type="hidden" name="roleuser"
                                              value="${sessionScope.USER.isRole()}"/>
                                  </td>
                                   <td>
                                       <c:url var="deleteLink" value="deleteController">
                                           <c:param name="btAction" value="Delete"/>
                                           <c:param name="pk" value="${dto.username}"/>
                                           <c:param name="role" value="${sessionScope.USER.isRole()}"/>
                                           <c:param name="pkyo" value="${sessionScope.USER.getUsername()}"/>
                                           
                                           <c:param name="lastSearchValue" value="${searchValue}"/>
                                           
                                       </c:url>
                                       <a href="${deleteLink}">Delete </a>
                                  </td>
                                  <td>
                                      <input type="submit" value="Update" name="btAction"/>
                                      <input type="hidden" name="lastSearchValue"
                                              value="${searchValue}"/>
                                      <input type="hidden" name="nameuser"
                                              value="${sessionScope.USER.getUsername()}"/>
                                      <input type="hidden" name="passuser"
                                              value="${sessionScope.USER.getPassword()}"/>
                                      
                                  </td>
                                 </tr>
                                 </form>
                             </c:forEach>
                         </tbody>
                     </table>

                     
                     
                     
                                      </c:if>
                     <c:if test="${empty result}">
                         <h2>
                             No record is matched!!!
                         </h2>
                     </c:if>
                     
               
               </c:if>
       
               <br/>
         <form action="logOutController" >

            <input type="submit" value="Logout" name="btAction" />
         </form>
                </c:if>
         <c:if test="${empty wel}" >
             Your session was time out
              <a href="loginPage"> Click here to Login!</a><br/>
             
         </c:if>
        </body>

        <%--<%
         //lastSearchValue
         Cookie[] cookies = request.getCookies();
          if(cookies!= null){
            
             //Cookie lastCookie = cookies[cookies.length -1 ];
             //String username = lastCookie.getName();
              String username="";
            for(Cookie o : cookies) {
                    String temp=o.getName();
                    if(!temp.equals("JSESSIONID")){
                      username=temp;
                    }
                }
 
            
             
          //String username = lastCookie.getName();
          %>
          <font color="red">
             Welcome, <%= username %>
             </font> 
          <%
          }//end cookiies are exixed
        %>
        <h1> Search PAGE</h1>
        <form action="DispathServerlet" >
            Search Value <input type="text" name="txtseach" 
                               value="<%= request.getParameter("txtseach") %>" /><br/>
            <input type="submit" value="Search" name="btAction" />
            
        </form> <br>
        <%
            String searchValue = request.getParameter("txtseach");
            
              if(searchValue != null){
                    //get attribute phai ep kieu tuong minh
                  List<RegistrationDTO> result = (List<RegistrationDTO>)
                          request.getAttribute("SEARCH_RESULT");
                  if(result != null){
                               //scriptlet co ten goi la fracment code
                      %>
        
                      <table border="1">
                          <thead>
                              <tr>
                                  <th>No.</th>
                                  <th>Username</th>
                                  <th>Password</th>
                                  <th>Full name</th>
                                  <th>Role</th>
                                  <th>Delete</th>
                                  <th>Update</th>
                              </tr>
                          </thead>
                          <tbody>
                              <%   
                                   int count =0;
                                   for(RegistrationDTO dto : result){
                                       String urlRewriting = "DispathServerlet"
                                       +"?btAction=Delete"
                                       +"&pk="+ dto.getUsername()
                                       +"&lastSearchValue=" +searchValue;
                                   %>
                          <form action="DispathServerlet" >
                         
                                   <tr>
                                  <td>
                                     <%= ++count %> 
                                   .</td>
                                  <td> 
                                      <%= dto.getUsername() %> 
                                      <input type="hidden" name="txtUsername" 
                                             value="<%= dto.getUsername() %>"/>
                                  </td>
                                  <td>
                                      <%= dto.getPassword() %> 
                                      <input type="text" name="txtPassword" 
                                             value="<%= dto.getPassword() %>"/>
                                  </td>
                                  <td> 
                                      <%= dto.getFullname() %> 
                                  </td>
                                  <td>
                                      <%= dto.isRole() %> 
                                      <input type="checkbox" name="chkAdmin" value="true"
                                      <%
                                         if(dto.isRole()){
                                         %>
                                         checked="checked"
                                         
                                         <%
                                         } // role is Admin
                                      
                                      
                                      %>/>
                                  </td>
                                  <td>
                                      <a href="<%= urlRewriting %>">Delete</a>
                                  </td>

                                  <td>
                                  
                                      <input type="submit" name="btAction" value="Update"/>
                                      <input type="hidden" name="lastSearchValue" 
                                             value="<%= searchValue %>"/>
                                  </td>
                                 
                              </tr>
                                 </form>
                              <%
                                   
                                   }//end traverse DTO to show data
                              
                              
                              %>
                          </tbody>
                      </table>

        
        
        <%
                  }else{
                      %>
        
                      <h2> No record is matched!!!!!</h2>
                        
        
        <%
                  } // end no record is mathched
              }// end search Value has been loaded
            
            
            
            %>
            <form action="DispathServerlet" >

            <input type="submit" value="Logout" name="btAction" />
            
        </form> <br>   
    --%>

</html>
