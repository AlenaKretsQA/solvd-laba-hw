package com.solvd.laba.hospitalProject.multithreading;

public class MyThread extends Thread{

//add this class for practice purpose
    @Override
    public void run(){

    }
    public static void main(String[] args) {
        // Create an instance of MyThread
        MyThread myThread = new MyThread();

        // Start the thread
        myThread.start();
    }
}

