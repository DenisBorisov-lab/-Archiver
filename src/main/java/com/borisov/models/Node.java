package com.borisov.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Node {
    private String name;
    private int weight;
    private List<Node> children;


}
