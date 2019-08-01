package com.bits.pieces.service.threading;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO - Add Class Definition
 *
 * @author Nate Vardell
 * @since 7/20/2019
 */
@Slf4j
public class ThreadPrinter {

    public void printCount() {
        try {
            for(int i=5; i>0; i--)
                log.info("Counter   ---   " + i);
        } catch (Exception e) {
            log.info("Thread  interrupted.");
        }
    }
}
