package com.travel_agancy.Travel_Agency.services;

import com.travel_agancy.Travel_Agency.models.client;
import com.travel_agancy.Travel_Agency.models.voucher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class client_services {
    private Connection connection;

    public client_services() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Travel_Agency", "postgres", "qwerty");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<client> getAllclient() {
        List<client> clients_all = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients");
            while (resultSet.next()) {
                client client_m = new client();
                client_m.setFirst_name(resultSet.getString("First_name"));
                client_m.setSurname(resultSet.getString("Surname"));
                client_m.setPhone(resultSet.getString("Phone"));
                client_m.setName_vouch(resultSet.getString("name_vouch"));
                client_m.setPrice(resultSet.getInt("price"));
                client_m.setCol_day(resultSet.getInt("col_day"));
                clients_all.add(client_m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients_all;
    }

    public String search_vouch_name(int id){
        String name = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM voucher where id=" + String.valueOf(id));
            while (resultSet.next()) {
                name = resultSet.getString("name_vouch");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public int search_price(int id){
        int price = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM voucher where id=" + String.valueOf(id));
            while (resultSet.next()) {
                price = resultSet.getInt("price");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return price;
    }

    public int search_col_day(int id){
        int col_day = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM voucher where id=" + String.valueOf(id));
            while (resultSet.next()) {
                col_day = resultSet.getInt("col_day");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return col_day;
    }



    public void add_client_vouch(int id,String First_name,String Surname,String Phone){
        try{
            String sql = "INSERT INTO clients(First_name,Surname,Phone,name_vouch,price,col_day) values(?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,First_name);
            statement.setString(2,Surname);
            statement.setString(3,Phone);
            statement.setString(4,search_vouch_name(id));
            statement.setInt(5,search_price(id));
            statement.setInt(6,search_col_day(id));



            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<client> get_client_info(String Phone) {

        List<client> clients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clients where phone='"+Phone + "'");
            while (resultSet.next()) {
                client clients_m = new client();
                clients_m.setFirst_name(resultSet.getString("First_name"));
                clients_m.setSurname(resultSet.getString("Surname"));
                clients_m.setPhone(resultSet.getString("Phone"));
                clients_m.setName_vouch(resultSet.getString("name_vouch"));
                clients_m.setPrice(resultSet.getInt("price"));
                clients_m.setCol_day(resultSet.getInt("col_day"));
                clients.add(clients_m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    public void del_client(String First_name,String Surname,String Phone){
        try{
            String sql = "Delete FROM clients where First_name=? and Surname=? and Phone=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, First_name);
            statement.setString(2, Surname);
            statement.setString(3,Phone);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int total_revenue(){
        int end = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT price FROM clients");
            while (resultSet.next()) {
                end += resultSet.getInt("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return end;
    }
}
