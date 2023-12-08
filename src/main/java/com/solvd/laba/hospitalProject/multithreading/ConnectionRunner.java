package com.solvd.laba.hospitalProject.multithreading;

public class ConnectionRunner implements Runnable {
private final ConnectionPool connectionPool;

 public ConnectionRunner(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
        }
    @Override
    public void run() {
        try {
            Connection connection = connectionPool.getConnection();
            System.out.println(Thread.currentThread().getName() + " got connection: " + connection.getId());

            // Simulate some work with the connection
            connection.executeQuery("SELECT * FROM table");

            connectionPool.releaseConnection(connection);
            System.out.println(Thread.currentThread().getName() + " released connection: " + connection.getId());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

