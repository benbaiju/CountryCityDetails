package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse {
    private boolean error;
    private String msg;
    private List<Countries> Data;

    public List<Countries> getData() {
        return Data;
    }
}
