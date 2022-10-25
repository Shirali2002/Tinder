package com.abbtech.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Like {

    private int id;
    private int fromWhom;
    private int toWhom;

}