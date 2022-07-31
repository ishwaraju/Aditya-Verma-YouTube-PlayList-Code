package com.ishwaraju.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
/**
 * This class describe the whether from the given array equal sum  is possible or not
 * The main method contains custom and static input.
 * Accordingly <i>comment and uncomment</i> the static and custom input line in main method.
 *
 * @author ishwaraju
 */

public class DP05EqualSumPartition {
    //This method describe the subset probelm by top down approach
    public static boolean subset(int[] array, int sum, int size) {
        //base condition
        boolean[][] dp = new boolean[size + 1][sum + 1];
        for (int i = 0; i < size + 1; i++) {
            for (int j = 0; j < sum + 1; j++) {
                if (i == 0)
                    dp[i][j] = false;
                if (j == 0)
                    dp[i][j] = true;
            }
        }
        //loop from 1 to n-1
        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                //check if element in subset is bigger or smaller than the sum
                if (array[i - 1] <= j) {
                    //either include or exclude the item
                    dp[i][j] = dp[i - 1][j - array[i - 1]] || dp[i - 1][j];
                } else {
                    //exclude the item
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[size][sum];
    }

    public static boolean isequalSumPartition(int[] array, int size) {
        int sum = 0;
        //find the sum of all elements in the array
        for (int i = 0; i < size; i++) {
            sum += array[i];
        }
        //if sum is odd partition is not possible
        if (sum % 2 != 0)
            return false;
            // is sum is not add call subset function by passing the half of the sum
            //s1+ s2 = sum
            //2s = sum
        else {
            return subset(array, sum / 2, size);
        }
    }

    public static void main(String[] args) {
        HashMap<String, Object> input = staticInput();
        //HashMap<String, Object> input = customInput();
        int[] array = (int[]) input.get("array");
        int size = (int) input.get("size");
        System.out.println("Array : " + Arrays.toString(array));
        System.out.println("equal partition is possible or not ::" + isequalSumPartition(array, size));
    }

    public static HashMap<String, Object> staticInput() {
        int[] array = new int[]{3, 4, 5, 6, 2};
        int size = 5;
        HashMap<String, Object> input = new HashMap<String, Object>();
        input.putIfAbsent("array", array);
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
        HashMap<String, Object> input = new HashMap<String, Object>();
        input.putIfAbsent("array", array);
        input.putIfAbsent("size", size);
        return input;
    }

    /**
     * Array : [3, 4, 5, 6, 2]
     * equal partition is possible or not ::true
     */
}
