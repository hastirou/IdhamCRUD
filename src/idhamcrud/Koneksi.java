/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idhamcrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MSI GAMING
 */
public class Koneksi {
    public static Connection koneksi;
    
    public static Connection koneksis() 
        throws SQLException,ClassNotFoundException,ClassCastException{
        if(koneksi==null){
            try{
                String url="jdbc:mysql://localhost:3306/idham_crud";
                String user="root";
                String password="";
                
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi=(Connection) DriverManager.getConnection(url,user,password);
                System.out.println("Koneksi berhasil");
            }catch(SQLException t){
                System.out.println("error membuat koneksi");
            }
        }
        
        return koneksi;
    }
}
