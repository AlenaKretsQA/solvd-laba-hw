package com.solvd.laba.hospitalProject.multithreading;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadMain {

    public static void main(String[] args) throws InterruptedException {
        runThreads();
        System.out.println("ASYNC");
        runThreadsAsync();

        // Create a new connection
        //Connection connection1 = new Connection();
        //Connection connection2 = new Connection();

        // Get and set the 'number' variable
        //System.out.println(Connection.getNumber()); // Prints the current value of 'number'
        //Connection.setNumber("123"); // Sets a new value for 'number'

         // Execute a query on a connection
        //connection1.executeQuery("SELECT * FROM table");
        //connection2.executeQuery("UPDATE users SET name='John' WHERE id=1");
    }

    public static void runThreads() throws InterruptedException {
        int poolSize = 7;
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);

        for (int i = 0; i < poolSize; i++) {
            new Thread(new ConnectionRunner(connectionPool)).start();
            Thread.sleep(1000);
        }

        System.out.println("Final counter value: " + Connection.getNumber());
    }

    public static void runThreadsAsync() {
        int poolSize = 7;
        ConnectionPool connectionPool = ConnectionPool.getInstance(5);
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i < poolSize; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                ConnectionRunner connectionRunner = new ConnectionRunner(connectionPool);
                connectionRunner.run();
            }, executorService);

            futures.add(future);
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        try {
            allOf.get(); // Wait for all CompletableFuture to complete
            System.out.println("Final counter value: " + Connection.getNumber());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        } finally {
            executorService.shutdown();
        }
    }
}