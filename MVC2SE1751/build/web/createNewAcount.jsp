<%-- 
    Document   : createNewAcount
    Created on : 10-03-2023, 09:00:45
    Author     : KHOA
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div>Create new account</div>
        
        <form action="createAccountController" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
            
            Username*  <input type="text" name="txtUsername" value="${param.txtUsername}" size="20"/>    (6 to 20 chars)  <br/>
              <c:if test="${not empty errors.usernameLengthError}">
                  <font color="red" >
                  ${errors.usernameLengthError}
                  </font><br/>
                  
              </c:if>
                <c:if test="${not empty errors.usernameisExit}">
                  <font color="red" >
                  ${errors.usernameisExit}
                  </font><br/>
                  
              </c:if>
                  Password*  <input type="password" name="txtpassword" value="" size="20"/>    (6 to 30 chars)  <br/>  
             <c:if test="${not empty errors.passwordLengthError}">
                  <font color="red" >
                  ${errors.passwordLengthError}
                  </font><br/>
                  
              </c:if>
                  Comfirm*  <input type="password" name="txtComfirm" value="" size="20"/>   <br/>  
             <c:if test="${not empty errors.confirmNotMatchedError}">
                  <font color="red" >
                  ${errors.confirmNotMatchedError}
                  </font><br/>
                  
              </c:if>
            Lastname*  <input type="text" name="txtFullname" value="${param.txtFullname}" size="20"/>    (2 to 50 chars)  <br/> 
             <c:if test="${not empty errors.fullnameLengthError}">
                  <font color="red" >
                  ${errors.fullnameLengthError}
                  </font><br/>
                  
              </c:if>
            <input type="submit" name="btAction" value="Create new acount" />      <br/> 
            <input type="reset" value="Reset" />      <br/> 
            
        </form>
             
               <a href="loginPage"> Login</a><br/>
    </body>
</html>
