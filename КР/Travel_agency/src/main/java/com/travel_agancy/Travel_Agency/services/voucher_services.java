package com.travel_agancy.Travel_Agency.services;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.travel_agancy.Travel_Agency.models.voucher;


import java.sql.*;

public class voucher_services {

    private Connection connection;

    public voucher_services() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Travel_Agency", "postgres", "qwerty");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<voucher> getAllvouch() {
        List<voucher> vouchers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM voucher");
            while (resultSet.next()) {
                voucher voucher_m = new voucher();
                voucher_m.setId(resultSet.getInt("id"));
                voucher_m.setName_vouch(resultSet.getString("name_vouch"));
                voucher_m.setPrice(resultSet.getInt("price"));
                voucher_m.setCol_day(resultSet.getInt("col_day"));
                vouchers.add(voucher_m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vouchers;
    }

    public void remove_vouch(int id){
        try{
            String sql = "Delete FROM voucher where id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add_vouch(int id,String name_vouch,int price,int col_day){
        try{
            String sql = "INSERT INTO voucher(id,name_vouch,price,col_day) values(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2,name_vouch);
            statement.setInt(3,price);
            statement.setInt(4,col_day);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






}
