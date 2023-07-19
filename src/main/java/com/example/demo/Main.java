package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("driver loading");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/java30", "root", "123456");
            System.out.println("connet db");
            Statement stm = conn.createStatement();
//            stm.execute("create table if not exists brands(id int primary key auto_increment,name varchar(50))");
            int affectRow= stm.executeUpdate("insert into brands(name) values('nike')");
            System.out.println(affectRow);
            stm.executeUpdate("insert into brands(name) values('puma')");
            stm.executeUpdate("insert into brands(name) values('çonver')");

            // Truy vấn để lấy dữ liệu từ bảng "brands"
            ResultSet rs = stm.executeQuery("SELECT * FROM brands");
            // Hiển thị dữ liệu lên console
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
            conn.close();


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
