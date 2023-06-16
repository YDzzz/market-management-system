package org.example;

import init.Init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        try {
            Class.forName("init.Init");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}