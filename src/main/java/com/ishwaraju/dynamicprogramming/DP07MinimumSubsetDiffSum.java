package com.ishwaraju.dynamicprogramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;


/**
 * This class describe minimum difference when subset are created.
 * The main method contains custom and static input.
 * Accordingly <i>comment and uncomment</i> the static and custom input line in main method.
 *
 * @author ishwaraju
 */
public class DP07MinimumSubsetDiffSum {

    public static Vector<Integer> isSubSetPossible(int[] array, int sum, int size) {
        Vector<Integer> vector = new Vector<Integer>();
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
        //need to consider only the last row of thr dp table
        //i.e which shows wether that sum is possible or not
        for (int j = 0; j < sum + 1; j++)
            if (dp[size][j] == true)
                vector.add(j);
        return vector;
    }

    public static int subsetMin(int[] array, int size) {
        int range = 0;
        //calculate the range of the subset sum possible
        for (int i = 0; i < size; i++) {
            range += array[i];
        }
        Vector<Integer> vector = isSubSetPossible(array, range, size);
        //here is the logic to find the minimum difference
        //0,1,2,3,7,8,9,10 these are the possible candidate for the subset sum.
        //10-2 = 8 10-4 = 6 10-6 = 4
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < vector.size() / 2; i++) {
            min = Math.min(min, range - (2 * vector.get(i)));
        }
        return min;
    }

    public static void main(String[] args) {
        HashMap<String, Object> input = staticInput();
        //HashMap<String, Object> input = customInput();
        int[] array = (int[]) input.get("array");
        int size = (int) input.get("size");
        System.out.println("Array : " + Arrays.toString(array));
        System.out.println("Minimum difference  between subset ::" + subsetMin(array, size));
    }

    public static HashMap<String, Object> staticInput() {
        int[] array = new int[]{1, 2, 7};
        int size = 3;
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
     * Array : [1, 3, 4, 5, 2]
     * Sum  : 5
     * Count of subset ::3
     */
}
