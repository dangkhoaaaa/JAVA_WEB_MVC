/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoand.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author KHOA
 */
public class DBhelp implements Serializable{
    public static Connection  mameconect()
        throws NamingException , SQLException{
        
        
        //1 get current context
        Context context = new InitialContext();
        
        //2. get tomcat context
        
        Context tomcat =(Context) context.lookup("java:comp/env");
        //3 get Ds
        DataSource ds =(DataSource) tomcat.lookup("JohnyEnglishDB");
        //4 open connection
        Connection con = ds.getConnection();
        return con;
         /*
        // 1 load drive
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //localhost
        //2 create connection string
        String url = "jdbc:sqlserver://HELLO-WORLD:1433;databaseName=dao";
        //jdbc:sqlserver://HELLO-WORLD:1433;databaseName=dao
        
        /* "jdbc:sqlserver://HELLO-WORLD:1433" 
                +"databaseName=master;instanceName=HELLO-WORLD";*/
        
        // 3 open connection
        //Connection con = DriverManager.getConnection(url, "ahko8334","21620023k");
                
        //  return con; 
          
    }
    
    public static Connection  productConnect()
        throws NamingException , SQLException, ClassNotFoundException{
        
        
        //1 get current context
        //Context context = new InitialContext();
        
        //2. get tomcat context
        
        //Context tomcat =(Context) context.lookup("java:comp/env");
        //3 get Ds
        //DataSource ds =(DataSource) tomcat.lookup("JohnyEnglishDB");
        //4 open connection
        //Connection con = ds.getConnection();
        //return con;
        
        // 1 load drive
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //localhost
        //2 create connection string
        String url = "jdbc:sqlserver://HELLO-WORLD:1433;databaseName=dao";
        //jdbc:sqlserver://HELLO-WORLD:1433;databaseName=dao
        
        // "jdbc:sqlserver://HELLO-WORLD:1433" 
          //      +"databaseName=master;instanceName=HELLO-WORLD";
        
        // 3 open connection
        Connection con = DriverManager.getConnection(url, "ahko8334","21620023k");
                
         return con; 
          
    }
        
    
}
