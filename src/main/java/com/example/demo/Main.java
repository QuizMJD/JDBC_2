package com.example.demo;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void searchBrandByName(Connection conn, String searchKeyword) throws SQLException {
        String searchQuery = "SELECT * FROM brands WHERE name LIKE ?";
        PreparedStatement searchStatement = conn.prepareStatement(searchQuery);
        searchStatement.setString(1, "%" + searchKeyword + "%");
        ResultSet rs = searchStatement.executeQuery();

        // Hiển thị kết quả tìm kiếm
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.println("ID: " + id + ", Tên: " + name);
        }
    }
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

            // Assume we want to update the name of the brand with ID 1
            String updateQuery = "UPDATE brands SET name = ? WHERE id = ?";
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
            updateStatement.setString(1, "new_brand_name");
            updateStatement.setInt(2, 1);
            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
            // Assume we want to delete the brand with ID 3
            String deleteQuery = "DELETE FROM brands WHERE id = ?";
            PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, 3);
            int rowsDeleted = deleteStatement.executeUpdate();
            System.out.println("Rows deleted: " + rowsDeleted);
            // Mã đã có sẵn cho việc kết nối cơ sở dữ liệu và thêm dữ liệu ...

            // Tạo đối tượng Scanner để đọc dữ liệu nhập từ bàn phím
            Scanner scanner = new Scanner(System.in);

            System.out.println("Nhập tên thương hiệu cần tìm kiếm:");
            String searchKeyword = scanner.nextLine();

            // Thực hiện tìm kiếm dựa trên dữ liệu nhập từ người dùng
            searchBrandByName(conn, searchKeyword);

            // Đóng đối tượng Scanner và kết nối...
            scanner.close();
            conn.close();


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
