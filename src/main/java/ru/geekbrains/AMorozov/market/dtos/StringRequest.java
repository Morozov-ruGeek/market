package ru.geekbrains.AMorozov.market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StringRequest {
    private char[] str;
}