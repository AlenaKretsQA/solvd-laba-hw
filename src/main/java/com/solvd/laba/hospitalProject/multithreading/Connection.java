package com.solvd.laba.hospitalProject.multithreading;

class Connection {
    private static int counter = 0;//number of connections
    private static String number = "0";

    private final int id;

    public Connection() {
        this.id = ++counter;
    } //create new connection and assing id

    public static String getNumber() {
        return number;
    } //current value of number

    public static void setNumber(String number) {
        Connection.number = number;
    }

    public int getId() {
        return id;
    }

    public void executeQuery(String query) {
        System.out.println("Executing query on connection " + id + ": " + query);
    }
}

