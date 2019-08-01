package com.bits.pieces.other;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 6/30/2019
 */
//import static org.hamcrest.CoreMatchers.is;
//import static org.hamcrest.MatcherAssert.assertThat;
@Slf4j
public class Temp {
    @Test
    public void testRepeatingString(){
        String str = "helloslkhellodjladfjhello";
        String findStr = "hello";
        int lastIndex = 0;
        int count = 0;

        while(lastIndex != -1){

            lastIndex = str.indexOf(findStr,lastIndex);

            if(lastIndex != -1){
                count ++;
                lastIndex += findStr.length();
            }
        }
        System.out.println(count);
        System.out.println(StringUtils.countMatches(str, findStr));
    }
}
