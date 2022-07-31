package com.ishwaraju.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class describe the top down version of the 0/1 Knapsack problem
 * Instead of recursion its better to store the value in 2d array which can be used later if already computed.
 * The main method contains custom and static input.
 * Accordingly <i>comment and uncomment</i> the static and custom input line in main method.
 *
 * @author ishwaraju
 */
public class DP03KnapsackTopDown {

    public static int maxProfit(int[] weight, int[] value, int capacity, int size) {
        int[][] dp = new int[size + 1][capacity + 1];
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j <= capacity; j++) {
                //Base condition e.i. 0 fill in all 0 rows and column
                if (i == 0 || j == 0)
                    dp[i][j] = 0;
                    //either select or deselect the element
                else if (weight[i - 1] <= j)
                    dp[i][j] = Math.max(value[i - 1] + dp[i - 1][j - weight[i - 1]], dp[i - 1][j]);
                    //when the weight is too high cant be fit in bag
                else if (weight[i - 1] > j)
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[size][capacity];
    }


    public static void main(String[] args) {
        HashMap<String, Object> input = staticInput();
        //HashMap<String, Object> input = customInput();
        int[] weight = (int[]) input.get("weight");
        int[] value = (int[]) input.get("value");
        int capacity = (int) input.get("capacity");
        int size = (int) input.get("size");
        System.out.println("Weight : " + Arrays.toString(weight));
        System.out.println("Value  : " + Arrays.toString(value));
        System.out.println("Maximum profit ::" + maxProfit(weight, value, capacity, size));
    }

    public static HashMap<String, Object> staticInput() {
        int[] weight = new int[]{1, 3, 4, 5};
        int[] value = new int[]{1, 4, 5, 7};
        int capacity = 7;
        int size = 4;
        HashMap<String, Object> input = new HashMap<String, Object>();
        input.putIfAbsent("weight", weight);
        input.putIfAbsent("value", value);
        input.putIfAbsent("capacity", capacity);
        input.putIfAbsent("size", size);
        return input;
    }

    public static HashMap<String, Object> customInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter size of array : ");
        int size = sc.nextInt();
        System.out.println("enter weight and value :");
        int weight[] = new int[size];
        int value[] = new int[size];
        for (int i = 0; i < size; i++) {
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }
        System.out.println("enter capacity of bag :");
        int capacity = sc.nextInt();
        HashMap<String, Object> input = new HashMap<String, Object>();
        input.putIfAbsent("weight", weight);
        input.putIfAbsent("value", value);
        input.putIfAbsent("capacity", capacity);
        input.putIfAbsent("size", size);
        return input;
    }

    /**
     * Expected output ::
     * Weight : [1, 3, 4, 5]
     * Value :[1, 4, 5, 7]
     * Maximum profit ::9
     */
}
