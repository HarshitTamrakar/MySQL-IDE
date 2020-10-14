/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.*;

/**
 *
 * @author 0kl
 */

public class Query_Generator {
    public static String ifNull(String db,String username,String password,String query)
    {
    try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db, username, password);
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    int col = rs.getMetaData().getColumnCount();
                    
//="ifnull("+rs.getMetaData().getColumnName(0)+",\"NULL\") as  "+rs.getMetaData().getColumnName(0);
                   
                    for(int i=0;i<col;++i)
                    {
                        String str = rs.getMetaData().getColumnName(i);
                        int index = query.indexOf(str);
                        query = query.substring(index ,str.length())+" ifnull("+rs.getMetaData().getColumnName(i)+",\"NULL\") as  "+rs.getMetaData().getColumnName(i)+query.substring(index+str.length()+1);
                        System.out.println("for "+query);
                    }
                   System.out.println(query);

                        con.close();
                    }
                 catch (Exception e) {
                   
                   
                }
        return "";
    }
    public static void main(String[] args) {
        ifNull("website", "root", "123", "select age from tabbb");
    }
    
}
