package com.ishwaraju.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class describe the memoization version of the 0/1 Knapsack problem
 * Instead of recursion its better to store the value in 2d array which can be used later if already computed.
 * The main method contains custom and static input.
 * Accordingly <i>comment and uncomment</i> the static and custom input line in main method.
 *
 * @author ishwaraju
 */
public class DP02KnapsackMemoization {
    //dp[][] array used to store value which already calculated.
    public static int maxProfit(int weight[], int value[], int capacity, int size) {
        int dp[][] = new int[size + 1][capacity + 1];
        for (int i = 0; i < size+1; i++) {
            for (int j = 0; j < capacity+1; j++)
                dp[i][j] = -1;
        }
        return maxProfitRec(weight, value, capacity, size, dp);
    }


    //This method is the core logic
    public static int maxProfitRec(int weight[], int value[], int capacity, int size, int[][] dp) {
        //base condition is minimum valid input
        //i.e. when capacity is 0 or size of array is 0
        if (size == 0 || capacity == 0)
            return 0;
        //check wether the value is already calculated
        if (dp[size][capacity] != -1)
            return dp[size][capacity];
        //two option are there , either we can select the object or deselect the object
        else if (weight[size - 1] <= capacity) {
            return dp[size][capacity] = Math.max((value[size - 1] + maxProfitRec(weight, value, capacity - weight[size - 1], size - 1, dp)),
                    (maxProfitRec(weight, value, capacity, size - 1, dp)));
        }
        //no option when the weight of item is bigger than capacity of bag
        else if (weight[size - 1] > capacity) {
            return dp[size][capacity] = maxProfitRec(weight, value, capacity, size - 1, dp);
        }
        return -1;
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
