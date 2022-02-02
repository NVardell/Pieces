package com.bits.pieces.topics.temp;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * TODO - Add Class Definition
 *
 * @author NV
 * @since 2/2/2022
 */
public class Factorial {

    @Test
    public void testFactorial(){
        int x = factorialFunction(5);
//        assertThat(x, is(120));
//        assertThat(factorialFunction(0), is(1));

        // recursiveFactorial
        assertThat(recursiveFactorial(5), is(120));
        assertThat(recursiveFactorial(0), is(1));
    }

    private static int factorialFunction(int i) {
        int factorial = 1;

        if(i>0) {
            factorial = i;
        }
        // Factorial - In = 5
        //             Out = 5*4*3*2*1 =
        while(i>1){
            // 5 | 0 - 5*4 = 20
            // 4 | 20 + 4*3 = 12 + 20 = 32
            // 3 | 32 + 3*2 = 6+32 = 38
            //          2*1
            factorial *= --i;
        }

        return factorial;
    }

    private static int recursiveFactorial(int x) {
        int factorial = x;  // 5

        if(x == 0) {
            return 1;
        }

        if(x>1){
            factorial *= recursiveFactorial(--x);
        }
        return factorial;

    }
}
