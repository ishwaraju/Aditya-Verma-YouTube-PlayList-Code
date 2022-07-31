package com.ishwaraju.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class describe the whether from the given array sum is possible or not
 * The main method contains custom and static input.
 * Accordingly <i>comment and uncomment</i> the static and custom input line in main method.
 *
 * @author ishwaraju
 */
public class DP04SubsetSumProblem {
    public static boolean isPossible(int[] array, int sum, int size) {
        boolean[][] dp = new boolean[size + 1][sum + 1];
        // Base Condition
        for (int i = 0; i < size + 1; i++) {
            for (int j = 0; j < sum + 1; j++) {
                //top row will be always false since it is not possible to get any sum without any element in array
                if (i == 0)
                    dp[i][j] = false;
                //first column will be always true. since it is always to have sum =0 , if we take and empty set
                if (j == 0)
                    dp[i][j] = true;
            }
        }
        //this logic is for 1 to n-1
        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                if (array[i - 1] <= j) {
                    //either take the number or not
                    dp[i][j] = dp[i - 1][j - array[i - 1]] || dp[i - 1][j];
                } else {
                    //not include the number if the number is gretaer than sum
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[size][sum];
    }

    public static void main(String[] args) {
        HashMap<String, Object> input = staticInput();
        //HashMap<String, Object> input = customInput();
        int[] array = (int[]) input.get("array");
        int sum = (int) input.get("sum");
        int size = (int) input.get("size");
        System.out.println("Array : " + Arrays.toString(array));
        System.out.println("Sum  : " + sum);
        System.out.println("Subset is present or not ::" + isPossible(array, sum, size));
    }

    public static HashMap<String, Object> staticInput() {
        int[] array = new int[]{1, 3, 4, 5};
        int sum = 8;
        int size = 4;
        HashMap<String, Object> input = new HashMap<String, Object>();
        input.putIfAbsent("array", array);
        input.putIfAbsent("sum", sum);
        input.putIfAbsent("size", size);
        return input;
    }

    public static HashMap<String, Object> customInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter size of array : ");
        int size = sc.nextInt();
        System.out.println("Enter array elements:");
        int array[] = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = sc.nextInt();
        }
        System.out.println("enter sum which you looking for  :");
        int sum = sc.nextInt();
        HashMap<String, Object> input = new HashMap<String, Object>();
        input.putIfAbsent("array", array);
        input.putIfAbsent("sum", sum);
        input.putIfAbsent("size", size);
        return input;
    }

    /**
     * Array : [1, 3, 4, 5]
     * Sum  : 8
     * Subset is present or not ::true
     */
}
