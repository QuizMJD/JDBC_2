package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
    public static void main(String[] args){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("driver loading");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/java30", "root", "123456");
            System.out.println("connet db");
            Statement stm = conn.createStatement();
            stm.execute("create table if not exists brands(id int primary key auto_increment,name varchar(50))");
            conn.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
