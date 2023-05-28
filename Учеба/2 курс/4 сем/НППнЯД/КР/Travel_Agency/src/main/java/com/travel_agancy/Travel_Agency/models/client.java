package com.travel_agancy.Travel_Agency.models;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class client {
    String First_name;
    String Surname;
    String Phone;
    String name_vouch;
    int price;
    int col_day;
    public client(){}
}


