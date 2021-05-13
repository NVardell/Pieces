package com.bits.pieces.topics.subsets;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 9/5/2019
 */
public class ComboTests_4 {

    // Find all distinct combinations of given length
    public static void main(String[] args)
    {
        int[] A = { 1, 2, 3 };
        int k = 2;

        // if array contains repeated elements,
        // sort the array to handle duplicate combinations
        // Arrays.sort(A);

        // process elements from left to right
        recur(A, "", 0, A.length, k);
    }

    // Function to print all distinct combinations of length k
    private static void recur(int[] A, String out, int i, int n, int k)
    {
        // invalid input
        if (k > n) {
            return;
        }

        // base case: combination size is k
        if (k == 0) {
            System.out.println(out);
            return;
        }

        // start from next index till last index
        for (int j = i; j < n; j++)
        {
            // add current element A[j] to solution & recur for next index
            // (j+1) with one less element (k-1)
            recur(A, out + " " + (A[j]) , j + 1, n, k - 1);

            // uncomment below code to handle duplicates
			/* while (j < n - 1 && A[j] == A[j + 1]) {
				 j++;
			}*/
        }
    }

}
