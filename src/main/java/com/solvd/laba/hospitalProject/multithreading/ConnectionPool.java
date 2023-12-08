package com.solvd.laba.hospitalProject.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static ConnectionPool instance;
    private final List<Connection> pool;
    private final Lock lock;

    private ConnectionPool(int size) {
        this.pool = new ArrayList<>(size);
        this.lock = new ReentrantLock();

        for (int i = 0; i < size; i++) {
            pool.add(new Connection());
        }
    }

    public static synchronized ConnectionPool getInstance(int size) {
        if (instance == null) {
            instance = new ConnectionPool(size);
        }
        return instance;
    }

    public Connection getConnection() throws InterruptedException {
        lock.lock();
        try {
            while (pool.isEmpty()) {
                Thread.sleep(100);
            }
            return pool.remove(pool.size() - 1);
        } finally {
            lock.unlock();
        }
    }

    public void releaseConnection(Connection connection) {
        lock.lock();
        try {
            pool.add(connection);
        } finally {
            lock.unlock();
        }
    }

    public CompletableFuture<Connection> getConnectionAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return getConnection();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
