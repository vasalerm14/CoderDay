package com.travel_agancy.Travel_Agency.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class voucher {
    int id;
    String name_vouch;
    int price;
    int col_day;

    public voucher(){}
}
