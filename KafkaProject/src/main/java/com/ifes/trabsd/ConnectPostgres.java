/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifes.trabsd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectPostgres {
    private String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/jqmltnoq";
    private String username = "jqmltnoq";
    private String password = "pWYbeG6Ij3zF6mJwxbhjxs0auts9Xhz3";
    private ResultSet rs;
    private Statement st;
    public static Connection db;
    

    public ConnectPostgres() {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        try {
            db = DriverManager.getConnection(this.url,this.username, this.password);
        } 
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeConnection(){
        try {
            this.db.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void closeStatement() {
        try {
            this.st.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void closeResultSet() {
        try {
            this.rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
public static Connection getDb() {
        return db;
    }

    public static void setDb(Connection db) {
        ConnectPostgres.db = db;
    }
    //Atualiza coisas no banco
    public void executeSql(String query) {
        try {
            st = db.createStatement();
            
            //exeucta o sql no meu banco de dados
            st.executeUpdate(query);
            //st.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        //closeConnection();
    }

    public ArrayList retrieveData(String query){
        ArrayList result = new ArrayList();
        ArrayList linhas;
        
        try {
            st = db.createStatement();

            rs = st.executeQuery(query);
            //rs é um conjunto de linhas 
            while (rs.next()) {
                linhas = new ArrayList();
                /*adiciona os valores resultantes da query na arraylist, 
                colocar as colunas corretas
                */
                linhas.add(rs.getString(2)); //getString retorna valor da coluna
                linhas.add(rs.getString(3));
                result.add(linhas);
            }
            rs.close();
            st.close();
        } catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
        //closeConnection();
        return result;
    }

}