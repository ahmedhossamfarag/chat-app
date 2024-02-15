package com.example.chat;

public class User {
    public String name;
    public String host;
    public int port;

    public User(String name, String host, int port) {
        this.name = name;
        this.host = host;
        this.port = port;
    }

    @Override
    public String toString() {
        return name + ',' + host + ',' + port;
    }

    public static User of(String s){
        String[] a = s.split(",");
        return  new User(a[0], a[1], Integer.parseInt(a[2]));
    }
}
