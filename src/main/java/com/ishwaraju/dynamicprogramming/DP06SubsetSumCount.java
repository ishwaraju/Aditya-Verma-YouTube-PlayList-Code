package com.ishwaraju.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class describe the count of the subset having the sum x.
 * The main method contains custom and static input.
 * Accordingly <i>comment and uncomment</i> the static and custom input line in main method.
 *
 * @author ishwaraju
 */
public class DP06SubsetSumCount {
    public static int countSubset(int[] array, int sum, int size) {
        int[][] dp = new int[size + 1][sum + 1];
        //base condition 0 and 1 instead of true and false
        for (int i = 0; i < size + 1; i++) {
            for (int j = 0; j < sum + 1; j++) {
                if (i == 0)
                    dp[i][j] = 0;
                if (j == 0)
                    dp[i][j] = 1;
            }
        }
        //for loop form 1 to n-1
        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                //if number is less than sum either include or exclude
                if (array[i - 1] <= j) {
                    //instead of using or operator need to use + operator since we need the count.
                    dp[i][j] = dp[i - 1][j - array[i - 1]] + dp[i - 1][j];
                } else {
                    //if number is greater than we have only one option to exclude the item
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
        System.out.println("Count of subset ::" + countSubset(array, sum, size));
    }

    public static HashMap<String, Object> staticInput() {
        int[] array = new int[]{1, 3, 4, 5, 2};
        int sum = 5;
        int size = 5;
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
     * Array : [1, 3, 4, 5, 2]
     * Sum  : 5
     * Count of subset ::3
     */
}
