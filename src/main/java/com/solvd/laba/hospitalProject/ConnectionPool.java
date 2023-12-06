package com.solvd.laba.hospitalProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static final int MAX_POOL_SIZE = 10;
    private static final BlockingQueue<Connection> pool = new LinkedBlockingQueue<>(MAX_POOL_SIZE);

    // Lazy initialization of the connection pool
    private static boolean initialized = false;

    private static synchronized void initializePool() {
        if (!initialized) {
            try {
                Class.forName("your.jdbc.driver.Class"); // Replace with your actual JDBC driver class
                for (int i = 0; i < MAX_POOL_SIZE; i++) {
                    Connection connection = DriverManager.getConnection("your.jdbc.url", "username", "password");
                    pool.offer(connection);
                }
                initialized = true;
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                // Handle initialization failure
            }
        }
    }

    public static Connection getConnection() throws InterruptedException {
        if (!initialized) {
            initializePool();
        }
        return pool.take();
    }

    public static void releaseConnection(Connection connection) {
        pool.offer(connection);
    }
}

