package com.bits.pieces.other.subsets;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 9/5/2019
 */
public class ComboTests_3 {

    public static void main(String[] args){
        Object[] elements = new Object[] {'A','B','C','D','E'};

        combination(elements,3);
    }

    public static void combination(Object[]  elements, int k){

        // get the length of the array
        // e.g. for {'A','B','C','D'} => N = 4
        int n = elements.length;

        if(k > n){
            System.out.println("Invalid input, K > N");
            return;
        }
        // calculate the possible combinations
        // e.g. c(4,2)
//        c(n,k);

        // get the combination by index
        // e.g. 01 --> AB , 23 --> CD
        int combination[] = new int[k];

        // position of current index
        //  if (r = 1)              r*
        //  index ==>        0   |   1   |   2
        //  element ==>      A   |   B   |   C
        int r = 0;
        int index = 0;

        while(r >= 0){
            // possible indexes for 1st position "r=0" are "0,1,2" --> "A,B,C"
            // possible indexes for 2nd position "r=1" are "1,2,3" --> "B,C,D"

            // for r = 0 ==> index < (4+ (0 - 2)) = 2
            if(index <= (n + (r - k))){
                combination[r] = index;

                // if we are at the last position print and increase the index
                if(r == k-1){

                    //do something with the combination e.g. add to list or print
//                    print(combination, elements);
                    index++;
                }
                else{
                    // select index for next position
                    index = combination[r]+1;
                    r++;
                }
            }
            else{
                r--;
                if(r > 0)
                    index = combination[r]+1;
                else
                    index = combination[0]+1;
            }
        }
    }
}
