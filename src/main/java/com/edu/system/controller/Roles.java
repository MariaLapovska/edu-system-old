package com.edu.system.controller;

public enum Roles {

    ALL(1), ADMIN(3), USER(2);

    int weight;
    Roles(int weight){
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
