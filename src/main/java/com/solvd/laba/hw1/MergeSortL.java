package com.solvd.laba.hw1;

import java.util.Arrays;

public class MergeSortL {
    public static int[] mergeSort(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;
            int[] left = Arrays.copyOfRange(arr, 0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, arr.length);

            mergeSort(left);   // the array is divided into smaller and smaller subarrays until each subarray contains only one element
            mergeSort(right);

            merge(arr, left, right); //merging process combines the sorted subarrays into a single sorted array.
        }
        return arr;
    }

    private static void merge(int[] arr, int[] left, int[] right) {   //This method is responsible for merging the two subarrays into a sorted array.
        int i = 0, j = 0, k = 0; //initialize three variables: i for iterating through the left array, j for iterating through the right array, and k for iterating through the original array arr.

        while (i < left.length && j < right.length) { //runs as long as there are elements remaining in both the left and right subarrays.
            if (left[i] < right[j]) {
                arr[k] = left[i];
                i++;//is incremented
            } else {
                arr[k] = right[j];
                j++;//is incremented
            }
            k++; //incremented to indicate the next position where an element will be placed
        }

        while (i < left.length) { // for remaining elements
            arr[k] = left[i];
            i++;
            k++;
        }

        while (j < right.length) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please enter numbers to be sorted");
            return; // Exit the program if there are no input elements.
        }

        int num = args.length;
        int[] arr = new int[num]; // Declare the 'arr' array for storing the input elements.

        for (int i = 0; i < args.length; i++) {
            arr[i] = Integer.parseInt(args[i]); // Populate the 'arr' array.
        }

        System.out.println("Before");
        System.out.println(Arrays.toString(arr)); // Use 'arr' to print the original array.
        System.out.println("After");
        System.out.println(Arrays.toString(mergeSort(arr))); // Use 'arr' to call mergeSort.
    }

}
