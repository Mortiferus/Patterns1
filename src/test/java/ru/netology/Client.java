package ru.netology;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Client {
    private String city;
    private String data;
    private String name;
    private String phone;


}
